package com.cooperativismo.sispautas.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cooperativismo.sispautas.domain.dto.PautaDTO;
import com.cooperativismo.sispautas.domain.entity.Associado;
import com.cooperativismo.sispautas.domain.entity.Pauta;
import com.cooperativismo.sispautas.domain.repository.PautaRepository;
import com.cooperativismo.sispautas.domain.service.PautaService;
import com.cooperativismo.sispautas.domain.service.impl.component.PautaComponent;
import com.cooperativismo.sispautas.domain.service.impl.validators.PautaValidators;
import com.cooperativismo.sispautas.exception.DomainInternalServerErrorException;
import com.cooperativismo.sispautas.exception.DomainNotFoundException;
import com.cooperativismo.sispautas.exception.message.ErrorMessage;
import com.cooperativismo.sispautas.utils.BasicLog;

/***
 * Classe responsável pelos serviços de Pauta.
 */
@Service
public class PautaServiceImpl implements PautaService {
	
	private final PautaComponent pautaComponent;
	private final PautaRepository repository;
	
	
	@Autowired
	public PautaServiceImpl(PautaComponent pautaComponent, PautaRepository repository) {
		this.pautaComponent = pautaComponent;
		this.repository = repository;
	}


	@Override
	public Pauta createPauta(PautaDTO pautaDto) {
		Pauta response = null;
		
		PautaValidators.validators(pautaDto);
		
		Associado autor = pautaComponent.findAssociadoByCpf(pautaDto.getCpfAutor());
		
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
	
	



	private Pauta mapPauta(PautaDTO dto, Associado autor) {
		Pauta pauta = new Pauta();
		
		pauta.setTitulo(dto.getTitulo());
		pauta.setDetalhes(dto.getDetalhes());
		pauta.setAutor(autor);
		return pauta;
	}

}
