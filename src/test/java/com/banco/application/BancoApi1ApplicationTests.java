package com.banco.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;

import com.banco.application.dto.ClienteDTO;
import com.banco.application.models.Agencia;
import com.banco.application.models.InstituicaoFinanceira;
import com.banco.application.service.ClienteService;
import com.banco.application.service.CryptoService;
import com.banco.application.service.InstituicaoService;

@SpringBootTest
class BancoApi1ApplicationTests {

	@Autowired
	InstituicaoService instituicaoService;
	
	@Autowired
	ClienteService clienteService;
	
	@MockBean
	CryptoService cryptoService;
	
	@BeforeEach
	void setUp() {
		when(cryptoService.getBitCoinPrice()).thenReturn(3.5);
	}
	
	@Test
	void verificarRetornoClientePorNome() {
		ClienteDTO clienteDto = clienteService.findById(123457);
		
		//só vai passar se não for nula
		assertNotNull(clienteDto.getNome());
	}
	
	@Test
	void inserirInstituicaoFinanceira() {
		
		InstituicaoFinanceira instituicao = new InstituicaoFinanceira();
		instituicao.setName("Zequinha Banco");
		InstituicaoFinanceira instituicaoRetorno = instituicaoService.save(instituicao);
		
		assertNotEquals(null, instituicaoRetorno.getIdentifier());
	}
	
	@Test
	void inserirInstituicaoFinanceiraSemNome() {
		
		InstituicaoFinanceira instituicao = new InstituicaoFinanceira();
		instituicao.setAgencias(new ArrayList<Agencia>());
		
		assertThrows(DataIntegrityViolationException.class, ()-> instituicaoService.save(instituicao) );
	}
	
	@Test
	void atualizarInstituicaoFinanceiraNaoExistente() {
		
		InstituicaoFinanceira instituicao = new InstituicaoFinanceira();
		instituicao.setIdentifier(20012032);
		
		Exception exception = assertThrows(Exception.class, ()-> instituicaoService.update(instituicao) );
		
		if("Não existe instituição com o código informado".equals(exception.getMessage())) {
			System.out.println("Teste passou");
		} else {
			System.out.println("Teste não passou");
		}
		
		assertEquals("Não existe instituição com o código informado", exception.getMessage());
	}
}




































