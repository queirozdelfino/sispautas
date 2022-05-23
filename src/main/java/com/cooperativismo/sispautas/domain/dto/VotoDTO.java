package com.cooperativismo.sispautas.domain.dto;

import com.cooperativismo.sispautas.domain.enums.Decisao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VotoDTO {
	
	private Long idPauta;
	
	private Long cpfAssociado;
	
	private Decisao decisao;

}
