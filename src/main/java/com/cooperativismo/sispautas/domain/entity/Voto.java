package com.cooperativismo.sispautas.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.cooperativismo.sispautas.domain.enums.Decisao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Voto")
@Table(name = "voto")
public class Voto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;
	
	@Setter
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Decisao decisao;
	
	@Setter
	@ManyToOne(cascade = CascadeType.ALL)
	private Associado autor;
	
	@Setter
	@ManyToOne(fetch = FetchType.LAZY)
	private Pauta pauta;
	
}
