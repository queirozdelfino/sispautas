package com.cooperativismo.sispautas.domain.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cooperativismo.sispautas.domain.dto.PautaDTO;
import com.cooperativismo.sispautas.domain.dto.SessaoPautaDTO;
import com.cooperativismo.sispautas.domain.entity.Associado;
import com.cooperativismo.sispautas.domain.entity.Pauta;
import com.cooperativismo.sispautas.domain.repository.PautaRepository;
import com.cooperativismo.sispautas.domain.service.AssociadoService;
import com.cooperativismo.sispautas.domain.service.PautaService;
import com.cooperativismo.sispautas.domain.service.impl.component.PautaComponent;
import com.cooperativismo.sispautas.domain.service.impl.validators.PautaValidators;
import com.cooperativismo.sispautas.exception.DomainInternalServerErrorException;
import com.cooperativismo.sispautas.exception.DomainNotFoundException;
import com.cooperativismo.sispautas.exception.DomainUnprocessableEntityException;
import com.cooperativismo.sispautas.exception.message.ErrorMessage;
import com.cooperativismo.sispautas.utils.BasicLog;

/***
 * Classe responsável pelos serviços de Pauta.
 */
@Service
public class PautaServiceImpl implements PautaService {
	
	private static final int DEFAULT_MINUTE_SESSAO = 1;
	private final PautaComponent pautaComponent;
	private final PautaRepository repository;
	
	
	@Autowired
	public PautaServiceImpl(PautaComponent pautaComponent, PautaRepository repository) {
		this.pautaComponent = pautaComponent;
		this.repository = repository;
	}


	@Override
	public Pauta createPauta(PautaDTO pautaDto) {
		
		Pauta response;
		Associado autor;
		
		PautaValidators.validators(pautaDto);
		
		autor = pautaComponent.findAssociadoByCpf(pautaDto.getCpfAutor());
		
		if(autor == null) {  //Verifica se o cpf foi encontrado
			BasicLog.error(ErrorMessage.CPF_NAO_ENCONTRADO.getMessage(), PautaService.class);
			throw new DomainNotFoundException(ErrorMessage.CPF_NAO_ENCONTRADO.getMessage());
		}
		
		try {
			BasicLog.info("Acesso ao banco", PautaService.class);
			response = repository.save(mapPauta(pautaDto, autor));
		} catch (Exception e) {
			BasicLog.error(e.getMessage(), PautaService.class);
			throw new DomainInternalServerErrorException(ErrorMessage.ERRO_INTERNO.getMessage(), e);
		}
		
		return response;
	}
	
	
	@Override
	public Pauta createSessao(SessaoPautaDTO sessaoPautaDTO) {
		
		Pauta response;
		Associado autor;
		
		PautaValidators.validatorsSessao(sessaoPautaDTO);
		
		response = findPautaById(sessaoPautaDTO.getIdPauta());
		
		if(response == null) {  //Verifica se a pauta existe.
			BasicLog.error(ErrorMessage.PAUTA_NAO_ENCONTRADA.getMessage(), PautaService.class);
			throw new DomainNotFoundException(ErrorMessage.PAUTA_NAO_ENCONTRADA.getMessage());
		}
		
		if(response.getDataLimite() != null) {  //Verifica se a pauta já foi iniciada.
			BasicLog.error(ErrorMessage.PAUTA_JA_INICIADA.getMessage(), PautaService.class);
			throw new DomainUnprocessableEntityException(ErrorMessage.PAUTA_JA_INICIADA.getMessage());
		}
		
		autor = pautaComponent.findAssociadoByCpf(sessaoPautaDTO.getCpfAutor());
		
		if(autor == null) {  //Verifica se o cpf foi encontrado
			BasicLog.error(ErrorMessage.CPF_NAO_ENCONTRADO.getMessage(), PautaService.class);
			throw new DomainNotFoundException(ErrorMessage.CPF_NAO_ENCONTRADO.getMessage());
		}
		
		
		try {
			BasicLog.info("Acesso ao banco", PautaService.class);
			response = repository.save(mapPautaBySessao(sessaoPautaDTO, autor, response));
		} catch (Exception e) {
			BasicLog.error(e.getMessage(), PautaService.class);
			throw new DomainInternalServerErrorException(ErrorMessage.ERRO_INTERNO.getMessage(), e);
		}
		return response;
	}

	
	@Override
	public Pauta findPautaById(Long id) {
		Optional<Pauta> response;
		
		try {
			BasicLog.info("Acesso ao banco", AssociadoService.class);
			response = repository.findById(id);
		} catch (Exception e) {
			BasicLog.error(e.getMessage(), AssociadoService.class);
			throw new DomainInternalServerErrorException(ErrorMessage.ERRO_INTERNO.getMessage(), e);
		}
		if(response.isPresent()) {
			return response.get();
		}
		return null;
	}


	private Pauta mapPauta(PautaDTO dto, Associado autor) {
		Pauta pauta = new Pauta();
		
		pauta.setTitulo(dto.getTitulo());
		pauta.setDetalhes(dto.getDetalhes());
		pauta.setAutor(autor);
		return pauta;
	}
	
	private Pauta mapPautaBySessao(SessaoPautaDTO dto, Associado autor, Pauta pauta) {
	
		pauta.setAutor(autor);
		
		if(dto.getDataLimite() == null) { //Se data limite não for colocado o default será setado.
			pauta.setDataLimite(LocalDateTime.now().plusMinutes(DEFAULT_MINUTE_SESSAO));
		} else {			
			pauta.setDataLimite(dto.getDataLimite());
		}
		return pauta;
	}



}
