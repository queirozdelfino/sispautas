package com.cooperativismo.sispautas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SispautasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SispautasApplication.class, args);
	}

}
