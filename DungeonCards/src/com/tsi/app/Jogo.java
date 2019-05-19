package com.tsi.app;

import java.io.IOException;

import com.tsi.card.Card;
import com.tsi.card.CardInteragivel;
import com.tsi.card.CardMutavel;
import com.tsi.card.Cards;
import com.tsi.card.Card.TipoCard;
import com.tsi.chars.Heroi;
import com.tsi.chars.Inimigo;
import com.tsi.chars.InimigoTransformavel;
import com.tsi.exception.InteracaoException;
import com.tsi.exception.MovimentoException;
import com.tsi.grid.Grid;
import com.tsi.grid.Posicao;
import com.tsi.ui.Ajuda;
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

   private Ajuda ajuda;

   private static final Posicao POSICAO_DE_INICIO = new Posicao(1, 1);

   private int qtdCardsRuim = 0;
   private int qtdCardsBom = 0;

   public Jogo() {
	   	grid = new Grid(POSICAO_DE_INICIO);
	   	Cards.carregarCards();

		Card card;

		for (int i = 0; i < Grid.TAMANHO_X; i++)
			for (int j = 0; j < Grid.TAMANHO_Y; j++) {
				card = getRandomCard();
				card.setPosicao(new Posicao(i, j));
				grid.setCard(card);
			}

		heroi = new Heroi();
		heroi.setNome("Herói");
		heroi.setValor(500);
		heroi.setPosicao(POSICAO_DE_INICIO.clone());
		heroi.setSprite(new Sprite("Heroi.png"));
		grid.setCard(heroi);
		ajuda = Ajuda.getInstance(DungeonCards.getStage());
	}

   private Card getRandomCard() {
	   Card card;

	   if (qtdCardsRuim - qtdCardsBom > 1) {
			card = Cards.getRandomCard(TipoCard.BOM);
			qtdCardsBom++;
		}
		else if (qtdCardsBom - qtdCardsRuim > 1) {
			card = Cards.getRandomCard(TipoCard.RUIM);
			qtdCardsRuim++;
		}
		else {
			card = Cards.getRandomCard();

			if (card.getTipoCard().equals(TipoCard.BOM))
				qtdCardsBom++;
			else
				qtdCardsRuim++;
		}

	   return card;
   }

   public boolean interagir() throws InteracaoException {
	   Boolean interacao = grid.getPosicaoCursor().isAdjacente(heroi.getPosicao());
	   boolean movimentoHeroi = false;

		if (interacao == Boolean.TRUE) {
			Card card = grid.getCard(grid.getPosicaoCursor());
			Card resultadoInteracao = null;

		   if (card != null) {
			   resultadoInteracao = ((CardInteragivel)card).interagir(heroi);

			   if (heroi.getArma() != null && heroi.getArma().getValor() <= 0)
				   heroi.setArma(null);
		   }
		   if (resultadoInteracao == null) {
			   if (card.getTipoCard().equals(TipoCard.BOM))
					qtdCardsBom--;
				else
					qtdCardsRuim--;

			   card = getRandomCard();
			   card.setPosicao(heroi.getPosicao().clone());
			   grid.setCard(card);
			   card = heroi;
			   movimentoHeroi = true;
		   }
		   else
			   card = resultadoInteracao;

		   card.setPosicao(grid.getPosicaoCursor().clone());
		   grid.setCard(card);
		   return movimentoHeroi;
		}
		else if(interacao == null)
			throw new InteracaoException("Interação com o próprio herói.");

		throw new InteracaoException();
   }


   public Grid getGrid() {
	   return grid;
   }

	public void informacao() {
		Card card = grid.getCard(grid.getPosicaoCursor());
		ajuda.exibirAjuda(card.getInformacao(), card.getNome());

	}

	public void fecharInfo() {
		ajuda.esconderAjuda();

	}
}
