package com.banco.application.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.application.Repository.ContaRepository;
import com.banco.application.models.Agencia;
import com.banco.application.models.Conta;

@Service
public class ContaService {

	@Autowired
	ContaRepository contaRepository;
	
	public ArrayList<Conta> findAll(){
		ArrayList<Conta> contas = new ArrayList<Conta>();
		
		contas  = (ArrayList<Conta>) contaRepository.findAll();
		
		for(Conta conta : contas) {
			System.out.println(conta.getCliente().getNome());
		}
		
		return contas;
	}
}























