package com.cooperativismo.sispautas.domain.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooperativismo.sispautas.domain.dto.AssociadoDTO;
import com.cooperativismo.sispautas.domain.entity.Associado;
import com.cooperativismo.sispautas.domain.repository.AssociadoRepository;
import com.cooperativismo.sispautas.domain.service.AssociadoService;
import com.cooperativismo.sispautas.domain.service.impl.validators.AssociadoValidators;
import com.cooperativismo.sispautas.exception.DomainInternalServerErrorException;
import com.cooperativismo.sispautas.exception.DomainUnprocessableEntityException;
import com.cooperativismo.sispautas.exception.message.ErrorMessage;
import com.cooperativismo.sispautas.utils.BasicLog;

@Service
public class AssociadoServiceImpl implements AssociadoService{
	
	private final AssociadoRepository repository;
	
	
	
	@Autowired
	public AssociadoServiceImpl(AssociadoRepository repository) {
		this.repository = repository;
	}


	@Override
	public Associado createAssociado(AssociadoDTO associadoDto) {
		Associado response = null;
		
		AssociadoValidators.validators(associadoDto);
		
		if(findAssociadoByCpf(associadoDto.getCpf()) != null) {  //Verifica se o cadastro não está duplicado por CPF.
			BasicLog.error(ErrorMessage.CADASTO_DUPLICADO.getMessage(), AssociadoService.class);
			throw new DomainUnprocessableEntityException(ErrorMessage.CADASTO_DUPLICADO.getMessage());
		}
	
		try {
			BasicLog.info("Acesso ao banco", AssociadoService.class);
			response = repository.save(mapAssociado(associadoDto));
		} catch (Exception e) {
			BasicLog.error(e.getMessage(), AssociadoService.class);
			throw new DomainInternalServerErrorException(ErrorMessage.ERRO_INTERNO.getMessage(), e);
		}
		return response;
	}
	
	@Override
	public Associado findAssociadoByCpf(String cpf) {
		Optional<Associado> response;
		
		try {
			BasicLog.info("Acesso ao banco", AssociadoService.class);
			response = repository.findByCpf(cpf);
		} catch (Exception e) {
			BasicLog.error(e.getMessage(), AssociadoService.class);
			throw new DomainInternalServerErrorException(ErrorMessage.ERRO_INTERNO.getMessage(), e);
		}
		if(response.isPresent()) {
			return response.get();
		}
		return null;
	}
	
	
	private Associado mapAssociado(AssociadoDTO dto){
		Associado associado = new Associado();
		associado.setCpf(dto.getCpf());
		associado.setNome(dto.getCpf());
		return associado;
	}

}
