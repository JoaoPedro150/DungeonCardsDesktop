package com.tsi.itemespecial;

import com.tsi.card.Card;
import com.tsi.card.CardInteragivel;
import com.tsi.chars.Heroi;

public class Moeda extends CardInteragivel {
	public Moeda(Card card) {
		super(card);
		setTipoCard(TipoCard.BOM);
	}
	@Override
	public Card interagir(Heroi heroi) {
		heroi.adicionarMoedas(getValor());
		return null;
	}
	
	@Override
	public Moeda clone() {
		return new Moeda(super.clone());
	}
}
