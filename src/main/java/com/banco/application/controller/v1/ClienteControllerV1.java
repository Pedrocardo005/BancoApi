package com.banco.application.controller.v1;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banco.application.dto.ClienteDTO;
import com.banco.application.models.Cliente;
import com.banco.application.service.ClienteService;

@RestController
@RequestMapping("/v1/clientes")
//Controller responsável pelo recurso cliente
public class ClienteControllerV1 {

	//notação obrigatório colocar para bean que não precisa instanciar
	//ponto de injeção.
	@Autowired
	ClienteService clienteService;
	
	@GetMapping("/{cpf}")
	public ResponseEntity<ClienteDTO> buscarClientePorCpf(@PathVariable Integer cpf){
		clienteService.findById(cpf);
		return new ResponseEntity<ClienteDTO>(clienteService.findById(cpf), HttpStatus.OK);
	}

	//Criar uma rota para o método POST
	@PostMapping
	public void incluirCliente(@RequestBody Cliente cliente) {
		
		System.out.println(cliente);
	}
	
	@GetMapping("/pesquisarNome")
	public String pesquisarNome(@RequestParam("mat") Integer matricula) {
		String nome = clienteService.pesquisarNomeService(matricula);
		return nome;
	}

	@GetMapping("/pesquisarCliente")
	public Cliente pesquisarNomeCliente(@RequestParam("cpf") Integer cpf) {

		Cliente cliente = clienteService.pesquisarClienteService(cpf);
		return cliente;
	}

	//Nova forma de passar parâmetros, por rota
	@GetMapping("/genero/{genero}")
	public ArrayList<Cliente> pesquisarClientePorGenero(
			@PathVariable("genero") Character genero,
			@RequestParam("cpf") String cpf,
			/*/genero/m?cpf=123&nome=phascoal*/@RequestParam("nome") String nome){
		//@RequestParam o primeiro é ? e os próximos é &.
		return clienteService.pesquisarPorGenero(genero);
	}
}





















