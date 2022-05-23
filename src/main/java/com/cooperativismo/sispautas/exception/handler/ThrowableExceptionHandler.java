package com.cooperativismo.sispautas.exception.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cooperativismo.sispautas.exception.DomainInternalServerErrorException;
import com.cooperativismo.sispautas.exception.dto.ResponseError;

/**
 * Resolve as exceções não tratados no projeto.
 */
@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class ThrowableExceptionHandler {
	
	@ExceptionHandler(value = Throwable.class)
	@Order(Ordered.LOWEST_PRECEDENCE)
	public ResponseEntity<ResponseError> handleThrowable(Throwable ex) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.body(ResponseError.create(DomainInternalServerErrorException.CODE, 
							DomainInternalServerErrorException.TITLE, DomainInternalServerErrorException.TITLE));
	}
}
