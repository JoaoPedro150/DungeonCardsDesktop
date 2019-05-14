package com.tsi.card;

import com.tsi.chars.Heroi;

public abstract class CardInteragivel extends Card {

	public CardInteragivel() {

	}

	public CardInteragivel(Card card) {
		super(card);
	}

	public abstract Card interagir(Heroi heroi);

	public Card receberAtaque(CardDeAtaque cardAtaque) {
		return receberAtaque(cardAtaque);
	}

	protected Card receberAtaque(Card card) {
		if (getValor() - card.getValor() <= 0)
			return null;
		else
			setValor(getValor() - card.getValor());

		return this;
	}
}
