package com.cooperativismo.sispautas.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cooperativismo.sispautas.domain.entity.Voto;

public interface VotoRepository extends JpaRepository<Voto, Long>{

	Optional<Voto> findTopByAutorIdAndPautaId(Long idAutor, Long idPauta);

}
