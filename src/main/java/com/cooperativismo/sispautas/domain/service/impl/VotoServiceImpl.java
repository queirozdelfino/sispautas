package com.cooperativismo.sispautas.domain.service.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cooperativismo.sispautas.domain.dto.VotoDTO;
import com.cooperativismo.sispautas.domain.entity.Associado;
import com.cooperativismo.sispautas.domain.entity.Pauta;
import com.cooperativismo.sispautas.domain.entity.Voto;
import com.cooperativismo.sispautas.domain.repository.VotoRepository;
import com.cooperativismo.sispautas.domain.service.VotoService;
import com.cooperativismo.sispautas.domain.service.impl.component.VotoComponent;
import com.cooperativismo.sispautas.domain.service.impl.validators.PautaValidators;
import com.cooperativismo.sispautas.domain.service.impl.validators.VotoValidators;
import com.cooperativismo.sispautas.exception.DomainInternalServerErrorException;
import com.cooperativismo.sispautas.exception.DomainNotFoundException;
import com.cooperativismo.sispautas.exception.DomainUnprocessableEntityException;
import com.cooperativismo.sispautas.exception.message.ErrorMessage;
import com.cooperativismo.sispautas.utils.BasicLog;

@Service
public class VotoServiceImpl implements VotoService {
	
	private final VotoRepository repository;
	private final VotoComponent votoComponent;
	
	
	@Autowired
	public VotoServiceImpl(VotoRepository repository, VotoComponent votoComponent) {
		this.repository = repository;
		this.votoComponent = votoComponent;
	}


	@Override
	public Voto createVoto(VotoDTO votoDto) {
		Voto response;
		Associado autor;
		Pauta pauta;
		
		//Validações
		VotoValidators.validators(votoDto);
		autor = votoComponent.findAssociadoByCpf(votoDto.getCpfAssociado());
		pauta = findPautaByIdVerify(votoDto.getIdPauta());
		PautaValidators.validatorsPautaVencida(pauta.getDataLimite());
		verifyExistsVoto(autor, pauta);
		
		
		//Persistência
		try {
			BasicLog.info("Acesso ao banco", VotoService.class);
			response = repository.save(mapVoto(votoDto, autor, pauta));
		} catch (Exception e) {
			BasicLog.error(e.getMessage(), VotoService.class);
			throw new DomainInternalServerErrorException(ErrorMessage.ERRO_INTERNO.getMessage(), e);
		}
		
		return response;
	}
	
	private Pauta findPautaByIdVerify(Long idPauta) {
		Pauta pauta = votoComponent.findPautaById(idPauta);
		
		if(pauta == null) {  //Verifica se a pauta existe.
			BasicLog.error(ErrorMessage.PAUTA_NAO_ENCONTRADA.getMessage(), VotoService.class);
			throw new DomainNotFoundException(ErrorMessage.PAUTA_NAO_ENCONTRADA.getMessage());
		}
		
		
		return pauta;
	}
	
	private void verifyExistsVoto(Associado autor, Pauta pauta) {
		Voto voto = findVotoByAssociadoAndPauta(autor, pauta);
		
		if(voto != null) {  //Verifica se a pauta já foi votada pelo associado.
			BasicLog.error(ErrorMessage.VOTO_EXISTENTE.getMessage(), VotoService.class);
			throw new DomainUnprocessableEntityException(ErrorMessage.VOTO_EXISTENTE.getMessage());
		}
	}
	
	@Override
	public Voto findVotoByAssociadoAndPauta(Associado autor, Pauta pauta) {
		Optional<Voto> response;
		
		try {
			BasicLog.info("Acesso ao banco", VotoService.class);
			response = repository.findTopByAutorIdAndPautaId(autor.getId(), pauta.getId());
		} catch (Exception e) {
			BasicLog.error(e.getMessage(), VotoService.class);
			throw new DomainInternalServerErrorException(ErrorMessage.ERRO_INTERNO.getMessage(), e);
		}
		
		if(response.isPresent()) {
			return response.get();
		}
		
		return null;
	}
	
	
	private Voto mapVoto(VotoDTO votoDto, Associado autor, Pauta pauta) {
		Voto voto = new Voto();
		
		voto.setAutor(autor);
		voto.setPauta(pauta);
		voto.setDecisao(votoDto.getDecisao());
		
		return voto;
	}

}
