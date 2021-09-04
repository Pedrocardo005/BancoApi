package com.banco.application.exceptions;

public class SenhaInvalidaException extends RuntimeException {

	public SenhaInvalidaException() {
		super("Senha Invalida");
	}
}
