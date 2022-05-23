package com.cooperativismo.sispautas.exception;

import org.springframework.http.HttpStatus;

import com.cooperativismo.sispautas.exception.dto.ResponseError;

/**
 * Usada somente para erros inesperados do sistema.
 */
public class DomainInternalServerErrorException extends AbstractDomainServiceException {
	
	private static final long serialVersionUID = 1L;
	public static final String TITLE = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
	public static final String CODE = HttpStatus.INTERNAL_SERVER_ERROR.toString();
	private static final int HTTP_STATUS_CODE = HttpStatus.INTERNAL_SERVER_ERROR.value();
	
	public DomainInternalServerErrorException(ResponseError responseError) {
		super(responseError,HTTP_STATUS_CODE);
	}

	public DomainInternalServerErrorException(ResponseError responseError, Throwable e) {
		super(responseError,HTTP_STATUS_CODE,e);
	}

	public DomainInternalServerErrorException(String detail) {
		super(ResponseError.create(CODE, TITLE, detail),HTTP_STATUS_CODE);
	}

	public DomainInternalServerErrorException(String detail,Throwable e) {
		super(ResponseError.create(CODE, TITLE, detail),HTTP_STATUS_CODE,e);
	}
	
}
