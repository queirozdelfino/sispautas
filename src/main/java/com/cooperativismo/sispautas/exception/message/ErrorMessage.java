package com.cooperativismo.sispautas.exception.message;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorMessage {
	
	ERRO_INTERNO("Erro interno. Tente novamente mais tarde."),
	CPF_INVALIDO("CPF inválido."),
	CADASTO_DUPLICADO("Cadastro duplicado."),
	DADO_VAZIO(" não pode ser vazio.");

	private final String message;

	public String getMessage() {
		return this.message;
	}
	
	public String erroCampoVazio(String campo) {
		return campo + this.message;
	}
}
