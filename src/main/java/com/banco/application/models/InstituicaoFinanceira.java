package com.banco.application.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="instituicao_financeira")
public class InstituicaoFinanceira {

	@Id
	@Column(name="id")//posso usar também com o id.
	@GeneratedValue(strategy=GenerationType.IDENTITY)//auto increment, mas cada banco pode ser diferente
	private Integer identifier;
	
	//boa prática identifi
	@Column(name="nome", nullable = false)
	private String name;
	
	//quem tem a informação de quem tem contas, pego lá em Conta.
	@OneToMany(mappedBy = "instituicao")
	private List<Conta> contas;
	
	@OneToMany(mappedBy="instituicao")
	private List<Agencia> agencias;	

	@Transient
	@JsonIgnore
	private String sId;
	
	
	public InstituicaoFinanceira() {
		
	}

	public Integer getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

	public String getsId() {
		return sId;
	}

	public void setsId(String sId) {
		this.sId = sId;
	}

	public List<Agencia> getAgencias() {
		return agencias;
	}

	public void setAgencias(List<Agencia> agencias) {
		this.agencias = agencias;
	}
}





























