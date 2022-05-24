package com.cooperativismo.sispautas.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.cooperativismo.sispautas.external.dto.ResponseCpfExternal;

@Component
@FeignClient(name = "sistemaCpfApiClient", url = "${external.sistema-cpf.host}")
public interface SistemaCpfApiClient {
	
	@GetMapping("/{cpf}")
	ResponseEntity<ResponseCpfExternal> getStatusVoteCpf(@PathVariable("cpf") String cpf);

}
