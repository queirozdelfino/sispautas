package com.cooperativismo.sispautas.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Entity(name = "Associado")
@Table(name = "associado")
public class Associado {
	
	@JsonProperty(access = Access.READ_ONLY)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;
	
	@Setter
	@Column(nullable = false, length = 11)
	@Schema(example = "99087586086")
	private String cpf;
	
	@Setter
	@Column(nullable = false, length = 100)
	@Schema(example = "João dos Reis")
	private String nome;
	
}
