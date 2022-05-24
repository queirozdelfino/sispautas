package com.cooperativismo.sispautas.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cooperativismo.sispautas.api.docs.PautaDocs;
import com.cooperativismo.sispautas.domain.dto.PautaDTO;
import com.cooperativismo.sispautas.domain.dto.SessaoPautaDTO;
import com.cooperativismo.sispautas.domain.entity.Pauta;
import com.cooperativismo.sispautas.domain.service.PautaService;

@RestController
@RequestMapping("/pauta")
public class PautaController implements PautaDocs{
	
	private final PautaService pautaService;
	
	@Autowired
	public PautaController(PautaService pautaService) {
		this.pautaService = pautaService;
	}


	@Override
	@PostMapping
	public ResponseEntity<Pauta> postPauta(PautaDTO pautaDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(pautaService.createPauta(pautaDto));
	}


	@Override
	@PostMapping("/sessao")
	public ResponseEntity<Pauta> postSessaoPauta(SessaoPautaDTO sessaoPautaDTO) {
		return ResponseEntity.status(HttpStatus.OK).body(pautaService.createSessao(sessaoPautaDTO));
	}


	@Override
	@GetMapping("/{pautaId}")
	public ResponseEntity<Pauta> getDecisaoPauta(@PathVariable(value ="pautaId") Long pautaId) {
		return ResponseEntity.status(HttpStatus.OK).body(pautaService.findDecisaoPauta(pautaId));
	}



}
