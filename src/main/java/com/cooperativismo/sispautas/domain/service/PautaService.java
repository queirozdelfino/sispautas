package com.cooperativismo.sispautas.domain.service;

import com.cooperativismo.sispautas.domain.dto.PautaDTO;
import com.cooperativismo.sispautas.domain.dto.SessaoPautaDTO;
import com.cooperativismo.sispautas.domain.entity.Pauta;

public interface PautaService {
	
	Pauta createPauta(PautaDTO pautaDto);
	
	Pauta createSessao(SessaoPautaDTO sessaoPautaDTO);

	Pauta findPautaById(Long id);
	
	Pauta findDecisaoPauta(Long id);

	Pauta createPauta(Pauta pauta);

}
