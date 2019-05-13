package com.tsi.app;

import com.tsi.card.Card;
import com.tsi.card.CardInteragivel;
import com.tsi.card.CardMutavel;
import com.tsi.card.Cards;
import com.tsi.chars.Heroi;
import com.tsi.exception.InteracaoException;
import com.tsi.exception.MovimentoException;
import com.tsi.grid.Grid;
import com.tsi.grid.Posicao;
import com.tsi.ui.Sprite;

import javafx.geometry.Pos;

@SuppressWarnings("unused")
public class Jogo {
	private Grid grid;

	private Cards cards;

	// Mantém a referência do objeto heroi.
	private Heroi heroi;

   // Mantém a referência dos objetos modificaveis.
	private CardMutavel cardsModificaveis[];

   // Mantém a referência dos objetos que estão sob efeito de alguma pocao.
   private Card cardsSobEfeitoPocao[];

   public Jogo() {
	   	grid = new Grid();
		cards = new Cards();

		Card card;

		for (int i = 0; i < Grid.TAMANHO_X; i++)
			for (int j = 0; j < Grid.TAMANHO_Y; j++) {
				card = cards.getRandomCard();
				card.setPosicao(new Posicao(i, j));
				grid.setCard(card);
			}

		heroi = new Heroi();
		heroi.setNome("Herói");
		heroi.setValor(1000);
		heroi.setPosicao(new Posicao(0, 0));
		heroi.setSprite(new Sprite("Medusa.png"));
		grid.setCard(heroi);
	}

   public void interagir() throws InteracaoException {
	   Posicao pos = grid.interagir();
	   Card card = grid.getCard(pos);


	   if (card != null) {
		   card.getAudio().play();
		   card = ((CardInteragivel)card).interagir(heroi);
	   }
	   if (card == null) {
		   card = cards.getRandomCard();
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

   public Cards getCards() {
	   return cards;
   }

	public void informacao() {
		System.out.println("Informacao");
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
