package com.cooperativismo.sispautas.api;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cooperativismo.sispautas.domain.entity.Associado;
import com.cooperativismo.sispautas.domain.entity.Pauta;
import com.cooperativismo.sispautas.domain.enums.Decisao;
import com.cooperativismo.sispautas.domain.repository.AssociadoRepository;
import com.cooperativismo.sispautas.domain.repository.PautaRepository;
import com.cooperativismo.sispautas.domain.repository.VotoRepository;
import com.cooperativismo.sispautas.exception.dto.ResponseError;
import com.cooperativismo.sispautas.exception.message.ErrorMessage;
import com.cooperativismo.sispautas.external.SistemaCpfApiClient;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(properties = "app.baseUrl=https://localhost:8091", webEnvironment = WebEnvironment.RANDOM_PORT)
@DisplayName("Testes de Telas")
class TelasControllerTest {
	
	@Autowired
	private TestRestTemplate testClient;
	
	@Autowired
	private ObjectMapper mapper;
	
	@MockBean
	private VotoRepository votoRepository;
	
	@MockBean
	private AssociadoRepository associadoRepository;
	
	@MockBean
	private PautaRepository pautaRepository;
	
	@MockBean
	private SistemaCpfApiClient sistemaCpfApiClient;
	
	@Test
	void getTelaNovoAssociado200() throws Exception {
		
		final ResponseEntity<String> response = this.testClient.exchange(
				"/tela/novo-associado",
                HttpMethod.GET,
                null,
                String.class);
		assertThat(response.getStatusCode(),
				is(equalTo(HttpStatus.OK)));
		
	}
	
	@Test
	void getTelaIncluirPauta200() throws Exception {
		
		final ResponseEntity<String> response = this.testClient.exchange(
				"/tela/nova-pauta",
                HttpMethod.GET,
                null,
                String.class);
		assertThat(response.getStatusCode(),
				is(equalTo(HttpStatus.OK)));
		
	}
	
	
	@Test
	void getTelaSelecionarPauta200() throws Exception {
		
		given(pautaRepository.findByDataLimite(any())).willReturn(mapListPautas());
		
		final ResponseEntity<String> response = this.testClient.exchange(
				"/tela/selecionar-pauta",
                HttpMethod.GET,
                null,
                String.class);
		assertThat(response.getStatusCode(),
				is(equalTo(HttpStatus.OK)));
		
	}
	
	
	@Test
	void getTelaAbrirSessao200() throws Exception {
		
		final long pautaId = 1L;
		
		given(pautaRepository.findById(anyLong())).willReturn(Optional.of(mapPauta())); 

		final ResponseEntity<String> response = this.testClient.exchange(
				"/tela/nova-sessao/" + pautaId,
                HttpMethod.GET,
                null,
                String.class);
		assertThat(response.getStatusCode(),
				is(equalTo(HttpStatus.OK)));
		
	}
	
	@Test
	void getTelaVotarPauta200() throws Exception {
		
		final long pautaId = 1L;
		
		given(pautaRepository.findById(anyLong())).willReturn(Optional.of(mapPautaNaoFechada())); 

		final ResponseEntity<String> response = this.testClient.exchange(
				"/tela/votar/" + pautaId,
                HttpMethod.GET,
                null,
                String.class);
		assertThat(response.getStatusCode(),
				is(equalTo(HttpStatus.OK)));
		
	}
	
	@Test
	void getTelaVotarPauta422PautaFechada() throws Exception {
		
		final long pautaId = 1L;
		ResponseError error;
		
		given(pautaRepository.findById(anyLong())).willReturn(Optional.of(mapPautaFechada())); 

		final ResponseEntity<String> response = this.testClient.exchange(
				"/tela/votar/" + pautaId,
                HttpMethod.GET,
                null,
                String.class);
		assertThat(response.getStatusCode(),
				is(equalTo(HttpStatus.UNPROCESSABLE_ENTITY)));
		error = mapper.readValue(response.getBody(), ResponseError.class);
		
		assertThat(error.getErrors().get(0).getDetail(),
				is(equalTo(ErrorMessage.PAUTA_VENCIDA.getMessage())));
		
	}
	
	@Test
	void getTelaApresentarResultado200() throws Exception {
		
		final long pautaId = 1L;
		
		given(pautaRepository.findById(anyLong())).willReturn(Optional.of(mapPautaFechada())); 

		final ResponseEntity<String> response = this.testClient.exchange(
				"/tela/resultado/" + pautaId,
                HttpMethod.GET,
                null,
                String.class);
		assertThat(response.getStatusCode(),
				is(equalTo(HttpStatus.OK)));
		
	}
	
	@Test
	void getTelaApresentarResultado422PautaAberta() throws Exception {
		
		final long pautaId = 1L;
		ResponseError error;
		
		given(pautaRepository.findById(anyLong())).willReturn(Optional.of(mapPautaNaoFechada())); 

		final ResponseEntity<String> response = this.testClient.exchange(
				"/tela/resultado/" + pautaId,
                HttpMethod.GET,
                null,
                String.class);
		assertThat(response.getStatusCode(),
				is(equalTo(HttpStatus.UNPROCESSABLE_ENTITY)));
		error = mapper.readValue(response.getBody(), ResponseError.class);
		
		assertThat(error.getErrors().get(0).getDetail(),
				is(equalTo(ErrorMessage.PAUTA_ABERTA.getMessage())));
		
	}
	
	
	private List<Pauta> mapListPautas() {
		List<Pauta> pautas = new ArrayList<>();
		pautas.add(new Pauta(1L,"Teste", "Detalhe", mapAssociado(), LocalDateTime.now().plusHours(1), new ArrayList<>(), null));
		return pautas;
	}
	
	private Associado mapAssociado(){
		return new Associado(1L,"99087586086", "João dos Reis", new ArrayList<>());
	}
	
	private Pauta mapPauta() {
		return new Pauta(1L,"Teste", "Detalhe", mapAssociado(), null, new ArrayList<>(), null);
	}
	
	private Pauta mapPautaNaoFechada() {
		return new Pauta(1L,"Teste", "Detalhe", mapAssociado(), LocalDateTime.now().plusHours(1), new ArrayList<>(), null);
	}

	private Pauta mapPautaFechada() {
		return new Pauta(1L,"Teste", "Detalhe", mapAssociado(), LocalDateTime.now().minusHours(1), new ArrayList<>(), Decisao.SIM);
	}
}
