package com.cooperativismo.sispautas.domain.dto.tela;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BotaoDTO {
	
	@Schema(example = "Ação 1")
	private String texto;
	
	@Schema(example = "http://seudominio.com/acao1")
	private String url;
	
	@Schema(implementation = BodyDTO.class)
	private BodyDTO body;

}
