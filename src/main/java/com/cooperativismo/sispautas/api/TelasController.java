package com.cooperativismo.sispautas.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cooperativismo.sispautas.api.docs.TelaDocs;
import com.cooperativismo.sispautas.domain.dto.tela.TelaFormularioDTO;
import com.cooperativismo.sispautas.domain.dto.tela.TelaSelecaoDTO;
import com.cooperativismo.sispautas.domain.service.TelasService;

@RestController
@RequestMapping("/tela")
public class TelasController implements TelaDocs{
	
	private final TelasService telasService;
	
	
	@Autowired
	public TelasController(TelasService telasService) {
		this.telasService = telasService;
	}



	@Override
	@GetMapping("/novo-associado")
	public ResponseEntity<TelaFormularioDTO> getTelaIncluirAssociado() {
		return ResponseEntity.status(HttpStatus.OK).body(telasService.getTelaIncluirAssociado());
	}



	@Override
	@GetMapping("/nova-pauta")
	public ResponseEntity<TelaFormularioDTO> getTelaIncluirPauta() {
		return ResponseEntity.status(HttpStatus.OK).body(telasService.getTelaIncluirPauta());
	}



	@Override
	@GetMapping("/selecionar-pauta")
	public ResponseEntity<TelaSelecaoDTO> getTelaSelecionarPautaParaVotar() {
		return ResponseEntity.status(HttpStatus.OK).body(telasService.getTelaSelecionarPauta());
	}


	@Override
	@GetMapping("/nova-sessao/{pautaId}")
	public ResponseEntity<TelaFormularioDTO> getTelaAbrirSessao(@PathVariable(value ="pautaId") Long pautaId) {
		return ResponseEntity.status(HttpStatus.OK).body(telasService.getTelaAbrirSessao(pautaId));
	}



	@Override
	@GetMapping("/votar/{pautaId}")
	public ResponseEntity<TelaFormularioDTO> getTelaVotarPauta(@PathVariable(value ="pautaId") Long pautaId) {
		return ResponseEntity.status(HttpStatus.OK).body(telasService.getTelaVotarPauta(pautaId));
	}



	@Override
	@GetMapping("/resultado/{pautaId}")
	public ResponseEntity<TelaFormularioDTO> getTelaApresentarResultadoPauta(@PathVariable(value ="pautaId") Long pautaId) {
		return ResponseEntity.status(HttpStatus.OK).body(telasService.getTelaApresentarResultadoPauta(pautaId));
	}
	
	
	
	

}
