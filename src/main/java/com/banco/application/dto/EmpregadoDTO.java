package com.banco.application.dto;

import java.io.Serializable;

import com.banco.application.models.Dependente;
import com.banco.application.models.Empregado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpregadoDTO implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 6375830332096667059L;

	private Integer cpf;
	
	private String nome;
	
	private String nomeAgencia;
	
	private String nomeInstituicao;
	
	private String nomesDependentes;
	
	private Double salario;
	
	public EmpregadoDTO createEmpregadoDto(Empregado empregado) {
		EmpregadoDTO empregadoDto = new EmpregadoDTO();
		empregadoDto.setCpf(empregado.getCpf());
		empregadoDto.setNome(empregado.getNome());
		empregadoDto.setNomeAgencia(empregado.getAgencia() != null ? empregado.getAgencia().getNome() : "");
		empregadoDto.setSalario(empregado.getSalario() != null ? empregado.getSalario() : 0D);
		
		String nomeDependentesTemp = "";
		
		for(Dependente dependente : empregado.getDependentes()) {
			
			nomeDependentesTemp = nomeDependentesTemp.concat(dependente.getNome());
			nomeDependentesTemp = nomeDependentesTemp.concat(", ");
		}
		
		empregadoDto.setNomesDependentes(nomeDependentesTemp);
		return empregadoDto;
	}
}
