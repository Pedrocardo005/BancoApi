package com.banco.application.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.banco.application.Repository.ClienteRepository;
import com.banco.application.Repository.ClienteRepository2;
import com.banco.application.Repository.ContaRepository;
import com.banco.application.dto.ClienteDTO;
import com.banco.application.models.Cliente;
//Anotação que transforma essa classe em um Bean que será gerenciado
//pelo framework.
@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	//não vai erro porque o jpa faz criar como bean
	@Autowired
	ClienteRepository2 clienteRepository2;
	
	@Autowired
	CryptoService cryptoService;
	
	@Autowired
	ContaRepository contaRepository;
	
	public ArrayList<Cliente> findAll(int page, int size){
		ArrayList<Cliente> listaRetorno = new ArrayList<Cliente>();
		PageRequest.of(page, size);
		listaRetorno = (ArrayList<Cliente>)clienteRepository2.findAll();
		
		return listaRetorno;
	}
	
	//Método responsável
	public ClienteDTO findById(Integer cpf) {
		Optional<Cliente> oCliente;
		oCliente = clienteRepository2.findById(cpf);	
		
		Cliente cliente = oCliente.get();
		
		Double dCotacaoBitCoin = cryptoService.getBitCoinPrice();
		
		cliente.getContas().forEach(c ->
		c.setSaldoBitCoin(c.getSaldoBitCoin() * dCotacaoBitCoin));
		
		ClienteDTO clienteDto = new ClienteDTO();
		clienteDto.createClienteDto(cliente);
		
		return clienteDto;
	}
	
	public String pesquisarNomeService(Integer matricula) {
		String nome = clienteRepository.resgatarNome(matricula);
		return nome;
	}
	
	//Reponsável por executar uma operação sobre os clientes
	//e resgatar os dados.
	public Cliente pesquisarClienteService(Integer cpf) {
		
		Cliente cliente = clienteRepository.resgatarClienteRepository(cpf);
		return cliente;
	}
	
	public ArrayList<Cliente> pesquisarPorGenero(Character genero) {
		//no service que coloca essas regras ou essa conversão.
		ArrayList<Cliente> clientes = clienteRepository2.findByGenero(Character.toUpperCase(genero));
		return clientes;
	}
	
	public ArrayList<Cliente> pesquisarPorNumero(String numero){
		return clienteRepository2.pesquisarPorNumero(numero);
	}
}















































