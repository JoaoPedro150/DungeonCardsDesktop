package com.tsi.app;

import com.tsi.card.Card;
import com.tsi.card.CardInteragivel;
import com.tsi.card.CardMutavel;
import com.tsi.card.Cards;
import com.tsi.chars.Heroi;
import com.tsi.chars.Inimigo;
import com.tsi.chars.InimigoTransformavel;
import com.tsi.exception.InteracaoException;
import com.tsi.exception.MovimentoException;
import com.tsi.grid.Grid;
import com.tsi.grid.Posicao;
import com.tsi.ui.Sprite;

import javafx.geometry.Pos;

@SuppressWarnings("unused")
public class Jogo {
	private Grid grid;

	// Mantém a referência do objeto heroi.
	private Heroi heroi;

   // Mantém a referência dos objetos modificaveis.
	private CardMutavel cardsModificaveis[];

   // Mantém a referência dos objetos que estão sob efeito de alguma pocao.
   private Card cardsSobEfeitoPocao[];

   private static final Posicao POSICAO_DE_INICIO = new Posicao(1, 1);

   public Jogo() {
	   	grid = new Grid(POSICAO_DE_INICIO);
	   	Cards.carregarCards();

		Card card;

		for (int i = 0; i < Grid.TAMANHO_X; i++)
			for (int j = 0; j < Grid.TAMANHO_Y; j++) {
				card = Cards.getRandomCard();
				card.setPosicao(new Posicao(i, j));
				grid.setCard(card);
			}

		heroi = new Heroi();
		heroi.setNome("Herói");
		heroi.setValor(500);
		heroi.setPosicao(POSICAO_DE_INICIO.clone());
		heroi.setSprite(new Sprite("Medusa.png"));
		grid.setCard(heroi);
	}

   public void interagir() throws InteracaoException {
	   Boolean interacao = grid.getPosicaoCursor().isAdjacente(heroi.getPosicao());

		if (interacao == Boolean.TRUE) {
			Card card = grid.getCard(grid.getPosicaoCursor());

			System.out.println(heroi.getNome() + " interagiu com " + card.getNome());

		   if (card != null) {
			   card = ((CardInteragivel)card).interagir(heroi);

			   if (heroi.getArma() != null && heroi.getArma().getValor() <= 0)
				   heroi.setArma(null);
		   }
		   if (card == null) {
			   card = Cards.getRandomCard();
			   card.setPosicao(heroi.getPosicao().clone());
			   grid.setCard(card);
			   card = heroi;
		   }

		   card.setPosicao(grid.getPosicaoCursor().clone());
		   grid.setCard(card);
		   return;
		}
		else if(interacao == null)
			throw new InteracaoException("Interação com o próprio herói.");

		throw new InteracaoException();
   }

   public Grid getGrid() {
	   return grid;
   }

	public void informacao() {
		System.out.println("Informacao");
	}
}
