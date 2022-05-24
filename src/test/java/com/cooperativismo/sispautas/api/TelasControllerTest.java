package com.cooperativismo.sispautas.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import com.cooperativismo.sispautas.domain.dto.tela.BotaoDTO;
import com.cooperativismo.sispautas.domain.dto.tela.TelaFormularioDTO;
import com.cooperativismo.sispautas.domain.dto.tela.TelaSelecaoDTO;
import com.cooperativismo.sispautas.domain.service.TelasService;

@WebMvcTest(controllers = TelasController.class)
@ActiveProfiles("test")
@DisplayName("Tests of TelasController")
class TelasControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	TelasService telasService;
	
	
	@Test
    @DisplayName("findTelaIncluirAssociado")
    void findTelaIncluirAssociado() throws Exception {
        
		TelaFormularioDTO tela = new TelaFormularioDTO(new ArrayList<>(), new BotaoDTO(), new BotaoDTO());

        given(telasService.getTelaIncluirAssociado()).willReturn(tela);

        final TelaFormularioDTO expected = telasService.getTelaIncluirAssociado();

        assertThat(expected).isNotNull();

    }
	
	@Test
    @DisplayName("findTelaIncluirPauta")
    void findTelaIncluirPauta() throws Exception {
        
		TelaFormularioDTO tela = new TelaFormularioDTO(new ArrayList<>(), new BotaoDTO(), new BotaoDTO());

        given(telasService.getTelaIncluirPauta()).willReturn(tela);

        final TelaFormularioDTO expected = telasService.getTelaIncluirPauta();

        assertThat(expected).isNotNull();

    }
	
	@Test
    @DisplayName("findTelaSelecionarPauta")
    void findTelaSelecionarPauta() throws Exception {
        
		TelaSelecaoDTO tela = new TelaSelecaoDTO(new ArrayList<>());

        given(telasService.getTelaSelecionarPauta()).willReturn(tela);

        final TelaSelecaoDTO expected = telasService.getTelaSelecionarPauta();

        assertThat(expected).isNotNull();

    }
	

	@Test
    @DisplayName("findTelaAbrirSessao")
    void findTelaAbrirSessao() throws Exception {
        Long id = 1L;
		TelaFormularioDTO tela = new TelaFormularioDTO(new ArrayList<>(), new BotaoDTO(), new BotaoDTO());

        given(telasService.getTelaAbrirSessao(id)).willReturn(tela);

        final TelaFormularioDTO expected = telasService.getTelaAbrirSessao(id);

        assertThat(expected).isNotNull();

    }
	
	@Test
    @DisplayName("findTelaVotarPauta")
    void findTelaVotarPauta() throws Exception {
        Long id = 1L;
		TelaFormularioDTO tela = new TelaFormularioDTO(new ArrayList<>(), new BotaoDTO(), new BotaoDTO());

        given(telasService.getTelaVotarPauta(id)).willReturn(tela);

        final TelaFormularioDTO expected = telasService.getTelaVotarPauta(id);

        assertThat(expected).isNotNull();

    }
	
	@Test
    @DisplayName("findTelaApresentarResultadoPauta")
    void findTelaApresentarResultadoPauta() throws Exception {
        Long id = 1L;
		TelaFormularioDTO tela = new TelaFormularioDTO(new ArrayList<>(), new BotaoDTO(), new BotaoDTO());

        given(telasService.getTelaApresentarResultadoPauta(id)).willReturn(tela);

        final TelaFormularioDTO expected = telasService.getTelaApresentarResultadoPauta(id);

        assertThat(expected).isNotNull();

    }
	
	
	
}
