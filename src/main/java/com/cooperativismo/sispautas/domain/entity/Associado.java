package com.cooperativismo.sispautas.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 *Entidade responsável por manter Associado na cooperativa.
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Associado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;
	
	@Setter
	@Column(nullable = false, length = 11)
	private String cpf;
	
	@Setter
	@Column(nullable = false, length = 100)
	private String nome;
	
}
