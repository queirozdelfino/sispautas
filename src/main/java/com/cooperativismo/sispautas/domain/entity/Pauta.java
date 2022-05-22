package com.cooperativismo.sispautas.domain.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cooperativismo.sispautas.domain.enums.Decisao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *Entidade responsável por manter pautas da cooperativa*/

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Pauta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	@Setter
	@Column(nullable = false, length = 40)
	private String titulo;
	
	@Setter
	@ManyToOne
	private Associado autor;
	
	@Setter
	@Column(nullable = false)
	private LocalDateTime dataLimite;
	
	@Setter
	@OneToMany
	private List<Voto> votos;
	
	@Setter
	@Enumerated(EnumType.STRING)
	private Decisao decisaoFinal;
	
}
