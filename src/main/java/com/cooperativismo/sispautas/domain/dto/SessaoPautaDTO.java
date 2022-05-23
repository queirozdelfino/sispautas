package com.cooperativismo.sispautas.domain.dto;

import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessaoPautaDTO {
	
	@Schema(example = "1")
	private Long idPauta;
	
	@Schema(example = "99087586086")
	private String cpfAutor;

	@Schema(example = "2022-10-15T14:00:00Z")
	private LocalDateTime dataLimite;
	

}
