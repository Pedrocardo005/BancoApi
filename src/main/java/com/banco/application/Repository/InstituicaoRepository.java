package com.banco.application.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.banco.application.models.InstituicaoFinanceira;
//Repository que vai ser transformado em Bean pelo JPA e que 
//conterá operações padrões que serão extendidas do JPARepository
public interface InstituicaoRepository extends JpaRepository<InstituicaoFinanceira, Integer>{

	//inst vira uma variável do tipo instituicao_financeira
	//?1 é o primeiro parâmetro, ?2 seria o segundo do método.
	//Hibernate Query Language ou HQL, que vai ser dentro desse query
	@Query("SELECT inst FROM instituicao_financeira inst WHERE nome like ?1") 
	public List<InstituicaoFinanceira> findByName(String nome);
}
















