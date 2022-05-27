package com.cooperativismo.sispautas.api;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cooperativismo.sispautas.domain.dto.AssociadoDTO;
import com.cooperativismo.sispautas.domain.entity.Associado;
import com.cooperativismo.sispautas.domain.repository.AssociadoRepository;
import com.cooperativismo.sispautas.exception.dto.ResponseError;
import com.cooperativismo.sispautas.exception.message.ErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(properties = "app.baseUrl=https://localhost:8091", webEnvironment = WebEnvironment.RANDOM_PORT)
@DisplayName("Testes de Associados")
class AssociadoControllerTest {
	
	
	@Autowired
	private TestRestTemplate testClient;
	
	@Autowired
	private ObjectMapper mapper;
	
	@MockBean
    private AssociadoRepository associadoRepository;
	
	@Test
	void createAssociados200() throws Exception {
		
		given(associadoRepository.save(any(Associado.class))).willReturn(mapAssociado());
		
		AssociadoDTO associadoDTO = new AssociadoDTO("99087586086", "João dos Reis");
		
		final ResponseEntity<String> response = this.testClient.exchange(
				"/associado",
                HttpMethod.POST,
                new HttpEntity<>(associadoDTO),
                String.class);
		assertThat(response.getStatusCode(),
				is(equalTo(HttpStatus.CREATED)));
	}
	
	
	@Test
	void createAssociados400() throws Exception {
		
		AssociadoDTO associadoDTO = new AssociadoDTO(null, "João dos Reis");
		
		final ResponseEntity<String> response = this.testClient.exchange(
				"/associado",
                HttpMethod.POST,
                new HttpEntity<>(associadoDTO),
                String.class);
		assertThat(response.getStatusCode(),
				is(equalTo(HttpStatus.BAD_REQUEST)));
		ResponseError error = mapper.readValue(response.getBody(), ResponseError.class);
		assertThat(error.getErrors().get(0).getDetail(),
				is(equalTo("CPF"+ ErrorMessage.DADO_VAZIO.getMessage())));
	}
	
	@Test
	void createAssociados422() throws Exception {
		
		given(associadoRepository.findByCpf("99087586086")).willReturn(Optional.of(mapAssociado()));
		
		AssociadoDTO associadoDTO = new AssociadoDTO("99087586086", "João dos Reis");
		
		final ResponseEntity<String> response = this.testClient.exchange(
				"/associado",
                HttpMethod.POST,
                new HttpEntity<>(associadoDTO),
                String.class);
		assertThat(response.getStatusCode(),
				is(equalTo(HttpStatus.UNPROCESSABLE_ENTITY)));
		ResponseError error = mapper.readValue(response.getBody(), ResponseError.class);
		assertThat(error.getErrors().get(0).getDetail(),
				is(equalTo(ErrorMessage.CADASTO_DUPLICADO.getMessage())));
	}
	
	private Associado mapAssociado(){
		Associado associado = new Associado();
		associado.setCpf("99087586086");
		associado.setNome("João dos Reis");
		return associado;
	}


}
