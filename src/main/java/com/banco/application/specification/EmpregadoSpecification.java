package com.banco.application.specification;

import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.banco.application.models.Empregado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpregadoSpecification implements Specification<Empregado>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Integer cpf;
	
	String nome;
	
	String nomeAgencia;
	
	Double salario;
	
	@Override
	public Predicate toPredicate(Root<Empregado> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		ArrayList<Predicate> predicates = new ArrayList<Predicate>();
		
		if(this.cpf != null) {
			Predicate p = criteriaBuilder.equal(root.get("cpf"), cpf);
			predicates.add(p);
		}
		
		if(this.nome != null) {
			//criteriabuilder que cria as regras.
			Predicate p = criteriaBuilder.like(root.get("nome"), "%"+nome+"%");
			predicates.add(p);
		}
		
		if(this.nomeAgencia != null) {
			Predicate p = criteriaBuilder.equal(root.get("agencia").get("nome"), nomeAgencia);
			predicates.add(p);
		}
		
		if(this.salario != null) {
			Predicate p = criteriaBuilder.greaterThan(root.get("salario"), salario);
			predicates.add(p);
		}
		
		Predicate[] arrayPredicates = predicates.toArray(new Predicate[predicates.size()]);
		
		Predicate pAnd = criteriaBuilder.and(arrayPredicates);
		
		return pAnd;
	}
	
	public EmpregadoSpecification(Double salario) {
		this.setSalario(salario);
	}

}
