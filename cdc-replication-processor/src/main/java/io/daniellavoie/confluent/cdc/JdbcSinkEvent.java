package io.daniellavoie.confluent.cdc;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JdbcSinkEvent {
	private final Object schema;
	private final Map<String, Object> payload;

	@JsonCreator
	public JdbcSinkEvent(@JsonProperty("schema") Object schema, @JsonProperty("payload") Map<String, Object> payload) {
		this.schema = schema;
		this.payload = payload;
	}

	public Object getSchema() {
		return schema;
	}

	public Map<String, Object> getPayload() {
		return payload;
	}
}
