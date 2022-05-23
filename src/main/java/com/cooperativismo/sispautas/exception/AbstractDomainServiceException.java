package com.cooperativismo.sispautas.exception;

import com.cooperativismo.sispautas.exception.dto.ResponseError;

import lombok.Getter;

public abstract class AbstractDomainServiceException extends RuntimeException {

	@Getter
	private final int httpStatus;
	
	@Getter
	private final ResponseError responseError;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AbstractDomainServiceException(ResponseError responseError,int httpStatus) {
		super(responseError.toString());
		this.httpStatus = httpStatus;
		this.responseError = responseError;
	}

	public AbstractDomainServiceException(ResponseError responseError,int httpStatus, Throwable e) {
		super(responseError.toString(),e);
		this.httpStatus = httpStatus;
		this.responseError = responseError;
	}
}