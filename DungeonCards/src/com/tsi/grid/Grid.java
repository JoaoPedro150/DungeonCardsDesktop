package com.tsi.grid;

import com.tsi.card.Card;
import com.tsi.card.CardMutavel;
import com.tsi.chars.Heroi;
import com.tsi.exception.InteracaoException;
import com.tsi.exception.MovimentoException;

public class Grid {

	//Representa o grid do jogo
	private int[][] grid;

	//Coordenada x(posicao 0), y(posicao 1)
	private Posicao posicaoCursor, posicaoHeroi;


	public static final int CIMA = 1, BAIXO = 2, DIREITA = 3, ESQUERDA = 4;

	@SuppressWarnings("unused")
	private Card[][] cards;

	 // Mantém a referência do objeto heroi.
    @SuppressWarnings("unused")
	private static Heroi heroi;

    // Mantém a referência dos objetos modificaveis.
    @SuppressWarnings("unused")
	private static CardMutavel cardsModificaveis[];

    // Mantém a referência dos objetos que estão sob efeito de alguma pocao.
    @SuppressWarnings("unused")
	private static Card cardsSobEfeitoPocao[];



	public Grid() {
		cards = new Card[3][3];
		grid = new int[3][3];
		posicaoCursor = new Posicao();
		posicaoHeroi = new Posicao();
	}

	public Posicao moverCursor(int direcao) throws MovimentoException {

		switch(direcao) {
		case BAIXO:
			System.out.println("Down");
			return moverCursor(0, 1);

		case CIMA:
			System.out.println("Up");
			return moverCursor(0, -1);
		case ESQUERDA:
			System.out.println("Left");
			return moverCursor(-1, 0);
		case DIREITA:
			System.out.println("Right");
			return moverCursor(1, 0);
		default:
			throw new MovimentoException();

		}



	}

	private Posicao moverCursor(int x, int y) throws MovimentoException{
		int oldX = posicaoCursor.getX(), oldY = posicaoCursor.getY();
		try {

			grid[oldX + x][oldY + y] = 1;
			posicaoCursor.addX(x);
			posicaoCursor.addY(y);
			grid[oldX][oldY] = 0;
		}catch(ArrayIndexOutOfBoundsException e) {
			throw new MovimentoException();
		}

		novaRodada();
		return posicaoCursor;

	}

	public Posicao interagir() throws InteracaoException {
		Boolean interacao = posicaoCursor.isAdjacente(posicaoHeroi);
		if (interacao == Boolean.TRUE) {
			System.out.println("Interagiu com " + posicaoCursor.toString());
			return posicaoCursor;
		}
		else if(interacao == null)
			throw new InteracaoException("Interação com o próprio herói.");

		throw new InteracaoException();
	}

	public Posicao getPosicaoCursor() {
		return posicaoCursor;
	}

	public Posicao getPosicaoHeroi() {
		return posicaoHeroi;
	}

	private static void novaRodada() {
        // cardsModificaveis[todos].novaRodada();

        //  if cardsSobEfeitoPocao[todos] instanceof Heroi || Inimigo {
        //    cardsSobEfeitoPocao[todos].getPocao().efeito(
        //        cardsSobEfeitoPocao[todos]);
        //  }
        // }

		//TODO
    }





}
