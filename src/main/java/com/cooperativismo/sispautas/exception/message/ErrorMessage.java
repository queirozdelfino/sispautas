package com.cooperativismo.sispautas.exception.message;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorMessage {
	
	ERRO_INTERNO("Erro interno. Tente novamente mais tarde."),
	CPF_INVALIDO("CPF inválido."),
	CADASTO_DUPLICADO("Cadastro duplicado."),
	DADO_VAZIO(" não pode ser vazio."),
	DADO_MAIOR(" não pode ser maior que ");

	private final String message;

	public String getMessage() {
		return this.message;
	}
	
	public String erroCampoVazio(String campo) {
		return campo + this.message;
	}
	public String erroCampoMaior(String campo, int tamanho) {
		return campo + this.message + tamanho;
	}
}
