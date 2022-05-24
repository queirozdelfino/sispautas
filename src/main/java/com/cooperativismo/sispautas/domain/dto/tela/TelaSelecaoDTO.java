package com.cooperativismo.sispautas.domain.dto.tela;

import java.util.List;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TelaSelecaoDTO extends AbstractTelaDTO{
	
	@ArraySchema(schema = @Schema(implementation = ItensTelaSelecaoDTO.class))
	private List<ItensTelaSelecaoDTO> itens;

}
