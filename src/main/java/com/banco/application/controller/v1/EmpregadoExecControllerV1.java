package com.banco.application.controller.v1;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banco.application.models.Empregado;
import com.banco.application.service.EmpregadoServiceExec;

@RestController
@RequestMapping("/v1/empregadosalario")
public class EmpregadoExecControllerV1 {

	@Autowired
	EmpregadoServiceExec empregadoService;
	
	@GetMapping
	public ResponseEntity<ArrayList<Empregado>> findAllSalario(){
		ArrayList<Empregado> empregados = new ArrayList<Empregado>();
		empregados = empregadoService.findAllSalario();
		return new ResponseEntity<ArrayList<Empregado>>(empregados, HttpStatus.OK);
	}
}
