package com.banco.application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.banco.application.models.Dependente;

public interface DependenteRepository extends JpaRepository<Dependente, Integer>, JpaSpecificationExecutor<Dependente>{

}
