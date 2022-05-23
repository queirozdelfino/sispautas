package com.cooperativismo.sispautas.domain.service;

import com.cooperativismo.sispautas.domain.dto.VotoDTO;
import com.cooperativismo.sispautas.domain.entity.Associado;
import com.cooperativismo.sispautas.domain.entity.Pauta;
import com.cooperativismo.sispautas.domain.entity.Voto;

public interface VotoService {
	
	Voto createVoto(VotoDTO votoDTO);

	Voto findVotoByAssociadoAndPauta(Associado autor, Pauta pauta);

}
