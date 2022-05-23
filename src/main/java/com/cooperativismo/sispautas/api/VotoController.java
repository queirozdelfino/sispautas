package com.cooperativismo.sispautas.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cooperativismo.sispautas.api.docs.VotoDocs;
import com.cooperativismo.sispautas.domain.dto.VotoDTO;
import com.cooperativismo.sispautas.domain.entity.Voto;
import com.cooperativismo.sispautas.domain.service.VotoService;

@RestController
@RequestMapping("/votar")
public class VotoController implements VotoDocs{
	
	private final VotoService votoService;
	
	
	@Autowired
	public VotoController(VotoService votoService) {
		this.votoService = votoService;
	}

	@Override
	@PostMapping
	public ResponseEntity<Voto> postVotarPauta(VotoDTO votoDTO) {
		return ResponseEntity.status(HttpStatus.OK).body(votoService.createVoto(votoDTO));
	}

}
