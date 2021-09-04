package com.banco.application.controller.v2;

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

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v2/clientes")
//Controller responsável pelo recurso cliente
public class ClienteControllerV2 {

	//notação obrigatório colocar para bean que não precisa instanciar
	//ponto de injeção.
	@Autowired
	ClienteService clienteService;
	
	@ApiOperation(value="Retorna uma lista de pessoas")
	@GetMapping
	public ArrayList<Cliente> pesquisarTodos(
			@RequestParam int page,
			@RequestParam int size) {
		return clienteService.findAll(page, size);
	}
	
	//
	@ApiOperation(value="chama por cpf")
	//Cria uma rota para resgatar cliente por ID
	@GetMapping("/{cpf}")
	public ClienteDTO resgatarClientePorId(@PathVariable("cpf") Integer cpf) {
		return clienteService.findById(cpf);
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
	
	@GetMapping("/telefone/{numero}")
	public ResponseEntity<ArrayList<Cliente>>
		pesquisarPorNumero(@PathVariable String numero){
		ArrayList<Cliente> listaRetorno = clienteService.pesquisarPorNumero(numero);
		return new ResponseEntity<ArrayList<Cliente>>(listaRetorno, HttpStatus.OK);
	}
}





















