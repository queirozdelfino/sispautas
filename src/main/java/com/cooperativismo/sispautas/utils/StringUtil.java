package com.cooperativismo.sispautas.utils;

import com.cooperativismo.sispautas.exception.DomainBadRequestException;
import com.cooperativismo.sispautas.exception.message.ErrorMessage;

public class StringUtil {
	

	
	
	//Responsável por testar se o campo está válido, senão, estourará um badRequest.
	public static void validaCampo(String campo, String nomeCampo, int tamanhoMax, Class<?> clazz) {
		if(vazio(campo)) {   //Se o nome for inválido estorará um BadRequest para o cliente.
			BasicLog.error(ErrorMessage.DADO_VAZIO.erroCampoVazio(nomeCampo), clazz);
			throw new DomainBadRequestException(ErrorMessage.DADO_VAZIO.erroCampoVazio(nomeCampo));
		}
		if(campo.length() > tamanhoMax) {	 //Se o nome for maior que o permitido estorará um BadRequest para o cliente.
			BasicLog.error(ErrorMessage.DADO_MAIOR.erroCampoMaior(nomeCampo, Integer.toString(tamanhoMax)), clazz);
			throw new DomainBadRequestException(ErrorMessage.DADO_MAIOR.erroCampoMaior(nomeCampo, Integer.toString(tamanhoMax)));
		}
	}
	
	
	public static boolean vazio(String string) {
		if(string == null || string.trim().isEmpty()) {
			return true;
		}
		return false;
	}

}
