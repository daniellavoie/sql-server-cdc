package io.daniellavoie.confluent.cdc.sqlserverservice;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Component
public class CustomerGenerator {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerGenerator.class);

	private CustomerRepository customerRepository;

	public CustomerGenerator(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public void generateCustomers() {
		Faker faker = new Faker();

		Flux.interval(Duration.ofSeconds(1))
				.map(index -> new Customer(0, faker.name().lastName(), faker.name().firstName(),
						faker.address().country(), faker.address().city(), faker.address().streetName(),
						faker.address().buildingNumber()))

				.doOnNext(customerRepository::save)

				.retryBackoff(10, Duration.ofSeconds(1))

				.doOnError(ex -> LOGGER.error("Failed to rety", ex))

				.subscribeOn(Schedulers.newSingle("Customer-Generator"))

				.subscribe();
	}
}
