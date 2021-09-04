package com.banco.application.controller.v1;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banco.application.models.InstituicaoFinanceira;
import com.banco.application.service.InstituicaoService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1/instituicoes")
public class InstituicaoFinanceiraControllerV1 {

	@Autowired
	InstituicaoService instituicaoService;
	
	@GetMapping
	public ResponseEntity<ArrayList<InstituicaoFinanceira>> buscarTodos(){
		return new ResponseEntity<ArrayList<InstituicaoFinanceira>>(instituicaoService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{identifier}")
	public ResponseEntity<InstituicaoFinanceira> buscarPorId(@PathVariable Integer identifier) {
		return new ResponseEntity<InstituicaoFinanceira>(instituicaoService.findById(identifier), HttpStatus.OK);
	}
	
	@GetMapping("/nome")
	public ResponseEntity<ArrayList<InstituicaoFinanceira>> buscarPorNome(@RequestParam String nome){
		ArrayList<InstituicaoFinanceira> instituicoes;
		instituicoes = instituicaoService.findByName(nome);
		
		return new ResponseEntity<ArrayList<InstituicaoFinanceira>>(instituicoes, HttpStatus.OK); 
	}
	
	@PostMapping
	public ResponseEntity inserir(@RequestBody InstituicaoFinanceira instituicao) {
		instituicaoService.save(instituicao);
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Atualizou a isntituição"),
		    @ApiResponse(code = 422, message = "Instituição informada não existe"),
		})
	@PutMapping
	public ResponseEntity atualizar(@RequestBody InstituicaoFinanceira instituicao) {
		try {
			instituicaoService.update(instituicao);
			return ResponseEntity.status(HttpStatus.OK).body(instituicao);
			//return ResponseEntity.ok(instituicao); é a mesma coise de cima
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Não foi encontrado");
		}
	}
	
	@DeleteMapping("/{identifier}")
	public void deletar(@PathVariable("identifier") Integer identifier) {
		instituicaoService.deletar(identifier);
	}
}


























