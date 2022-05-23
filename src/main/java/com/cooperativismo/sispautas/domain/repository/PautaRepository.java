package com.cooperativismo.sispautas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cooperativismo.sispautas.domain.entity.Pauta;
/**
 * Interface responsável pelo banco de dados da Pauta.
 */
public interface PautaRepository extends JpaRepository<Pauta, Long>{

}
