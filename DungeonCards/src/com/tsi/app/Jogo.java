package com.tsi.app;

import com.tsi.card.Card;
import com.tsi.card.CardInteragivel;
import com.tsi.card.CardMutavel;
import com.tsi.card.Cards;
import com.tsi.chars.Heroi;
import com.tsi.chars.Inimigo;
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

   public Jogo() {
	   	grid = new Grid();
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
		heroi.setPosicao(new Posicao(1, 1));
		heroi.setSprite(new Sprite("Medusa.png"));
		grid.setCard(heroi);
		grid.setPosicaoHeroi(heroi.getPosicao().clone());
	}

   public void interagir() throws InteracaoException {
	   Posicao pos = grid.interagir();
	   Card card = grid.getCard(pos);

	   if (card != null) {
		   card.getAudio().play();

		   if (heroi.getArma() == null || !(card instanceof Inimigo))
			   card = ((CardInteragivel)card).interagir(heroi);
		   else {
			   card = ((CardInteragivel)card).receberAtaque(heroi.getArma());

			   if (card != null)
				   heroi.setArma(null);
		   }
	   }
	   if (card == null) {
		   card = Cards.getRandomCard();
		   card.setPosicao(heroi.getPosicao().clone());
		   grid.setCard(card);
		   grid.setPosicaoHeroi(pos.clone());
		   card = heroi;
	   }
	   card.setPosicao(pos);
	   grid.setCard(card);
   }

   public Grid getGrid() {
	   return grid;
   }

	public void informacao() {
		System.out.println("Informacao");
	}
}
