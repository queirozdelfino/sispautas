package com.cooperativismo.sispautas.domain.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cooperativismo.sispautas.domain.entity.Pauta;

public interface PautaRepository extends JpaRepository<Pauta, Long>{
	
	List<Pauta> findByDataLimite(LocalDateTime dataLimite);

}
