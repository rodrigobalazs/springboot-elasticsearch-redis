package com.rbalazs.storeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Enables 'Redis Cache' during Spring Boot bootstrap
 */
@SpringBootApplication
@EnableCaching
public class StoreApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApiApplication.class, args);
	}

}
