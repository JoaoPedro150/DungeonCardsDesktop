package com.tsi.exception;

import com.tsi.grid.Posicao;

/**Classe de exceção para sinalizar quando um movimento do cursor ultrapassa os limites do grid de jogo.
 * @see Posicao*/
public class MovimentoException extends Exception {

	private static final long serialVersionUID = 1L;

	public MovimentoException() {
		super("Movimento não permitido");
	}
	
	public MovimentoException(String mensagem) {
		super(mensagem);
	}
}
