package com.cooperativismo.sispautas.exception;

import org.springframework.http.HttpStatus;
import com.cooperativismo.sispautas.exception.dto.ResponseError;

/**
 * Utilizado para exceção de regras de negócio.
 */
public class DomainUnprocessableEntityException extends AbstractDomainServiceException {

	private static final long serialVersionUID = 1L;
	private static final String TITLE = HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase();
	private static final String CODE = HttpStatus.UNPROCESSABLE_ENTITY.toString();
	private static final int HTTP_STATUS_CODE = HttpStatus.UNPROCESSABLE_ENTITY.value();
	
	public DomainUnprocessableEntityException(ResponseError responseError) {
		super(responseError,HTTP_STATUS_CODE);
	}

	public DomainUnprocessableEntityException(ResponseError responseError, Throwable e) {
		super(responseError,HTTP_STATUS_CODE,e);
	}

	public DomainUnprocessableEntityException(String detail) {
		super(ResponseError.create(CODE, TITLE, detail),HTTP_STATUS_CODE);
	}

	public DomainUnprocessableEntityException(String detail,Throwable e) {
		super(ResponseError.create(CODE, TITLE, detail),HTTP_STATUS_CODE,e);
	}
	
}

