package com.tsi.item;

import com.tsi.card.Card;
import com.tsi.card.CardDeAtaque;
import com.tsi.chars.Heroi;

public class Arma extends CardDeAtaque {

	public Arma(Card card) {
		super(card);
		inicializar();
	}
	public Arma() {
		inicializar();
	}

	public void inicializar() {
		setTipoCard(TipoCard.BOM);
	}

	@Override
	public Card interagir(Heroi heroi) {

		if (heroi.getArma() == null || heroi.getArma().getValor() < getValor())
			heroi.setArma(this);

		return null;
	}

	@Override
	public Arma clone() {
		return new Arma(super.clone());
	}
}
