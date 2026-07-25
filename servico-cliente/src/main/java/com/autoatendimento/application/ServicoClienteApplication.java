package com.autoatendimento.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.autoatendimento")
@EnableFeignClients(basePackages = "com.autoatendimento.cliente.client")
@EnableJpaRepositories(basePackages = "com.autoatendimento.cliente.repository")
@EntityScan(basePackages = "com.autoatendimento.cliente.entity")
public class ServicoClienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicoClienteApplication.class, args);
	}

}
