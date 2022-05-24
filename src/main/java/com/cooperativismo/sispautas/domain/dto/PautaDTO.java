package com.cooperativismo.sispautas.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PautaDTO {
	
	@Schema(example = "99087586086")
	private String cpfAutor;
	
	@Schema(example = "0")	
	private Long id;
	
	@Schema(example = "Comprar outra cooperativa menor?")
	private String titulo;
	
	@Schema(example = "Vendo que o orçamento está bom poderemos comprar outra cooperativa e aumentar nossa produção.")
	private String detalhes;
}
