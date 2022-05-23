package com.cooperativismo.sispautas.domain.service.impl.validators;

import com.cooperativismo.sispautas.domain.dto.AssociadoDTO;
import com.cooperativismo.sispautas.exception.DomainBadRequestException;
import com.cooperativismo.sispautas.exception.message.ErrorMessage;
import com.cooperativismo.sispautas.utils.BasicLog;
import com.cooperativismo.sispautas.utils.CPFUtil;
import com.cooperativismo.sispautas.utils.StringUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AssociadoValidators {
	
	public static void validators(AssociadoDTO associadoDto) {
		BasicLog.info("Começando validações", AssociadoValidators.class);
		validaCPF(associadoDto.getCpf());
		StringUtil.validaCampo(associadoDto.getNome(), "Nome", 100, AssociadoValidators.class);

	}
	
	public static void validaCPF(String cpf) {
		if(!CPFUtil.isCPF(cpf)) { 	//Se o cpf for inválido estorará um BadRequest para o cliente.
			BasicLog.error(ErrorMessage.CPF_INVALIDO.getMessage(), AssociadoValidators.class);
			throw new DomainBadRequestException(ErrorMessage.CPF_INVALIDO.getMessage());
		}
	}
	
}
