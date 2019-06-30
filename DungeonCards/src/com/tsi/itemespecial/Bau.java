package com.tsi.itemespecial;

import com.tsi.card.Card;
import com.tsi.card.CardInteragivel;
import com.tsi.card.Cards;
import com.tsi.chars.Heroi;

public class Bau extends CardInteragivel {

	public Bau(Card card) {
		super(card);
	}

	@Override
	public Card interagir(Heroi heroi) {
		return Cards.getRandomCard(getTipoCard(), heroi.getQtdMoedasPartida());
	}

	@Override
	public void setTipoCard(TipoCard tipo) {
		setInformacao("Contém algo " + tipo.toString().toLowerCase() + ".");
		super.setTipoCard(tipo);
	}

	@Override
	public Card clone() {
		return new Bau(super.clone());
	}
}
