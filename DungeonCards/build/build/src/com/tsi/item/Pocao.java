package com.tsi.item;

import com.tsi.card.Card;
import com.tsi.card.CardInteragivel;
import com.tsi.chars.Heroi;

public class Pocao extends CardInteragivel {

	public Pocao(Card card) {
		super(card);
	}

	@Override
	public Card interagir(Heroi heroi) {
		if (getTipoCard().equals(TipoCard.BOM)) {
			heroi.setValor(heroi.getValor() + getValor());
		}
		else if (heroi.getValor() - getValor() > 1)
			heroi.setValor(heroi.getValor() - getValor());
		else
			heroi.setValor(1);
			
		return null;
	}
	
	@Override
	public Pocao clone() {
		return new Pocao(super.clone());
	}
}