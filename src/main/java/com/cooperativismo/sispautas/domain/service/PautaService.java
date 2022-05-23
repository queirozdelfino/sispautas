package com.cooperativismo.sispautas.domain.service;

import com.cooperativismo.sispautas.domain.dto.PautaDTO;
import com.cooperativismo.sispautas.domain.entity.Pauta;

public interface PautaService {
	
	Pauta createPauta(PautaDTO pautaDto);

}
