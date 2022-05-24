package com.cooperativismo.sispautas.api;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import com.cooperativismo.sispautas.domain.dto.VotoDTO;
import com.cooperativismo.sispautas.domain.entity.Associado;
import com.cooperativismo.sispautas.domain.entity.Pauta;
import com.cooperativismo.sispautas.domain.entity.Voto;
import com.cooperativismo.sispautas.domain.enums.Decisao;
import com.cooperativismo.sispautas.domain.service.VotoService;
import com.cooperativismo.sispautas.exception.DomainBadRequestException;
import com.cooperativismo.sispautas.exception.DomainUnprocessableEntityException;
import com.cooperativismo.sispautas.exception.message.ErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = VotoController.class)
@ActiveProfiles("test")
@DisplayName("Tests of VotoController")
public class VotoControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	VotoService votoService;
	
	@Autowired
    private ObjectMapper objectMapper;
	
	@Test
	@DisplayName("createVotarPauta200")
	void createVotarPauta200() throws Exception {
		Associado associado = new Associado(1L,"99087586086", "João dos Reis");
		Pauta pauta = new Pauta(1L,"Teste", "Detalhe", associado, null, new ArrayList<>(), null);
		VotoDTO dto = new VotoDTO(1L, "99087586086", Decisao.SIM);
		
		given(votoService.createVoto(any(VotoDTO.class)))
			.willReturn(new Voto(1L, Decisao.SIM, associado, pauta));
		
		
		 this.mockMvc.perform(post("/voto")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(dto)))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.autor.cpf", is(dto.getCpfAssociado())));
	}
	
	@Test
	@DisplayName("createVotarPauta400")
	void createVotarPauta400() throws Exception {
		VotoDTO dto = new VotoDTO(1L, "99087586086", Decisao.SIM);
		
		given(votoService.createVoto(any(VotoDTO.class))).willThrow(new DomainBadRequestException(ErrorMessage.VALIDATION_ERROR.getMessage()));
		
		
		 this.mockMvc.perform(post("/voto")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(dto)))
		 			.andExpect(status().isBadRequest())
		 			.andExpect(jsonPath("$.errors[0].detail", is(ErrorMessage.VALIDATION_ERROR.getMessage())));
	}
	
	@Test
	@DisplayName("createVotarPauta422")
	void createVotarPauta422() throws Exception {
		VotoDTO dto = new VotoDTO(1L, "99087586086", Decisao.SIM);
		
		given(votoService.createVoto(any(VotoDTO.class))).willThrow(new DomainUnprocessableEntityException(ErrorMessage.VOTO_EXISTENTE.getMessage()));
		
		
		 this.mockMvc.perform(post("/voto")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(dto)))
		 			.andExpect(status().isUnprocessableEntity())
		 			.andExpect(jsonPath("$.errors[0].detail", is(ErrorMessage.VOTO_EXISTENTE.getMessage())));
	}

}
