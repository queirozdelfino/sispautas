package com.cooperativismo.sispautas.domain.service.impl.validators;

import com.cooperativismo.sispautas.domain.dto.PautaDTO;
import com.cooperativismo.sispautas.utils.BasicLog;
import com.cooperativismo.sispautas.utils.StringUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PautaValidators {
	
	public static void validators(PautaDTO pautaDto) {
		BasicLog.info("Começando validações", PautaValidators.class);
		StringUtil.validaCampo(pautaDto.getTitulo(), "Título", 40, PautaValidators.class);
		StringUtil.validaCampo(pautaDto.getDetalhes(), "Detalhes", 300, PautaValidators.class);
		//DateUtil.validaDataIntervalo(LocalDateTime.now(), pautaDto.getDataLimite(), PautaValidators.class);

	}
	
	


}
