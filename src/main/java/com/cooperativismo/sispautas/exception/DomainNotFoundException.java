package com.cooperativismo.sispautas.exception;

import org.springframework.http.HttpStatus;
import com.cooperativismo.sispautas.exception.dto.ResponseError;

/**
 * Ultilizado somente para resposta NOT FOUND para o usuário.
 **/
public class DomainNotFoundException extends AbstractDomainServiceException {

	private static final long serialVersionUID = 1L;
	private static final String TITLE = HttpStatus.NOT_FOUND.getReasonPhrase();
	private static final String CODE = HttpStatus.NOT_FOUND.toString();
	private static final int HTTP_STATUS_CODE = HttpStatus.NOT_FOUND.value();
	
	public DomainNotFoundException(ResponseError responseError) {
		super(responseError,HTTP_STATUS_CODE);
	}

	public DomainNotFoundException(ResponseError responseError, Throwable e) {
		super(responseError,HTTP_STATUS_CODE,e);
	}

	public DomainNotFoundException(String detail) {
		super(ResponseError.create(CODE, TITLE, detail),HTTP_STATUS_CODE);
	}

	public DomainNotFoundException(String detail,Throwable e) {
		super(ResponseError.create(CODE, TITLE, detail),HTTP_STATUS_CODE,e);
	}
	
}

