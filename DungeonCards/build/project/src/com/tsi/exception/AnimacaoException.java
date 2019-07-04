package com.tsi.exception;

public class AnimacaoException extends Exception {
	private static final long serialVersionUID = 1L;

	public AnimacaoException() {
		super("Ocorreu um erro durante a animação");
	}

	public AnimacaoException(String mensagem) {
		super(mensagem);
	}

}
