package com.cooperativismo.sispautas.api;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.io.IOException;
import org.json.JSONException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cooperativismo.sispautas.domain.dto.AssociadoDTO;

@SpringBootTest(properties = "app.baseUrl=https://localhost:8091", webEnvironment = WebEnvironment.DEFINED_PORT)
@DisplayName("Tests of AssociadoController")
class AssociadoControllerTest {
	
	@Autowired
	private TestRestTemplate testClient; 
	
	@Test
	@DisplayName("postAssociado201")
	void getAssociado201() throws JSONException, IOException {
		AssociadoDTO associadoDto = new AssociadoDTO();
		
		associadoDto.setCpf("");
		associadoDto.setNome("João dos Reis");
		
		final ResponseEntity<String> response = this.testClient.exchange(
				"/associado",
				HttpMethod.POST,
				new HttpEntity<>(associadoDto),
				String.class);
		
		assertThat(response.getStatusCode(),
				is((HttpStatus.UNPROCESSABLE_ENTITY)));
	}

}
