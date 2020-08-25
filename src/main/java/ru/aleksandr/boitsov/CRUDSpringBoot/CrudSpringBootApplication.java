package ru.aleksandr.boitsov.CRUDSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.aleksandr.boitsov.CRUDSpringBoot")
public class CrudSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringBootApplication.class, args);
	}
}
