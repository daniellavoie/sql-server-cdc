package io.daniellavoie.confluent.cdc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@EnableKafkaStreams
@SpringBootApplication
public class CDCReplicationProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CDCReplicationProcessorApplication.class, args);
	}

}
