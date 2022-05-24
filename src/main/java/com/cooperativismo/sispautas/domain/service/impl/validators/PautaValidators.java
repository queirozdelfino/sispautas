package com.cooperativismo.sispautas.domain.service.impl.validators;

import java.time.LocalDateTime;
import com.cooperativismo.sispautas.domain.dto.PautaDTO;
import com.cooperativismo.sispautas.domain.dto.SessaoPautaDTO;
import com.cooperativismo.sispautas.utils.BasicLog;
import com.cooperativismo.sispautas.utils.CPFUtil;
import com.cooperativismo.sispautas.utils.DateUtil;
import com.cooperativismo.sispautas.utils.NumberUtil;
import com.cooperativismo.sispautas.utils.StringUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PautaValidators {
	
	public static void validators(PautaDTO pautaDto) {
		BasicLog.info("Começando validações", PautaValidators.class);
		CPFUtil.validaCPF(pautaDto.getCpfAutor(), PautaValidators.class);
		StringUtil.validaCampo(pautaDto.getTitulo(), "Título", 40, PautaValidators.class);
		StringUtil.validaCampo(pautaDto.getDetalhes(), "Detalhes", 300, PautaValidators.class);
	}
	
	public static void validatorsSessao(SessaoPautaDTO sessaoPautaDTO) {
		BasicLog.info("Começando validações", PautaValidators.class);
		NumberUtil.validaCampoLong(sessaoPautaDTO.getIdPauta(), "idPauta", PautaValidators.class);
		CPFUtil.validaCPF(sessaoPautaDTO.getCpfAutor(), PautaValidators.class);
		if(sessaoPautaDTO.getDataLimite() != null) {
			DateUtil.validaDataIntervalo(LocalDateTime.now(), sessaoPautaDTO.getDataLimite(), PautaValidators.class);
		}
	}
	
	public static void validatorsPautaVencida(LocalDateTime data) {
		BasicLog.info("Começando validações", PautaValidators.class);
		if(data != null) {		
			DateUtil.validaDataVencida(LocalDateTime.now(), data, PautaValidators.class);
		}
	}

	
	


}
