package com.cooperativismo.sispautas.utils;

import com.cooperativismo.sispautas.domain.enums.Decisao;
import com.cooperativismo.sispautas.exception.DomainBadRequestException;
import com.cooperativismo.sispautas.exception.message.ErrorMessage;

/**
 * Responsável por testar se o campo está válido, senão, estourará um badRequest.
 * */
public class StringUtil {
	
	public static void validaCampo(String campo, String nomeCampo, int tamanhoMax, Class<?> clazz) {
		if(vazio(campo)) {   //Se o campo for inválido estorará um BadRequest para o cliente.
			BasicLog.error(ErrorMessage.DADO_VAZIO.erroCampoVazio(nomeCampo), clazz);
			throw new DomainBadRequestException(ErrorMessage.DADO_VAZIO.erroCampoVazio(nomeCampo));
		}
		if(campo.length() > tamanhoMax) {	 //Se o campo for maior que o permitido estorará um BadRequest para o cliente.
			BasicLog.error(ErrorMessage.DADO_MAIOR.erroCampoMaior(nomeCampo, Integer.toString(tamanhoMax)), clazz);
			throw new DomainBadRequestException(ErrorMessage.DADO_MAIOR.erroCampoMaior(nomeCampo, Integer.toString(tamanhoMax)));
		}
	}
	
	
	public static void validaCampoDecisao(Decisao decisao, Class<?> clazz) {
		if(vazio(decisao)) {   //Se o nome for inválido estorará um BadRequest para o cliente.
			BasicLog.error(ErrorMessage.DADO_VAZIO.erroCampoVazio("Decisão"), clazz);
			throw new DomainBadRequestException(ErrorMessage.DADO_VAZIO.erroCampoVazio("Decisão"));
		}
	}
	
	
	public static boolean vazio(String string) {
		if(string == null || string.trim().isEmpty()) {
			return true;
		}
		return false;
	}
	
	public static boolean vazio(Decisao decisao) {
		if(decisao == null) {
			return true;
		}
		return false;
	}

}
