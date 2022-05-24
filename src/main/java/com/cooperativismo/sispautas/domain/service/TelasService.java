package com.cooperativismo.sispautas.domain.service;

import com.cooperativismo.sispautas.domain.dto.tela.TelaFormularioDTO;
import com.cooperativismo.sispautas.domain.dto.tela.TelaSelecaoDTO;

public interface TelasService {
	
	TelaFormularioDTO getTelaIncluirAssociado();
	
	TelaFormularioDTO getTelaIncluirPauta();
	
	TelaSelecaoDTO getTelaSelecionarPauta();

	TelaFormularioDTO getTelaAbrirSessao(Long idPauta);
	
	TelaFormularioDTO getTelaVotarPauta(Long idPauta);
	
	TelaFormularioDTO getTelaApresentarResultadoPauta(Long idPauta);

}
