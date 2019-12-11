package com.ms.training.SimpleWeb;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.ms.training.SimpleWeb.model.Event;
import com.ms.training.SimpleWeb.model.EventRepository;

@SpringBootApplication
public class SimpleWebApplication {

	private static Logger log = LoggerFactory.getLogger(SimpleWebApplication.class);
	
	@Autowired
	EventRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(SimpleWebApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(ApplicationContext ctx) {
		return args -> {
			Event e = new Event(1, "CART-CHECKEDOUT", UUID.randomUUID().toString(), new Date());
			repo.save(e);
			e = new Event(2, "ORDER PLACED", UUID.randomUUID().toString(), new Date());
			repo.save(e);
			e = new Event(3, "ORDER SHIPPED", UUID.randomUUID().toString(), new Date());
			repo.save(e);
			
		};
	}

}
