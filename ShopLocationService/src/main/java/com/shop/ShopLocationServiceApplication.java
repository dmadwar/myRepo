package com.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.controller","com.service"})
public class ShopLocationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopLocationServiceApplication.class, args);
	}
}
