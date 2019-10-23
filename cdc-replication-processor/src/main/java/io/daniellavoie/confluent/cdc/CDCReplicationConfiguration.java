package io.daniellavoie.confluent.cdc;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("cdc")
public class CDCReplicationConfiguration {
	private String[] tables;
	private String sourcePrefix = "";
	private String sinkPrefix = "";

	public String[] getTables() {
		return tables;
	}

	public void setTables(String[] tables) {
		this.tables = tables;
	}

	public String getSourcePrefix() {
		return sourcePrefix;
	}

	public void setSourcePrefix(String sourcePrefix) {
		this.sourcePrefix = sourcePrefix;
	}

	public String getSinkPrefix() {
		return sinkPrefix;
	}

	public void setSinkPrefix(String sinkPrefix) {
		this.sinkPrefix = sinkPrefix;
	}
}
