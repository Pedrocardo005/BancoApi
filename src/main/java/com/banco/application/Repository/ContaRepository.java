package com.banco.application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banco.application.models.Conta;
//Integer tipo do principal de conta
public interface ContaRepository extends JpaRepository<Conta, Integer>{

}
