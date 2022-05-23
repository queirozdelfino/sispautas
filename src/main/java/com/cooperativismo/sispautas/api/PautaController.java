package com.cooperativismo.sispautas.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cooperativismo.sispautas.api.docs.PautaDocs;
import com.cooperativismo.sispautas.domain.dto.PautaDTO;
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
	public ResponseEntity<Pauta> postPauta(@RequestBody PautaDTO pautaDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(pautaService.createPauta(pautaDto));
	}

}
