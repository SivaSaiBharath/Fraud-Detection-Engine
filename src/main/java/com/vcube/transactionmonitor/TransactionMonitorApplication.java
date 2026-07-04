package com.vcube.transactionmonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication
public class TransactionMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionMonitorApplication.class, args);
	}

}
