package com.cooperativismo.sispautas.domain.dto.tela;

import com.cooperativismo.sispautas.domain.enums.TipoTela;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractTelaDTO {
	
	@Schema(example = "FORMUALRIO")
	private TipoTela tipo;
	
	@Schema(example = "TITULO TELA")
	private String titulo;
	

}
