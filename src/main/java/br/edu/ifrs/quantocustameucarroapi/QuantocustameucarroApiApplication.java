package br.edu.ifrs.quantocustameucarroapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class QuantocustameucarroApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuantocustameucarroApiApplication.class, args);
	}

}
