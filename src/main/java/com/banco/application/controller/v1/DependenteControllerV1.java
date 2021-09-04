package com.banco.application.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.banco.application.service.DependenteService;

@CrossOrigin
@RestController
@RequestMapping("/v1/dependentes")
public class DependenteControllerV1 {

	@Autowired
	DependenteService dependenteService;
	
	@PostMapping
	public void salvar(@RequestParam("nome") String nome,
			@RequestParam("foto") MultipartFile foto ){
		dependenteService.salvar(nome, foto);
	}
}
