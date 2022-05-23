package com.cooperativismo.sispautas.domain.service;

import com.cooperativismo.sispautas.domain.dto.AssociadoDTO;
import com.cooperativismo.sispautas.domain.entity.Associado;

public interface AssociadoService {
	
	Associado createAssociado(AssociadoDTO associadoDto);
	
	Associado findAssociadoByCpf(String cpf);

}
