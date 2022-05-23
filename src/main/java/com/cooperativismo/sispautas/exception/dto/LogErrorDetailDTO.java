package com.cooperativismo.sispautas.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogErrorDetailDTO {

	private String consentId;
	private String spanId;
	private String traceId;
	private String requestUuid;
	private String idempotencyKey;

	private String sourceLocation;
	private String message;
	private String detail;
	private String code;
}