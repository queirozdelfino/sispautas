package com.cooperativismo.sispautas.utils;

import java.time.LocalDateTime;

import com.cooperativismo.sispautas.exception.DomainBadRequestException;
import com.cooperativismo.sispautas.exception.message.ErrorMessage;

public class DateUtil {
	
	
	public static void validaDataIntervalo(LocalDateTime startDate, LocalDateTime endDate, Class<?> clazz) {
		if(startDate.isAfter(endDate)) {
			BasicLog.error(ErrorMessage.DADO_MAIOR.erroCampoMaior(startDate.toString(), endDate.toString()), clazz);
			throw new DomainBadRequestException(ErrorMessage.DADO_MAIOR.erroCampoMaior(startDate.toString(), endDate.toString()));
		}
	}

}
