package com.tsi.itemespecial;

import com.tsi.card.Card;
import com.tsi.card.CardInteragivel;
import com.tsi.chars.Heroi;

public class Moeda extends CardInteragivel {
	public Moeda(Card card) {
		super(card);
		setTipoCard(TipoCard.BOM);
		setInformacao("Acumule o máximo que puder para torcar por valiosos upgrades! No topo, à esquerda estão"
				+ " todas as moedas que você já obteve e à direita as obtidas nessa partida!");
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
