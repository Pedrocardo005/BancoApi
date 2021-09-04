package com.banco.application.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.banco.application.models.Agencia;

public interface AgenciaRepository extends JpaRepository<Agencia, Integer>, JpaSpecificationExecutor<Agencia>{

	//Consulta por HQL
	@Query("SELECT ag FROM agencia ag WHERE ag.id = ?1 or ag.nome like ?2")
	public List<Agencia> buscarPorFiltro(Integer id, String nome);
	
	//Consulta por SQL nativo
	@Query(value="SELECT * FROM agencia WHERE id = :id or name like :nome", nativeQuery = true)
	public List<Agencia> buscarPorFiltroSqlNativo(Integer id, String nome);
	
	public List<Agencia> findByIdOrNome(Integer id, String nome);
	
	//Consulta por Derived methods Jpa data que interpreta e cria a consulta pelo nome do método
	public List<Agencia> findByIdGreaterThanEqualOrNomeContaining(Integer id, String nome);
	
	public List<Agencia> findByNomeAndId(String name, Integer id);
	
	public List<Agencia> findByNome(String name);
}
