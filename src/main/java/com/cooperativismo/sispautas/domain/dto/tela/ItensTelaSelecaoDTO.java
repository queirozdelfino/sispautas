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
public class ItensTelaSelecaoDTO {
	
	@Schema(example = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
	private String texto;
	
	@Schema(example = "http://teste.com")
	private String url;

}
