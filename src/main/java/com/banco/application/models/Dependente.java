package com.banco.application.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "dependente")
public class Dependente {

	@Id
	@Column(name = "id_dependente")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String nome;
	
	@Lob
	@Column
	private Byte[] foto; 
	
	//Opcional colocar, pois só com Empregado já dá e exibe menos.
	@ManyToMany(mappedBy = "dependentes")
	@JsonIgnoreProperties("dependentes")
	private List<Empregado> empregados;
}
