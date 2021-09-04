package com.banco.application.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.banco.application.Repository.AgenciaRepository;
import com.banco.application.models.Agencia;
import com.banco.application.specification.AgenciaSpecification;

@Service
public class AgenciaService {

	@Autowired
	AgenciaRepository agenciaRepository;
	
	public Page<Agencia> buscarTodos(Integer numeroPagina, Integer itensPorPagina,
			String campoOrdenacao, String direcaoOrdenacao, Integer id, String nome){
		
		AgenciaSpecification specAgencia = new AgenciaSpecification(id, nome);
		
		PageRequest pageRequest = PageRequest.of(numeroPagina, itensPorPagina,
				Direction.fromString(direcaoOrdenacao), campoOrdenacao);
		
		return agenciaRepository.findAll(specAgencia, pageRequest);
	}
	
	public ArrayList<Agencia> buscarPorFiltro(Integer id, String nome){
		
		ArrayList<Agencia> agencias = new ArrayList<Agencia>();
		
		if(id!= null && nome != null) {
			agencias = (ArrayList<Agencia>) agenciaRepository.findByNomeAndId(nome, id);
		} if (id == null) {
			agencias = (ArrayList<Agencia>) agenciaRepository.findByNome(nome);
		}else if(nome  == null) {
			agencias.add(agenciaRepository.findById(id).get());
		}
		return agencias;
	}
	
	public ArrayList<Agencia> buscarPorFiltroSpecification(Integer id, String nome){
		AgenciaSpecification specAgencia = new AgenciaSpecification(id, nome);
		
		return (ArrayList<Agencia>) agenciaRepository.findAll(specAgencia);
	}
	
}
