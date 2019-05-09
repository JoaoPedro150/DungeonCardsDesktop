package com.tsi.itemespecial;

import com.tsi.card.Card;
import com.tsi.card.CardInteragivel;
import com.tsi.chars.Heroi;

public class Moeda extends CardInteragivel {
	@Override
	public Card interagir(Heroi heroi) {
		heroi.adicionarMoedas(getValor());
		return null;
	}
}
