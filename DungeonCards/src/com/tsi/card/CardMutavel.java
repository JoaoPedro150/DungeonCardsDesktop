package com.tsi.card;


public abstract class CardMutavel extends CardInteragivel {

	public CardMutavel(Card card) {
		super(card);
	}

	public abstract void novaRodada();
}
