package com.xantx.financeapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.xantx.financeapp.services", "com.xantx.financeapp.controllers" })
public class FinanceAppBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceAppBackendApplication.class, args);
	}

}
