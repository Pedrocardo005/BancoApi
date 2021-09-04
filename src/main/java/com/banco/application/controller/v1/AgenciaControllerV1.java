package com.banco.application.controller.v1;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banco.application.models.Agencia;
import com.banco.application.service.AgenciaService;

@RestController
@RequestMapping("/v1/agencias")
public class AgenciaControllerV1 {
	
	@Autowired
	AgenciaService agenciaService;
	
	@GetMapping
	public ResponseEntity<Page<Agencia>> buscarTodos(
			@RequestParam(required = false, defaultValue = "0") Integer numeroPagina,
			@RequestParam(required = false, defaultValue = "2") Integer itensPorPagina,
			@RequestParam(required = false, defaultValue = "nome") String campoOrdenacao,
			@RequestParam(required = false, defaultValue = "ASC") String direcaoOrdenacao,
			@RequestParam(required = false) Integer id,
			@RequestParam(required = false) String nome){
		
		Page<Agencia> agencias = agenciaService.buscarTodos(numeroPagina, itensPorPagina,
				campoOrdenacao, direcaoOrdenacao, id, nome);
		return new ResponseEntity<Page<Agencia>>(agencias, HttpStatus.OK);
	}

	@GetMapping("/filtros")
	public ResponseEntity<ArrayList<Agencia>> buscarPorFiltros(
			@RequestParam(required=false) Integer id,
			@RequestParam(required=false) String nome){
		
		ArrayList<Agencia> agencias = agenciaService.buscarPorFiltroSpecification(id, nome);
		return new ResponseEntity<ArrayList<Agencia>>(agencias, HttpStatus.OK);
	}
	
}
