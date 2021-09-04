package com.banco.application.Repository;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.banco.application.models.Cliente;

//cria um bean do tipo repositório.
@Repository
@Deprecated
public class ClienteRepository {
	public String resgatarNome(Integer matricula) {
		HashMap<Integer, String> hashClientes = new HashMap<Integer, String>();
		hashClientes.put(1, "Duan");
		hashClientes.put(2, "Tayanne");
		hashClientes.put(3, "Henrique");
		String nome = hashClientes.get(matricula);
		return nome;
	}
	
	public Cliente resgatarClienteRepository(Integer cpf) {
		HashMap<Integer, Cliente> hashClientes = new HashMap<Integer, Cliente>();
		hashClientes.put(89398, new Cliente("Duan", 89398));
		hashClientes.put(873123, new Cliente("Tayanne", 873123));
		hashClientes.put(987312, new Cliente("Henrique", 987312));
				
		Cliente cliente = hashClientes.get(cpf);
		return cliente;
	}

	public ArrayList<Cliente> resgatarClientePorGeneroRepository(Character genero){
		ArrayList<Cliente> clientesBanco = new ArrayList<Cliente>();
		
		clientesBanco.add(new Cliente("Julyellen", 123, 'F'));
		clientesBanco.add(new Cliente("Duan", 123, 'M'));
		clientesBanco.add(new Cliente("Fernando", 123, 'M'));
		clientesBanco.add(new Cliente("Juliana", 123, 'F'));
		
		ArrayList<Cliente> listaRetorno = new ArrayList<Cliente>();
		for(Cliente clienteBanco: clientesBanco) {
			if(clienteBanco.getSexo().equals(genero)) {
				listaRetorno.add(clienteBanco);
			}
		}
		return listaRetorno;
	}
	
	
}














































