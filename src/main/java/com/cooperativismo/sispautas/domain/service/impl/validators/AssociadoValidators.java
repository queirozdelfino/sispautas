package com.cooperativismo.sispautas.domain.service.impl.validators;

import com.cooperativismo.sispautas.domain.dto.AssociadoDTO;
import com.cooperativismo.sispautas.utils.BasicLog;
import com.cooperativismo.sispautas.utils.CPFUtil;
import com.cooperativismo.sispautas.utils.StringUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AssociadoValidators {
	
	public static void validators(AssociadoDTO associadoDto) {
		BasicLog.info("Começando validações", AssociadoValidators.class);
		CPFUtil.validaCPF(associadoDto.getCpf(), AssociadoValidators.class);
		StringUtil.validaCampo(associadoDto.getNome(), "Nome", 100, AssociadoValidators.class);

	}
	
}
