package com.banco.application.models;

import java.net.URI;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.banco.application.until.UtilsUrl;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name="conta")
public class Conta {
	
	@Id
	@Column(name="id_conta")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column()
	private Double saldo;
	
	@Column()
	private Double saldoBitCoin;
	
	//Anotação para definir que ha muitas contas para um cliente
	//FetchType.LAZY não chama o cliente, só se você chamar.
	//FetchType.EAGER busca o cliente também.
	@ManyToOne(fetch = FetchType.EAGER)//definindo
	@JoinColumn(name = "cpf")//chave é o cpf
	//Anotação para que a propriedade contas de CLIENTE seja ignorada
	@JsonIgnoreProperties("contas")
	private Cliente cliente;
	
	@Transient
	private String uriClient;
	
	public URI getUriCliente() {
		return new UtilsUrl().getUri("/v2/clientes", this.getCliente().getCpf());
	}
	
	@ManyToOne
	@JoinColumn(name="id_instituicao")
	@JsonIgnoreProperties("contas")
	private InstituicaoFinanceira instituicao;
	
	
	public Conta() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public InstituicaoFinanceira getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(InstituicaoFinanceira instituicao) {
		this.instituicao = instituicao;
	}

	public Double getSaldoBitCoin() {
		return saldoBitCoin;
	}

	public void setSaldoBitCoin(Double saldoBitCoin) {
		this.saldoBitCoin = saldoBitCoin;
	}

	
}



























