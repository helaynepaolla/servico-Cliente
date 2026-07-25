package com.autoatendimento.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServicoClienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicoClienteApplication.class, args);
	}

}
