package com.cooperativismo.sispautas.exception.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@Builder
@ToString
public class ResponseError implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("errors")
    private final List<ResponseErrorInfo> errors = new ArrayList<>();

    public void addInfo(String code, String title, String detail) {

        this.errors.add(ResponseErrorInfo.builder()
                .code(code)
                .title(title)
                .detail(detail)
                .build());
    }

    public static ResponseError create(String code, String title, String detail) {
        final ResponseError responseError = ResponseError.builder().build();
		responseError.addInfo(code, title, detail);
		return responseError;
    }
}