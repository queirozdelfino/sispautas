package com.cooperativismo.sispautas.exception.dto;

import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseErrorInfo implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Schema(example = "99999999999")
	private String code;
	
	@Schema(example = "Título do Erro")
    private String title;
	
	@Schema(example = "Detalhes do erro.")
    private String detail;
}