package com.banco.application.specification;

import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.banco.application.models.Agencia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//implementa uma classe.
public class AgenciaSpecification implements Specification<Agencia>{

	private Integer id;
	
	private String nome;
	
	@Override
	public Predicate toPredicate(Root<Agencia> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		ArrayList<Predicate> predicates = new ArrayList<Predicate>();
		
		if(id != null) {
			Predicate p = criteriaBuilder.equal(root.get("id"), id);
			predicates.add(p);
		}
		if(nome != null) {
			Predicate p = criteriaBuilder.equal(root.get("nome"), nome);
			predicates.add(p);
		}
		Predicate[] arrayPredicates = predicates.toArray(new Predicate[predicates.size()]);
		
		return criteriaBuilder.and(arrayPredicates);
	}

}
