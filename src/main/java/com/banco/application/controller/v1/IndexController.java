package com.banco.application.controller.v1;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banco.application.models.Cliente;
import com.banco.application.service.ClienteService;

@RestController
@RequestMapping("/index")
public class IndexController {
	
	
	
	@GetMapping
	public String bemVindo() {
		return "Bem-vindo ao banco Fernandes";
	}
	
	//para acessar na url coloca /nome?name=nomeQueDeseja
	@GetMapping("/nome")
	public String bemVindoNome(@RequestParam("name") String nome) {
		return "Bem-vindo" + nome;
	}
	
	@GetMapping("/gerente")
	public String bemVindoGerente() {
		return "Seja muito bem-vindo Gerente!";
	}
}

























