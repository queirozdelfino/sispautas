package com.cooperativismo.sispautas.api.docs;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.cooperativismo.sispautas.domain.entity.Associado;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "associado", description = "Responsável por manter Associados da cooperativa")
public interface AssociadoDocs {
	
	@Operation(summary = "Inclui novo associado", tags = "associado")
	ResponseEntity<Associado> postAssociado(@RequestBody Associado associado);
}
