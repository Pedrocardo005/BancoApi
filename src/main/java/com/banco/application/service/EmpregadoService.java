package com.banco.application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.banco.application.Repository.EmpregadoRepository;
import com.banco.application.dto.EmpregadoDTO;
import com.banco.application.models.Empregado;
import com.banco.application.specification.EmpregadoSpecification;

@Service
public class EmpregadoService {

	@Autowired
	EmpregadoRepository empregadoRepository;
	
	@Autowired
	CacheManager cacheManager;
	
	@Cacheable("empregado")
	public Page<EmpregadoDTO> findAll(Integer cpf, String nome, String nomeAgencia, Integer qtdItensPagina, Integer numeroPagina, String direcaoOrdenacao, String campoOrdem){
		
		PageRequest pageRequest = PageRequest.of(numeroPagina, qtdItensPagina, Direction.valueOf(direcaoOrdenacao), campoOrdem);
		Double salario = null;
		EmpregadoSpecification specEmpregado = new EmpregadoSpecification(cpf, nome, nomeAgencia, salario);
		
		Page<Empregado> pageEmpregados = (Page<Empregado>) empregadoRepository.findAll(specEmpregado,pageRequest);
		
//		ArrayList<EmpregadoDTO> empregadosDto = new ArrayList<EmpregadoDTO>();
//		for(Empregado empregado: pageEmpregados) {
//			EmpregadoDTO empregadoDto = new EmpregadoDTO();
//			empregadoDto.createEmpregadoDto(empregado);
//			empregadosDto.add(empregadoDto);
//		}
//		
//		final int start = (int)pageRequest.getOffset();
//		final int end = Math.min((start + pageRequest.getPageSize()), empregadosDto.size());
//		final Page<EmpregadoDTO> pageEmpregadosDTO = new PageImpl<>(empregadosDto.subList(start, end), pageRequest,
//				empregadosDto.size());
		
		List<EmpregadoDTO> empregadosDto = pageEmpregados.stream()
				.map(e -> new EmpregadoDTO().createEmpregadoDto(e)).collect(Collectors.toList());
		
		int totalElements = (int) pageEmpregados.getTotalElements();
				
		return new PageImpl<EmpregadoDTO>(empregadosDto, pageRequest, totalElements);
		
//		return pageEmpregadosDTO; 
	}
	
	public void save(Empregado e) {
		empregadoRepository.save(e);
		//garante que todo save de empregado o cache é apagado. Empregado é o que é referenciado no cacheable.
//		cacheManager.getCache("empregado").clear();
	}
	
	public EmpregadoDTO findById(Integer cpf) {
		Optional<Empregado> opEmpregado = empregadoRepository.findById(cpf);
		Empregado empregado = new Empregado();
		if(opEmpregado.isPresent()) {
			empregado = opEmpregado.get();
		}
		EmpregadoDTO empregadoDto = new EmpregadoDTO();
		empregadoDto.createEmpregadoDto(empregado);
		return empregadoDto;
	}
	
	public void clearCacheEmpregados() {
		cacheManager.getCache("empregado").clear();
		System.out.println("Limpando o cache!");
	}
	
	public Page<EmpregadoDTO> buscarSalariosMaiores(Double salario
			,Integer qtdItensPagina,
			Integer numeroPagina,String direcaoOrdenacao,String campoOrdem){
		
		PageRequest pageRequest = PageRequest.of(numeroPagina, qtdItensPagina, Direction.fromString(direcaoOrdenacao), campoOrdem);

		EmpregadoSpecification empregadoSpec = new EmpregadoSpecification(salario);
		
		Page<Empregado> pageEmpregados = empregadoRepository.findAll(empregadoSpec, pageRequest);
		
		int totalElements = (int) pageEmpregados.getTotalElements();
		
		List<EmpregadoDTO> empregadosDTO = pageEmpregados.stream().filter(empregado -> empregado.getSalario() > salario)
			.map(empregado -> new EmpregadoDTO().createEmpregadoDto(empregado)).collect(Collectors.toList());
		
		return new PageImpl<>(empregadosDTO, pageRequest, totalElements);
	}
}
