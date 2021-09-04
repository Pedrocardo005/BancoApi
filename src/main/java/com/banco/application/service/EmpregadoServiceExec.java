package com.banco.application.service;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.application.Repository.EmpregadoRepository;
import com.banco.application.models.Empregado;

@Service
public class EmpregadoServiceExec {

	@Autowired
	EmpregadoRepository empregadoRepository;
	
	public ArrayList<Empregado> findAllSalario(){
		ArrayList<Empregado> empregados = new ArrayList<Empregado>();
		
		empregados = (ArrayList<Empregado>) empregadoRepository.findAll();
		
		ArrayList<Empregado> empregadosSalario = new ArrayList<Empregado>();
		for(Empregado empregado: empregados) {
			
		}
		
		empregadosSalario = (ArrayList<Empregado>)empregados.stream().filter(e -> e.getSalario() > 1000).collect(Collectors.toList());
		
		return empregadosSalario;
	}
}
