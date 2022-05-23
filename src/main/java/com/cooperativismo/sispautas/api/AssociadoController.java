package com.cooperativismo.sispautas.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooperativismo.sispautas.api.docs.AssociadoDocs;
import com.cooperativismo.sispautas.domain.entity.Associado;
import com.cooperativismo.sispautas.domain.service.AssociadoService;

@RestController
@RequestMapping("/associado")
public class AssociadoController implements AssociadoDocs{
	
	private final AssociadoService associadoService;

	@Autowired
	public AssociadoController(AssociadoService associadoService) {
		this.associadoService = associadoService;
	}

	@Override
	@PostMapping
	public ResponseEntity<Associado> postAssociado(@RequestBody Associado associado) {
		return ResponseEntity.status(HttpStatus.CREATED).body(associadoService.createAssociado(associado));
	}

}
