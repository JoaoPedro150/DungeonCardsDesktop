package com.tsi.exception;

import com.tsi.grid.Posicao;

/**Classe de exceção para sinalizar quando é feita uma interação com um célula do grid não adjacente
 * @see Posicao*/
public class InteracaoException extends Exception {
	private static final long serialVersionUID = 1L;

	public InteracaoException() {
		super("Interação não permitida");
	}
	
	public InteracaoException(String mensagem) {
		super(mensagem);
	}
}
