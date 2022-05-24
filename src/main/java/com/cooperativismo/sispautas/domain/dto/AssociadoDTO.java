package com.cooperativismo.sispautas.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 *DTO responsável por manter Associado na cooperativa.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssociadoDTO {
	
	@Schema(example = "99087586086")
	private String cpf;
	
	@Schema(example = "João dos Reis")
	private String nome;
	
}
