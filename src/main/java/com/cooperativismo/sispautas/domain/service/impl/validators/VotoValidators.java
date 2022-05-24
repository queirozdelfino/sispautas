package com.cooperativismo.sispautas.domain.service.impl.validators;

import com.cooperativismo.sispautas.domain.dto.VotoDTO;
import com.cooperativismo.sispautas.utils.BasicLog;
import com.cooperativismo.sispautas.utils.CPFUtil;
import com.cooperativismo.sispautas.utils.NumberUtil;
import com.cooperativismo.sispautas.utils.StringUtil;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VotoValidators {
	
	public static void validators(VotoDTO votoDto) {
		BasicLog.info("Começando validações", VotoValidators.class);
		NumberUtil.validaCampoLong(votoDto.getIdPauta(), "idPauta", VotoValidators.class);
		CPFUtil.validaCPF(votoDto.getCpfAssociado(), VotoValidators.class);
		StringUtil.validaCampoDecisao(votoDto.getDecisao(), VotoValidators.class);
	}
	
	

}
