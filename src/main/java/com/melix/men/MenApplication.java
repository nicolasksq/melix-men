package com.melix.men;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MenApplication {

	public static void main(String[] args) {
		SpringApplication.run(MenApplication.class, args);
	}

}
