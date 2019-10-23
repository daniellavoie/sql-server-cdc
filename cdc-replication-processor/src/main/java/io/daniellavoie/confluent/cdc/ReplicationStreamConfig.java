package io.daniellavoie.confluent.cdc;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.support.serializer.JsonSerde;

import io.daniellavoie.confluent.cdc.sqlserver.Event;
import io.daniellavoie.confluent.cdc.sqlserver.Field;

@Configuration
public class ReplicationStreamConfig {

	@Bean
	public List<KStream<Map<String, Object>, JdbcSinkEvent>> replicationStream(StreamsBuilder kStreamBuilder,
			CDCReplicationConfiguration cdcReplicationConfiguration, Environment environment) {

		Map<String, String[]> pkFieldsByTable = Arrays.stream(cdcReplicationConfiguration.getTables())

				.collect(Collectors.toMap(table -> table,
						table -> environment.getRequiredProperty("cdc.pk." + table).split(",")));

		return Arrays.stream(cdcReplicationConfiguration.getTables())

				.map(table -> to(buildStream(kStreamBuilder, computeSourceTopicName(cdcReplicationConfiguration, table))

						.map((k, v) -> mapPayload(k, v, pkFieldsByTable.get(v.getPayload().getSource().getTable())))

						, table, cdcReplicationConfiguration))

				.collect(Collectors.toList());
	}

	private String computeSourceTopicName(CDCReplicationConfiguration cdcReplicationConfiguration, String table) {
		return (!cdcReplicationConfiguration.getSourcePrefix().trim().equals("")
				? cdcReplicationConfiguration.getSourcePrefix().trim() + "."
				: "") + table;
	}

	private String computeSinkTopicName(CDCReplicationConfiguration cdcReplicationConfiguration, String table) {
		return (!cdcReplicationConfiguration.getSinkPrefix().trim().equals("")
				? cdcReplicationConfiguration.getSinkPrefix().trim() + "."
				: "") + table;
	}

	private KStream<String, Event> buildStream(StreamsBuilder kStreamBuilder, String sourceTopic) {
		return kStreamBuilder.stream(sourceTopic, Consumed.with(Serdes.String(), new JsonSerde<Event>(Event.class)));
	}

	private KeyValue<Map<String, Object>, JdbcSinkEvent> mapPayload(String key, Event event, String[] pkFieldsByTable) {
		Field tableSchema = Arrays.stream(event.getSchema().getFields())
				.filter(field -> "after".equals(field.getField())).findFirst()
				.orElseThrow(() -> new RuntimeException("Could not find \"after\" field"));

		Map<String, Object> keyStruct = Arrays.stream(pkFieldsByTable)
				.collect(Collectors.toMap(pkField -> pkField, pkField -> event.getPayload().getAfter().get(pkField)));

		return new KeyValue<Map<String, Object>, JdbcSinkEvent>(keyStruct,
				new JdbcSinkEvent(tableSchema, event.getPayload().getAfter()));

	}

	private KStream<Map<String, Object>, JdbcSinkEvent> to(KStream<Map<String, Object>, JdbcSinkEvent> eventStream,
			String table, CDCReplicationConfiguration cdcReplicationConfiguration) {
		eventStream.to(computeSinkTopicName(cdcReplicationConfiguration, table));

		return eventStream;
	}
}