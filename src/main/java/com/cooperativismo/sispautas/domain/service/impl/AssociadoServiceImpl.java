package com.cooperativismo.sispautas.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cooperativismo.sispautas.domain.entity.Associado;
import com.cooperativismo.sispautas.domain.repository.AssociadoRepository;
import com.cooperativismo.sispautas.domain.service.AssociadoService;
import com.cooperativismo.sispautas.domain.service.impl.validators.AssociadoValidators;
import com.cooperativismo.sispautas.exception.DomainInternalServerErrorException;
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
	public Associado createAssociado(Associado associado) {
		Associado response = null;
		
		AssociadoValidators.validators(associado);
		
		try {
			BasicLog.info("Acesso ao banco", AssociadoService.class);
			response = repository.save(associado);
		} catch (Exception e) {
			BasicLog.error(e.getMessage(), AssociadoService.class);
			throw new DomainInternalServerErrorException(ErrorMessage.ERRO_INTERNO.getMessage(), e);
		}
		return response;
	}
	
	

}
