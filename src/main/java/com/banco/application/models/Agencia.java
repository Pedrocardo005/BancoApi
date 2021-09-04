package com.banco.application.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "agencia")
public class Agencia {

	@Id
	@Column(name = "id_agencia")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "id_instituicao")
	@JsonIgnoreProperties("agencias")//nome do atributo, somente com ManyToOne
	private InstituicaoFinanceira instituicao;
	
	@OneToMany(mappedBy = "agencia")
	private List<Empregado> empregados;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public InstituicaoFinanceira getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(InstituicaoFinanceira instituicao) {
		this.instituicao = instituicao;
	}
	
	
}

























