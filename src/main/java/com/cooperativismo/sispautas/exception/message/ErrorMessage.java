package com.cooperativismo.sispautas.exception.message;

import lombok.RequiredArgsConstructor;

/**
 * Classe responsável por padronizar mensagens de erro pelo sistema.
 */
@RequiredArgsConstructor
public enum ErrorMessage {
	
	ERRO_INTERNO("Erro interno. Tente novamente mais tarde."),
	CPF_INVALIDO("CPF inválido."),
	CPF_NAO_ENCONTRADO ("O CPF informado não foi encontrado na base de dados."),
	CPF_NAO_AUTORIZADO ("CPF não permitido para votar no sistema."),
	PAUTA_NAO_ENCONTRADA("A Pauta informada não foi encontrada na base de dados."),
	PAUTA_JA_INICIADA("A Pauta informada já se encontra iniciada. Por favor, inclua uma nova pauta."),
	PAUTA_NAO_INICIADA("Não foi possível obter resultado, pois a sessão da pauta não foi iniciada."),
	PAUTA_ABERTA("Não foi possível obter resultado, pois a sessão da pauta aida está aberta."),
	VOTO_EXISTENTE("O associado informado já votou na pauta escolhida."),
	PAUTA_VENCIDA("O período de votação dessa pauta já se encerrou."),
	CADASTO_DUPLICADO("Cadastro duplicado."),
	DADO_VAZIO(" não pode ser vazio."),
	DADO_MAIOR(" não pode ser maior que "),
	VALIDATION_ERROR("VALIDATION ERROR");

	private final String message;

	public String getMessage() {
		return this.message;
	}
	
	public String erroCampoVazio(String campo) {
		return campo + this.message;
	}
	public String erroCampoMaior(String campo, String tamanho) {
		return campo + this.message + tamanho;
	}
}
