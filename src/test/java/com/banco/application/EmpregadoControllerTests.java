package com.banco.application;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.banco.application.dto.EmpregadoDTO;

@SpringBootTest(classes = BancoApi1Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmpregadoControllerTests {

	@Autowired
	TestRestTemplate testRestTemplate; //já usamos uma classe parecida com essa.
	
	public ResponseEntity<EmpregadoDTO> getObjectEmpregadoDTO(String url) {
		return testRestTemplate.exchange(url, 
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<EmpregadoDTO>() {
				});
	}
	
	//@Test
	public void testaRetornoEmpregado() {
		EmpregadoDTO empregadoDTO = this.getObjectEmpregadoDTO("/v1/empregados/33").getBody();
		assertNotNull(empregadoDTO.getNome());
	}
}
