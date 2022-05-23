package com.cooperativismo.sispautas.exception.message;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorMessage {
	
	ERRO_INTERNO("Erro interno. Tente novamente mais tarde."),
	DADOS_INVALIDOS("Dados inválidos");

	private final String message;

	public String getMessage() {
		return this.message;
	}
}
