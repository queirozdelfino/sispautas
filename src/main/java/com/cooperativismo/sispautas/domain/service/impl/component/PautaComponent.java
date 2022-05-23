package com.cooperativismo.sispautas.domain.service.impl.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cooperativismo.sispautas.domain.entity.Associado;
import com.cooperativismo.sispautas.domain.service.AssociadoService;
import com.cooperativismo.sispautas.domain.service.impl.validators.AssociadoValidators;
import com.cooperativismo.sispautas.utils.BasicLog;

/**
 * Classe component para auxiliar a service e separar métodos. 
 */
@Component
public class PautaComponent {
	
	private final AssociadoService associadoService;
	
	@Autowired
	public PautaComponent(AssociadoService associadoService) {
		this.associadoService = associadoService;
	}


	public Associado findAssociadoByCpf(String cpf) {
		BasicLog.info("Validando CPF", PautaComponent.class);
		AssociadoValidators.validaCPF(cpf);
		return associadoService.findAssociadoByCpf(cpf);
	}

}
