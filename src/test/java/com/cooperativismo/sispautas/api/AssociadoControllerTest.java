package com.cooperativismo.sispautas.api;



import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import com.cooperativismo.sispautas.domain.dto.AssociadoDTO;
import com.cooperativismo.sispautas.domain.entity.Associado;
import com.cooperativismo.sispautas.domain.service.AssociadoService;
import com.cooperativismo.sispautas.exception.DomainBadRequestException;
import com.cooperativismo.sispautas.exception.DomainUnprocessableEntityException;
import com.cooperativismo.sispautas.exception.message.ErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(controllers = AssociadoController.class)
@ActiveProfiles("test")
@DisplayName("Tests of AssociadoController")
class AssociadoControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	AssociadoService service;
	
	@Autowired
    private ObjectMapper objectMapper;
	
	@Test
	@DisplayName("createAssociados201")
	void createAssociados201() throws Exception {
		
		given(service.createAssociado(any(AssociadoDTO.class))).willReturn(new Associado(1L, "99087586086", "João dos Reis"));
		
		AssociadoDTO associadoDTO = new AssociadoDTO("99087586086", "João dos Reis");
		
		 this.mockMvc.perform(post("/associado")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(associadoDTO)))
	                .andExpect(status().isCreated())
	                .andExpect(jsonPath("$.cpf", is(associadoDTO.getCpf())))
	                .andExpect(jsonPath("$.nome", is(associadoDTO.getNome())));
	}
	
	
	@Test
	@DisplayName("createAssociados400")
	void createAssociados400() throws Exception {
		
		given(service.createAssociado(any(AssociadoDTO.class))).willThrow(new DomainBadRequestException(ErrorMessage.VALIDATION_ERROR.getMessage()));
		
		AssociadoDTO associadoDTO = new AssociadoDTO(null, null);
		
		 this.mockMvc.perform(post("/associado")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(associadoDTO)))
	                .andExpect(status().isBadRequest())
		 			.andExpect(jsonPath("$.errors[0].detail", is(ErrorMessage.VALIDATION_ERROR.getMessage())));
	}
	
	@Test
	@DisplayName("createAssociados422")
	void createAssociados422() throws Exception {
		
		given(service.createAssociado(any(AssociadoDTO.class))).willThrow(new DomainUnprocessableEntityException(ErrorMessage.CADASTO_DUPLICADO.getMessage()));
		
		AssociadoDTO associadoDTO = new AssociadoDTO("99087586086", "João dos Reis");
		
		 this.mockMvc.perform(post("/associado")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(associadoDTO)))
	                .andExpect(status().isUnprocessableEntity())
		 			.andExpect(jsonPath("$.errors[0].detail", is(ErrorMessage.CADASTO_DUPLICADO.getMessage())));
	}
	
	
	
}
