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
public class BodyDTO {
	
	@Schema(example = "valor1")
	private String campo1;
	
	@Schema(example = "123")
	private String campo2;

}
