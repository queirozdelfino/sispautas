package com.cooperativismo.sispautas.external.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.cooperativismo.sispautas.exception.DomainInternalServerErrorException;
import com.cooperativismo.sispautas.exception.DomainNotFoundException;
import com.cooperativismo.sispautas.exception.DomainUnprocessableEntityException;
import com.cooperativismo.sispautas.exception.message.ErrorMessage;
import com.cooperativismo.sispautas.external.CpfExternalService;
import com.cooperativismo.sispautas.external.SistemaCpfApiClient;
import com.cooperativismo.sispautas.external.dto.ResponseCpfExternal;
import com.cooperativismo.sispautas.utils.BasicLog;

import feign.FeignException;
import feign.FeignException.NotFound;

@Service
public class CpfExternalServiceImpl implements CpfExternalService{

	private final SistemaCpfApiClient sistemaCpfApiClient;
	
	
	@Autowired
	public CpfExternalServiceImpl(SistemaCpfApiClient sistemaCpfApiClient) {
		this.sistemaCpfApiClient = sistemaCpfApiClient;
	}



	@Override
	public void getPermissionCpfToVote(String cpf) {
		try {
			BasicLog.info("Acesso ao sistema externo", CpfExternalService.class);
			final ResponseEntity<ResponseCpfExternal> response = this.sistemaCpfApiClient.getStatusVoteCpf(cpf);
			
			if(response.getBody().getStatus().equalsIgnoreCase("UNABLE_TO_VOTE")) {
				throw new DomainUnprocessableEntityException(ErrorMessage.CPF_NAO_AUTORIZADO.getMessage());
			}
		} catch (NotFound e) {
			BasicLog.errorHttpStatus(e.getMessage(), HttpStatus.NOT_FOUND.value(), CpfExternalService.class);
			throw new DomainNotFoundException(ErrorMessage.CPF_INVALIDO.getMessage(), e);
		} catch (FeignException e) {
			BasicLog.errorHttpStatus(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), CpfExternalService.class);
			throw new DomainInternalServerErrorException(ErrorMessage.ERRO_INTERNO.getMessage(), e);
		}
		
	}

}
