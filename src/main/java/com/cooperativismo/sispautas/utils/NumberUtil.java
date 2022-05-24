package com.cooperativismo.sispautas.utils;

import com.cooperativismo.sispautas.exception.DomainBadRequestException;
import com.cooperativismo.sispautas.exception.message.ErrorMessage;

public class NumberUtil {
	

	//Responsável por testar se o campo está válido, senão, estourará um badRequest.
	public static void validaCampoLong(Long campo, String nomeCampo, Class<?> clazz) {
		if(vazioLong(campo)) {   //Se o nome for inválido estorará um BadRequest para o cliente.
			BasicLog.error(ErrorMessage.DADO_VAZIO.erroCampoVazio(nomeCampo), clazz);
			throw new DomainBadRequestException(ErrorMessage.DADO_VAZIO.erroCampoVazio(nomeCampo));
		}
	}
	
	
	public static boolean vazioLong(Long l) {
		if(l == null) {
			return true;
		}
		return false;
	}

}
