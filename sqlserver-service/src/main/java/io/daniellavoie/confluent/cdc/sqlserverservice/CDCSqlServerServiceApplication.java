package io.daniellavoie.confluent.cdc.sqlserverservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class CDCSqlServerServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CDCSqlServerServiceApplication.class, args);
	}

	private CustomerGenerator customerGenerator;

	public CDCSqlServerServiceApplication(CustomerGenerator customerGenerator) {
		this.customerGenerator = customerGenerator;
	}

	@Override
	public void run(String... args) throws Exception {
		customerGenerator.generateCustomers();
	}
}
