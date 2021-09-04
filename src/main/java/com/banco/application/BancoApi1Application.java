package com.banco.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.banco.application.models.Empregado;
//esse cara é um bean
@SpringBootApplication
@EnableCaching
@EnableScheduling
public class BancoApi1Application {

	public static void main(String[] args) {
		SpringApplication.run(BancoApi1Application.class, args);
		Empregado empregado = new Empregado();
		empregado.setCpf(198221);
		empregado.setNome("Maluquinho");
		empregado.setSalario(300D);
		System.out.println(empregado);
	}

}
