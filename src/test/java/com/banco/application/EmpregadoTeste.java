package com.banco.application;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.banco.application.dto.EmpregadoDTO;
import com.banco.application.service.EmpregadoService;

@SpringBootTest
public class EmpregadoTeste {
	
	@Autowired
	EmpregadoService empregadoService;

	@Test
	public void verificarEmpregadoPorId() {
		EmpregadoDTO empregadoDto = empregadoService.findById(123);
		assertNotNull(empregadoDto.getNome());
	}
}
