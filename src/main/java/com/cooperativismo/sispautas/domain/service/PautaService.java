package com.cooperativismo.sispautas.domain.service;

import java.util.List;

import com.cooperativismo.sispautas.domain.dto.PautaDTO;
import com.cooperativismo.sispautas.domain.dto.SessaoPautaDTO;
import com.cooperativismo.sispautas.domain.entity.Pauta;

public interface PautaService {
	
	Pauta createPauta(PautaDTO pautaDto);
	
	Pauta createSessao(SessaoPautaDTO sessaoPautaDTO);

	Pauta findPautaById(Long id);
	
	Pauta findDecisaoPauta(Long id);

	Pauta updatePauta(Pauta pauta);

	List<Pauta> findPautaParaVotar();
	
	void verifyPautaAberta(Pauta pauta);

	void verifyPautaNaoAberta(Pauta pauta);

	void verifyPautaEncerrada(Pauta pauta);

}
