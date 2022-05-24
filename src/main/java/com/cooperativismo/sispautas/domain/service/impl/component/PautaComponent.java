package com.cooperativismo.sispautas.domain.service.impl.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cooperativismo.sispautas.domain.entity.Associado;
import com.cooperativismo.sispautas.domain.entity.Pauta;
import com.cooperativismo.sispautas.domain.enums.Decisao;
import com.cooperativismo.sispautas.domain.service.AssociadoService;
import com.cooperativismo.sispautas.domain.service.impl.validators.AssociadoValidators;
import com.cooperativismo.sispautas.utils.BasicLog;

/**
 * Classe component para auxiliar a service e separar métodos. 
 */
@Component
public class PautaComponent {
	
	private final AssociadoService associadoService;
	private int countSim = 0;
	private int countNao = 0;
	
	@Autowired
	public PautaComponent(AssociadoService associadoService) {
		this.associadoService = associadoService;
	}


	public Associado findAssociadoByCpf(String cpf) {
		BasicLog.info("Validando CPF", PautaComponent.class);
		AssociadoValidators.validaCPF(cpf);
		return associadoService.findAssociadoByCpf(cpf);
	}
	
	public Pauta calcDecisao(Pauta pauta) {
		
		pauta.getVotos().forEach(voto -> {
			if(voto.getDecisao() == Decisao.SIM) {
				this.countSim++;
			} else {
				this.countNao++;
			}
		});
		
		if(this.countSim > this.countNao) {
			pauta.setDecisaoFinal(Decisao.SIM);
		} else {
			pauta.setDecisaoFinal(Decisao.NAO);  //Para casos de empate, o não será default.
		}
		
		return pauta;
		
	}

}
