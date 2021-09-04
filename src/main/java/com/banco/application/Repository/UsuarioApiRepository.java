package com.banco.application.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banco.application.models.UsuarioApi;

public interface UsuarioApiRepository extends JpaRepository<UsuarioApi, Integer>{

	Optional<UsuarioApi> findByLogin(String login);
}
