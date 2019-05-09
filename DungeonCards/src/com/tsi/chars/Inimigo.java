package com.tsi.chars;

import com.tsi.card.Card;
import com.tsi.card.CardDeAtaque;
import com.tsi.card.CardInteragivel;
import com.tsi.item.Arma;

public class Inimigo extends CardInteragivel {
	public Inimigo() {
		setTipoCard(TipoCard.RUIM);
		setInformacao("Inimigo comum. Causa dano igual aos seus pontos de vida.");
	}

	public Inimigo(Card card) {
		this();
		setInformacao(card.getInformacao());
		setNome(card.getNome());
		setPosicao(card.getPosicao());
		setSprite(card.getSprite());
		setValor(card.getValor());
	}

	@Override
	public Card receberAtaque(CardDeAtaque cardAtaque) {
		if (cardAtaque instanceof Arma)
			return lutar(cardAtaque);
		else
			return super.receberAtaque(cardAtaque);
	}

	@Override
	public Card interagir(Heroi heroi) {
		return lutar(heroi);
	}

	protected Inimigo lutar(Card card) {
		Inimigo inimigo = (Inimigo)receberAtaque(card);

		card.setValor(card.getValor() - getValor());

		return inimigo;
	}

	@Override
	public Inimigo clone() {
		return new Inimigo(super.clone());
	}
}
