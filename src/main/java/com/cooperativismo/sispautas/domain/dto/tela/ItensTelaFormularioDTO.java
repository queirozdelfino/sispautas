package com.cooperativismo.sispautas.domain.dto.tela;

import com.cooperativismo.sispautas.domain.enums.TipoInput;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItensTelaFormularioDTO {
	
	
	@Schema(example = "INPUT_TEXTO")
	private TipoInput tipo;
	
	@Schema(example = "idCampoTexto")
	private String id;
	
	@Schema(example = "Campo de texto")
	private String titulo;
	
	@Schema(example = "Texto")
	private String valor;
	
	@Schema(example = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
	private String texto;

}
