package com.cooperativismo.sispautas.exception.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.cooperativismo.sispautas.exception.AbstractDomainServiceException;
import com.cooperativismo.sispautas.exception.dto.ResponseError;

/**
 * Resolve as exceções TRATADAS dentro do sistema.
 *
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DomainExceptionHandler {

	/**
	 * Resolve as exceções TRATADAS dentro do sistema, derivadas de {@link AbstractDomainServiceException}
	 * 
	 * @param ex AbstractDomainServiceException
	 * @return
	 */
	@ExceptionHandler(AbstractDomainServiceException.class)
    public ResponseEntity<ResponseError> handleDomainServiceException(AbstractDomainServiceException ex) {
	      return ResponseEntity
	                .status(ex.getHttpStatus())
	                .body(ex.getResponseError());
    }

}
