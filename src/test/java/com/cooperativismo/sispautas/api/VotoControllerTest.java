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

import com.cooperativismo.sispautas.domain.dto.VotoDTO;
import com.cooperativismo.sispautas.domain.entity.Associado;
import com.cooperativismo.sispautas.domain.entity.Pauta;
import com.cooperativismo.sispautas.domain.entity.Voto;
import com.cooperativismo.sispautas.domain.enums.Decisao;
import com.cooperativismo.sispautas.domain.repository.AssociadoRepository;
import com.cooperativismo.sispautas.domain.repository.PautaRepository;
import com.cooperativismo.sispautas.domain.repository.VotoRepository;
import com.cooperativismo.sispautas.exception.dto.ResponseError;
import com.cooperativismo.sispautas.exception.message.ErrorMessage;
import com.cooperativismo.sispautas.external.SistemaCpfApiClient;
import com.cooperativismo.sispautas.external.dto.ResponseCpfExternal;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(properties = "app.baseUrl=https://localhost:8091", webEnvironment = WebEnvironment.RANDOM_PORT)
@DisplayName("Testes de Votos")
class VotoControllerTest {
	
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
	
	private Associado associado = mapAssociado();
	
	private Pauta pauta = mapPautaSessao(this.associado);
	
	@Test
	void postVotarPauta200() throws Exception {
		VotoDTO dto = new VotoDTO(this.pauta.getId(), this.associado.getCpf(), Decisao.SIM);
	
		given(associadoRepository.findById(anyLong())).willReturn(Optional.of(this.associado));
		given(pautaRepository.findById(anyLong())).willReturn(Optional.of(this.pauta));
		given(associadoRepository.findByCpf(anyString())).willReturn(Optional.of(this.associado));
		given(votoRepository.findTopByAutorIdAndPautaId(anyLong(), anyLong())).willReturn(Optional.ofNullable(null));
		given(sistemaCpfApiClient.getStatusVoteCpf(anyString())).willReturn(new ResponseEntity<ResponseCpfExternal>(mapResponseCpfExternal(), HttpStatus.OK));
		given(votoRepository.save(any(Voto.class))).willReturn(mapVoto());
		
		
		final ResponseEntity<String> response = this.testClient.exchange(
				"/voto",
                HttpMethod.POST,
                new HttpEntity<>(dto),
                String.class);
		assertThat(response.getStatusCode(),
				is(equalTo(HttpStatus.OK)));
	}
	
	@Test
	void postVotarPauta422SemPermissao() throws Exception {
		VotoDTO dto = new VotoDTO(this.pauta.getId(), this.associado.getCpf(), Decisao.SIM);
		ResponseError error;
		
		given(associadoRepository.findById(anyLong())).willReturn(Optional.of(this.associado));
		given(pautaRepository.findById(anyLong())).willReturn(Optional.of(this.pauta));
		given(associadoRepository.findByCpf(anyString())).willReturn(Optional.of(this.associado));
		given(votoRepository.findTopByAutorIdAndPautaId(anyLong(), anyLong())).willReturn(Optional.ofNullable(null));
		given(sistemaCpfApiClient.getStatusVoteCpf(anyString())).willReturn(new ResponseEntity<ResponseCpfExternal>(mapResponseCpfExternalUnable(), HttpStatus.OK));
		given(votoRepository.save(any(Voto.class))).willReturn(mapVoto());
		
		
		final ResponseEntity<String> response = this.testClient.exchange(
				"/voto",
                HttpMethod.POST,
                new HttpEntity<>(dto),
                String.class);
		assertThat(response.getStatusCode(),
				is(equalTo(HttpStatus.UNPROCESSABLE_ENTITY)));
		
		error = mapper.readValue(response.getBody(), ResponseError.class);
		
		assertThat(error.getErrors().get(0).getDetail(),
				is(equalTo(ErrorMessage.CPF_NAO_AUTORIZADO.getMessage())));
		
	}
	
	
	@Test
	void postVotarPauta422VotoExistente() throws Exception {
		VotoDTO dto = new VotoDTO(this.pauta.getId(), this.associado.getCpf(), Decisao.SIM);
		ResponseError error;
		
		given(associadoRepository.findById(anyLong())).willReturn(Optional.of(this.associado));
		given(pautaRepository.findById(anyLong())).willReturn(Optional.of(this.pauta));
		given(associadoRepository.findByCpf(anyString())).willReturn(Optional.of(this.associado));
		given(votoRepository.findTopByAutorIdAndPautaId(anyLong(), anyLong())).willReturn(Optional.of(new Voto()));
		
		final ResponseEntity<String> response = this.testClient.exchange(
				"/voto",
                HttpMethod.POST,
                new HttpEntity<>(dto),
                String.class);
		assertThat(response.getStatusCode(),
				is(equalTo(HttpStatus.UNPROCESSABLE_ENTITY)));
		
		error = mapper.readValue(response.getBody(), ResponseError.class);
		
		assertThat(error.getErrors().get(0).getDetail(),
				is(equalTo(ErrorMessage.VOTO_EXISTENTE.getMessage())));
		
	}
	
	@Test
	void postVotarPauta422SessaoFechada() throws Exception {
		VotoDTO dto = new VotoDTO(this.pauta.getId(), this.associado.getCpf(), Decisao.SIM);
		ResponseError error;
		
		given(associadoRepository.findById(anyLong())).willReturn(Optional.of(this.associado));
		given(pautaRepository.findById(anyLong())).willReturn(Optional.of(mapPautaSessaoFechada(associado)));
		given(associadoRepository.findByCpf(anyString())).willReturn(Optional.of(this.associado));
		given(votoRepository.findTopByAutorIdAndPautaId(anyLong(), anyLong())).willReturn(Optional.of(new Voto()));
		
		final ResponseEntity<String> response = this.testClient.exchange(
				"/voto",
                HttpMethod.POST,
                new HttpEntity<>(dto),
                String.class);
		assertThat(response.getStatusCode(),
				is(equalTo(HttpStatus.UNPROCESSABLE_ENTITY)));
		
		error = mapper.readValue(response.getBody(), ResponseError.class);
		
		assertThat(error.getErrors().get(0).getDetail(),
				is(equalTo(ErrorMessage.PAUTA_VENCIDA.getMessage())));
		
	}
	
	private Associado mapAssociado(){
		return new Associado(1L,"99087586086", "João dos Reis", new ArrayList<>());
	}
	
	private Pauta mapPautaSessao(Associado associado) {
		return new Pauta(1L,"Teste", "Detalhe", associado, LocalDateTime.now().plusMinutes(5), new ArrayList<>(), null);
	}
	
	private Pauta mapPautaSessaoFechada(Associado associado) {
		return new Pauta(1L,"Teste", "Detalhe", associado, LocalDateTime.now().minusMinutes(5), new ArrayList<>(), null);
	}
	private ResponseCpfExternal mapResponseCpfExternal() {
		return new ResponseCpfExternal("ABLE_TO_VOTE");
	}
	
	private ResponseCpfExternal mapResponseCpfExternalUnable() {
		return new ResponseCpfExternal("UNABLE_TO_VOTE");
	}
	
	private Voto mapVoto() {
		return new Voto(1L, Decisao.SIM, mapAssociado(), mapPautaSessao(mapAssociado()));
	}

	

}
