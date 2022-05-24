package com.cooperativismo.sispautas.domain.service.impl.component;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import com.cooperativismo.sispautas.domain.dto.tela.ItensTelaSelecaoDTO;
import com.cooperativismo.sispautas.domain.entity.Pauta;
import com.cooperativismo.sispautas.domain.service.PautaService;

@Component
public class TelasComponent {
	
	private final PautaService pautaService;
	private final Environment env;
	
	@Autowired
	public TelasComponent(PautaService pautaService, Environment env) {
		this.pautaService = pautaService;
		this.env = env;
	}


	public List<ItensTelaSelecaoDTO> buildOptionsToSessao(){
		List<ItensTelaSelecaoDTO> response = new ArrayList<>();
		List<Pauta> pautas = pautaService.findPautaParaVotar();
		ItensTelaSelecaoDTO item = new ItensTelaSelecaoDTO();
		
		pautas.forEach(pauta -> {
			item.setTexto(pauta.getTitulo());
			item.setUrl(env.getProperty("sispautas.host") + "/nova-sessao");
			response.add(item);
		});
		
		return response;
	}

	public Pauta findPautaNaoAbertaById(Long id){
		Pauta pauta = pautaService.findPautaById(id);

		pautaService.verifyPautaNaoAberta(pauta);
		
		return pauta;
	}
	
	public Pauta findPautaNaoFechadaById(Long id){
		Pauta pauta = pautaService.findPautaById(id);

		pautaService.verifyPautaAberta(pauta);
		
		return pauta;
	}
	
	public Pauta findPautaFechadaById(Long id){
		Pauta pauta = pautaService.findPautaById(id);

		pautaService.verifyPautaEncerrada(pauta);
		
		return pauta;
	}
}
