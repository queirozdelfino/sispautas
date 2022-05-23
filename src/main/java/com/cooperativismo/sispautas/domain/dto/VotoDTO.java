package com.cooperativismo.sispautas.domain.dto;

import com.cooperativismo.sispautas.domain.enums.Decisao;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VotoDTO {
	
	@Schema(example = "1")
	private Long idPauta;
	
	@Schema(example = "99087586086")
	private String cpfAssociado;
	
	private Decisao decisao;

}
