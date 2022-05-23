package com.cooperativismo.sispautas.domain.service.impl.validators;

import com.cooperativismo.sispautas.domain.entity.Associado;
import com.cooperativismo.sispautas.exception.DomainBadRequestException;
import com.cooperativismo.sispautas.exception.message.ErrorMessage;
import com.cooperativismo.sispautas.utils.BasicLog;
import com.cooperativismo.sispautas.utils.CPFUtil;
import com.cooperativismo.sispautas.utils.StringUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AssociadoValidators {

	
	public static void validators(Associado associado) {
		BasicLog.info("Começando validações", AssociadoValidators.class);
		validaCPF(associado.getCpf());
		validaNome(associado.getNome());
	}
	
	private static void validaCPF(String cpf) {
		if(!CPFUtil.isCPF(cpf)) { 	//Se o cpf for inválido estorará um BadRequest para o cliente.
			BasicLog.error(ErrorMessage.CPF_INVALIDO.getMessage(), AssociadoValidators.class);
			throw new DomainBadRequestException(ErrorMessage.CPF_INVALIDO.getMessage());
		}
	}
	
	private static void validaNome(String nome) {
		if(StringUtil.vazio(nome)) {   //Se o nome for inválido estorará um BadRequest para o cliente.
			BasicLog.error(ErrorMessage.DADO_VAZIO.erroCampoVazio("Nome"), AssociadoValidators.class);
			throw new DomainBadRequestException(ErrorMessage.DADO_VAZIO.erroCampoVazio("Nome"));
		}
	}
	

}
