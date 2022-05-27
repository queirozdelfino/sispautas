package com.cooperativismo.sispautas.api;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

import com.cooperativismo.sispautas.domain.dto.PautaDTO;
import com.cooperativismo.sispautas.domain.dto.SessaoPautaDTO;
import com.cooperativismo.sispautas.domain.entity.Associado;
import com.cooperativismo.sispautas.domain.entity.Pauta;
import com.cooperativismo.sispautas.domain.enums.Decisao;
import com.cooperativismo.sispautas.domain.repository.AssociadoRepository;
import com.cooperativismo.sispautas.domain.repository.PautaRepository;
import com.cooperativismo.sispautas.domain.repository.VotoRepository;
import com.cooperativismo.sispautas.exception.dto.ResponseError;
import com.cooperativismo.sispautas.exception.message.ErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(properties = "app.baseUrl=https://localhost:8091", webEnvironment = WebEnvironment.RANDOM_PORT)
@DisplayName("Testes de Pautas")
class PautaControllerTest {
	
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
	
	@Test
	void postpauta201() throws Exception {
		
		final PautaDTO dto = new PautaDTO(1L, "99087586086", "Teste", "Detalhe");
		given(associadoRepository.findByCpf(anyString())).willReturn(Optional.of(mapAssociado()));
		given(pautaRepository.save(any(Pauta.class)))
		.willReturn(new Pauta(1L,"Teste", "Detalhe", mapAssociado(), null, new ArrayList<>(), null));
		
		final ResponseEntity<String> response = this.testClient.exchange(
				"/pauta",
                HttpMethod.POST,
                new HttpEntity<>(dto),
                String.class);
		assertThat(response.getStatusCode(),
				is(equalTo(HttpStatus.CREATED)));
	}
	
	
	@Test
	void postSessaoPauta200() throws Exception {

		final SessaoPautaDTO dto = new SessaoPautaDTO(1L, "99087586086", null);
		
		given(pautaRepository.findById(anyLong())).willReturn(Optional.of(mapPauta()));
		given(associadoRepository.findByCpf(anyString())).willReturn(Optional.of(mapAssociado()));
		
		final ResponseEntity<String> response = this.testClient.exchange(
				"/pauta/sessao",
                HttpMethod.POST,
                new HttpEntity<>(dto),
                String.class);
		assertThat(response.getStatusCode(),
				is(equalTo(HttpStatus.OK)));
	}
	
	
	@Test
	void postSessaoPauta404SemCpf() throws Exception {

		final SessaoPautaDTO dto = new SessaoPautaDTO(1L, "99087586086", null);
		ResponseError error;
		
		given(pautaRepository.findById(anyLong())).willReturn(Optional.of(mapPauta()));

		
		final ResponseEntity<String> response = this.testClient.exchange(
				"/pauta/sessao",
                HttpMethod.POST,
                new HttpEntity<>(dto),
                String.class);
		assertThat(response.getStatusCode(),
				is(equalTo(HttpStatus.NOT_FOUND)));
		
		error = mapper.readValue(response.getBody(), ResponseError.class);
		
		assertThat(error.getErrors().get(0).getDetail(),
				is(equalTo(ErrorMessage.CPF_NAO_ENCONTRADO.getMessage())));
	}
	
	@Test
	void getDecisaoPauta200() throws Exception {

		final SessaoPautaDTO dto = new SessaoPautaDTO(1L, "99087586086", null);
		final long pautaId = 1L;
		given(pautaRepository.findById(anyLong())).willReturn(Optional.of(mapPautaFechada()));

		
		final ResponseEntity<String> response = this.testClient.exchange(
				"/pauta/"+ pautaId,
                HttpMethod.GET,
                new HttpEntity<>(dto),
                String.class);
		assertThat(response.getStatusCode(),
				is(equalTo(HttpStatus.OK)));
		
	}
	
	@Test
	void getDecisaoPauta200SaveRepository() throws Exception {

		final SessaoPautaDTO dto = new SessaoPautaDTO(1L, "99087586086", null);
		final long pautaId = 1L;
		given(pautaRepository.findById(anyLong())).willReturn(Optional.ofNullable(mapPautaFechadaSemDecisao()));
		given(pautaRepository.save(any(Pauta.class)))
		.willReturn(new Pauta(1L,"Teste", "Detalhe", mapAssociado(), LocalDateTime.now().minusHours(1),
				new ArrayList<>(), Decisao.SIM));
		
		final ResponseEntity<String> response = this.testClient.exchange(
				"/pauta/"+ pautaId,
                HttpMethod.GET,
                new HttpEntity<>(dto),
                String.class);
		assertThat(response.getStatusCode(),
				is(equalTo(HttpStatus.OK)));
		
	}
	
	
	@Test
	void getDecisaoPauta422NaoAberta() throws Exception {

		final SessaoPautaDTO dto = new SessaoPautaDTO(1L, "99087586086", null);
		final long pautaId = 1L;
		ResponseError error;
		
		given(pautaRepository.findById(anyLong())).willReturn(Optional.of(mapPauta()));

		
		final ResponseEntity<String> response = this.testClient.exchange(
				"/pauta/"+ pautaId,
                HttpMethod.GET,
                new HttpEntity<>(dto),
                String.class);
		assertThat(response.getStatusCode(),
				is(equalTo(HttpStatus.UNPROCESSABLE_ENTITY)));
		
		error = mapper.readValue(response.getBody(), ResponseError.class);
		
		assertThat(error.getErrors().get(0).getDetail(),
				is(equalTo(ErrorMessage.PAUTA_NAO_INICIADA.getMessage())));
		
	}
	
	@Test
	void getDecisaoPauta422NaoFechada() throws Exception {

		final SessaoPautaDTO dto = new SessaoPautaDTO(1L, "99087586086", null);
		final long pautaId = 1L;
		ResponseError error;
		
		given(pautaRepository.findById(anyLong())).willReturn(Optional.of(mapPautaNaoFechada()));

		
		final ResponseEntity<String> response = this.testClient.exchange(
				"/pauta/"+ pautaId,
                HttpMethod.GET,
                new HttpEntity<>(dto),
                String.class);
		assertThat(response.getStatusCode(),
				is(equalTo(HttpStatus.UNPROCESSABLE_ENTITY)));
		
		error = mapper.readValue(response.getBody(), ResponseError.class);
		
		assertThat(error.getErrors().get(0).getDetail(),
				is(equalTo(ErrorMessage.PAUTA_ABERTA.getMessage())));
		
	}
	
	
	
	
	
	private Associado mapAssociado(){
		return new Associado(1L,"99087586086", "João dos Reis", new ArrayList<>());
	}
	
	private Pauta mapPauta() {
		return new Pauta(1L,"Teste", "Detalhe", mapAssociado(), null, new ArrayList<>(), null);
	}
	
	private Pauta mapPautaFechada() {
		return new Pauta(1L,"Teste", "Detalhe", mapAssociado(), LocalDateTime.now().minusHours(1), new ArrayList<>(), Decisao.SIM);
	}
	
	private Pauta mapPautaFechadaSemDecisao() {
		return new Pauta(1L,"Teste", "Detalhe", mapAssociado(), LocalDateTime.now().minusHours(1), new ArrayList<>(), null);
	}
	
	private Pauta mapPautaNaoFechada() {
		return new Pauta(1L,"Teste", "Detalhe", mapAssociado(), LocalDateTime.now().plusHours(1), new ArrayList<>(), null);
	}

}
