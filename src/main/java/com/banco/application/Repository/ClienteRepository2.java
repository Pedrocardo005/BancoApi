package com.banco.application.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.banco.application.models.Cliente;
//Repositório que extende o JPARepository e vai ter a responsabilidade
//de executar operações em Cliente.
//Integer é o tipo do ID. No nosso caso é o id.
public interface ClienteRepository2 extends JpaRepository<Cliente, Integer>{

	//a variável genero não sobre pela troca por sexo
	@Query("SELECT c FROM cliente c WHERE sexo LIKE ?1")
	public ArrayList<Cliente> findByGenero(Character genero);
	
	@Query("SELECT c FROM cliente c WHERE telefone LIKE '%' || ?1 || '%'")
	public ArrayList<Cliente> pesquisarPorNumero(String numero);
}














































