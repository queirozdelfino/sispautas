package com.cooperativismo.sispautas.exception;

import org.springframework.http.HttpStatus;

import com.cooperativismo.sispautas.exception.dto.ResponseError;

/**
 * Usado somente para tratamento de atributos.
 */
public class DomainBadRequestException extends AbstractDomainServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String TITLE = "Validation error";
	public static final String CODE = "VALIDATION_ERROR"; 
	private static final int HTTP_STATUS_CODE = HttpStatus.BAD_REQUEST.value();
	
	public DomainBadRequestException(ResponseError responseError) {
		super(responseError,HTTP_STATUS_CODE);
	}

	public DomainBadRequestException(ResponseError responseError, Throwable e) {
		super(responseError,HTTP_STATUS_CODE,e);
	}
	
	public DomainBadRequestException(String detail) {
		super(ResponseError.create(CODE, TITLE, detail),HTTP_STATUS_CODE);
	}
	
}

