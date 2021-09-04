package com.banco.application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.application.Repository.InstituicaoRepository;
import com.banco.application.dto.EmpregadoDTO;
import com.banco.application.models.Empregado;
import com.banco.application.models.InstituicaoFinanceira;

//Define que esta classe é um bean
@Service
public class InstituicaoService {
	
	//Cria um ponto de injeção
	@Autowired // faz a injeção, inject.
	InstituicaoRepository instituicaoRepository;
	
	//método responsável por fazer o casting de list para ArrayList
	//e consumir o JpaRepository
	public ArrayList<InstituicaoFinanceira> findAll(){
		return (ArrayList<InstituicaoFinanceira>)instituicaoRepository.findAll();
	}
	
	public InstituicaoFinanceira findById(Integer identifier) {
		Optional<InstituicaoFinanceira> instituicao = instituicaoRepository.findById(identifier);
		return instituicao.get();
	}
	
	public ArrayList<InstituicaoFinanceira> findByName(String nome){
		ArrayList<InstituicaoFinanceira> instituicoes;
		instituicoes = (ArrayList<InstituicaoFinanceira>)instituicaoRepository.findByName(nome);
		return instituicoes;
	}
	
	//Chama o save od repositório responsável por 
	//inserir ou atualizar.
	public InstituicaoFinanceira save(InstituicaoFinanceira cliente){
		
		return instituicaoRepository.save(cliente);
	}
	
	public void update(InstituicaoFinanceira instituicao) throws Exception{
		Optional<InstituicaoFinanceira> opInstituicao = 
		instituicaoRepository.findById(instituicao.getIdentifier());
		
		if(opInstituicao.isPresent()) {
			instituicaoRepository.save(instituicao);
		}else {
			System.out.println("Erro");
			throw new Exception("Não existe instituição com o código informado");
		}
	}
	
	public void deletar(Integer identifier) {
		instituicaoRepository.deleteById(identifier);
	}
}


























