package com.tsi.grid;

import com.tsi.card.Card;
import com.tsi.exception.MovimentoException;

public class Grid {

	//Representa o grid do jogo
	private int[][] grid;

	//Coordenada x(posicao 0), y(posicao 1)
	private Posicao posicaoCursor;

	public static final int CIMA = 1, BAIXO = 2, DIREITA = 3, ESQUERDA = 4;
	public static final int TAMANHO_X = 3, TAMANHO_Y = 3;

	private Card[][] cards;

	public Grid() {
		cards = new Card[TAMANHO_X][TAMANHO_Y];
		grid = new int[TAMANHO_X][TAMANHO_Y];
		posicaoCursor = new Posicao();
	}
	public Grid(Posicao posicaoDeInicio) {
		this();
		posicaoCursor = posicaoDeInicio;
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

	public static int inverterDirecao(int direcao) {
		switch (direcao) {
		case BAIXO: return CIMA;
		case CIMA: return BAIXO;
		case DIREITA: return ESQUERDA;
		case ESQUERDA: return DIREITA;
		default: return 0;
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

		return posicaoCursor;
	}

	public Posicao getPosicaoCursor() {
		return posicaoCursor;
	}

	public Card getCard(Posicao posicao) {
		return cards[posicao.getX()][posicao.getY()];
	}

	public void setCard(Card card) {
		cards[card.getPosicao().getX()][card.getPosicao().getY()] = card;
	}

	public void removeCard(Posicao posicao) {
		cards[posicao.getX()][posicao.getY()] = null;
	}
}
