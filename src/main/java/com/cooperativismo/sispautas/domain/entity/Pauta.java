package com.cooperativismo.sispautas.domain.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.cooperativismo.sispautas.domain.enums.Decisao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Pauta")
@Table(name = "pauta")
public class Pauta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	@Setter
	@Column(nullable = false, length = 40)
	private String titulo;
	
	@Setter
	@Column(length = 300)
	private String detalhes;
	
	@Setter
	@ManyToOne(fetch = FetchType.LAZY)
	private Associado autor;
	
	@Setter
	@Column
	private LocalDateTime dataLimite;
	
	@JsonIgnore
	@OneToMany(
	        mappedBy = "pauta",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private List<Voto> votos = new ArrayList<>();
	
	@Setter
	@Enumerated(EnumType.STRING)
	private Decisao decisaoFinal;
	
}
