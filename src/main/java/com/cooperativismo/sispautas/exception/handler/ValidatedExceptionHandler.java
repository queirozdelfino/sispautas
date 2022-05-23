package com.cooperativismo.sispautas.exception.handler;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.cooperativismo.sispautas.exception.DomainBadRequestException;
import com.cooperativismo.sispautas.exception.dto.LogErrorDetailDTO;
import com.cooperativismo.sispautas.exception.dto.ResponseError;
import com.cooperativismo.sispautas.utils.BasicLog;


/**
 * Classe responsável pelo tratamento do retorno de exceções validadas como 400 BAD_REQUEST.
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ValidatedExceptionHandler {
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ResponseError> handleMethodArgumentTypeMismatchException(HttpMessageNotReadableException ex) {
		
		final String message = "Verifique os campos se estão de acordo com objeto JSON.";
		final ResponseError response = ResponseError.create(DomainBadRequestException.CODE,
				DomainBadRequestException.TITLE, message);
		logBadRequestExceptions(ex, response);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	/**
	 * Registra o log APENAS das exceções de BAD_REQUEST.
	 */
	private void logBadRequestExceptions(Throwable ex, final ResponseError response) {
		final LogErrorDetailDTO logErrorDetail = new LogErrorDetailDTO();
		logErrorDetail.setMessage(ExceptionUtils.getMessage(ex));
		logErrorDetail.setDetail(response.toString());
		logErrorDetail.setCode(HttpStatus.BAD_REQUEST.value()+"");
		BasicLog.error(logErrorDetail.getDetail(), ex.getClass());
	}
}
