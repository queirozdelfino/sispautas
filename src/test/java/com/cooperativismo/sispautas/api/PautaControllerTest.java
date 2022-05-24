package com.cooperativismo.sispautas.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import com.cooperativismo.sispautas.domain.dto.PautaDTO;
import com.cooperativismo.sispautas.domain.dto.SessaoPautaDTO;
import com.cooperativismo.sispautas.domain.entity.Associado;
import com.cooperativismo.sispautas.domain.entity.Pauta;
import com.cooperativismo.sispautas.domain.enums.Decisao;
import com.cooperativismo.sispautas.domain.service.PautaService;
import com.cooperativismo.sispautas.exception.DomainBadRequestException;
import com.cooperativismo.sispautas.exception.DomainUnprocessableEntityException;
import com.cooperativismo.sispautas.exception.message.ErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = PautaController.class)
@ActiveProfiles("test")
@DisplayName("Tests of PautaController")
class PautaControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	PautaService pautaService;
	
	@Autowired
    private ObjectMapper objectMapper;
	
	
	@Test
	@DisplayName("createPauta200")
	void createPauta200() throws Exception {
		final Associado associado = new Associado(1L,"99087586086", "João dos Reis");
		final PautaDTO dto = new PautaDTO(1L, "99087586086", "Teste", "Detalhe");
		
		given(pautaService.createPauta(any(PautaDTO.class)))
			.willReturn(new Pauta(1L,"Teste", "Detalhe", associado, null, new ArrayList<>(), null));
		
		
		 this.mockMvc.perform(post("/pauta")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(dto)))
	                .andExpect(status().isCreated())
	                .andExpect(jsonPath("$.autor.cpf", is(dto.getCpfAutor())));
	}
	
	@Test
	@DisplayName("createPauta400")
	void createPauta400() throws Exception {
		final PautaDTO dto = new PautaDTO(1L, "99087586086", "Teste", "Detalhe");
		
		given(pautaService.createPauta(any(PautaDTO.class)))
			.willThrow(new DomainBadRequestException(ErrorMessage.VALIDATION_ERROR.getMessage()));
		
		
		 this.mockMvc.perform(post("/pauta")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(dto)))
		 			.andExpect(status().isBadRequest())
		 			.andExpect(jsonPath("$.errors[0].detail", is(ErrorMessage.VALIDATION_ERROR.getMessage())));
	}
	
	@Test
	@DisplayName("createSessaoPauta200")
	void createSessaoPauta200() throws Exception {
		final Associado associado = new Associado(1L,"99087586086", "João dos Reis");
		final PautaDTO dto = new PautaDTO(1L, "99087586086", "Teste", "Detalhe");
		
		given(pautaService.createSessao(any(SessaoPautaDTO.class)))
			.willReturn(new Pauta(1L,"Teste", "Detalhe", associado, LocalDateTime.now(), new ArrayList<>(), null));
		
		
		 this.mockMvc.perform(post("/pauta")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(dto)))
	                .andExpect(status().isCreated())
	                .andExpect(jsonPath("$.autor.cpf", is(dto.getCpfAutor())));
	}
	
	@Test
	@DisplayName("createSessaoPauta422")
	void createSessaoPauta422() throws Exception {
		final PautaDTO dto = new PautaDTO(1L, "99087586086", "Teste", "Detalhe");
		
		given(pautaService.createSessao(any(SessaoPautaDTO.class)))
			.willThrow(new DomainUnprocessableEntityException(ErrorMessage.PAUTA_ABERTA.getMessage()));
		
		
		 this.mockMvc.perform(post("/pauta")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(dto)))
		 			.andExpect(status().isBadRequest())
		 			.andExpect(jsonPath("$.errors[0].detail", is(ErrorMessage.PAUTA_ABERTA.getMessage())));
	}
	
    @Test
    @DisplayName("findSessaoPauta200")
    void findSessaoPauta200() throws Exception {
        final Long id = 1L;
        final Associado associado = new Associado(1L,"99087586086", "João dos Reis");
        final Pauta pauta = new Pauta(1L,"Teste", "Detalhe", associado, LocalDateTime.now(), new ArrayList<>(), Decisao.SIM);

        given(pautaService.findDecisaoPauta(id)).willReturn(pauta);

        final Pauta expected = pautaService.findDecisaoPauta(id);

        assertThat(expected).isNotNull();

    }


}
