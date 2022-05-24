package com.cooperativismo.sispautas.domain.service.impl.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.cooperativismo.sispautas.domain.entity.Associado;
import com.cooperativismo.sispautas.domain.entity.Pauta;
import com.cooperativismo.sispautas.domain.service.AssociadoService;
import com.cooperativismo.sispautas.domain.service.PautaService;
import com.cooperativismo.sispautas.utils.BasicLog;
import com.cooperativismo.sispautas.utils.CPFUtil;

@Component
public class VotoComponent {
	
	private final AssociadoService associadoService;
	private final PautaService pautaService;
	
	@Autowired
	public VotoComponent(AssociadoService associadoService, PautaService pautaService) {
		this.associadoService = associadoService;
		this.pautaService = pautaService;
	}


	public Associado findAssociadoByCpf(String cpf) {
		BasicLog.info("Validando CPF", PautaComponent.class);
		CPFUtil.validaCPF(cpf, PautaComponent.class);
		return associadoService.findAssociadoByCpf(cpf);
	}
	
	public Pauta findPautaById(Long id) {
		return pautaService.findPautaById(id);
	}

}
