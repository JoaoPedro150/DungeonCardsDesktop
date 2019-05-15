package com.tsi.chars;

import com.tsi.card.Card;
import com.tsi.card.CardInteragivel;

public class Inimigo extends CardInteragivel {
	public Inimigo() {
		setTipoCard(TipoCard.RUIM);
		setInformacao("Inimigo comum. Causa dano igual aos seus pontos de vida.");
	}

	public Inimigo(Card card) {
		super(card);
	}

	@Override
	public Card interagir(Heroi heroi) {
		if (heroi.getArma() == null) {
			Inimigo inimigo = (Inimigo)receberAtaque(heroi);

			heroi.setValor(heroi.getValor() - getValor());

			return inimigo;
		}
		else {
			int vidaRestante = getValor() - heroi.getArma().getValor();

			heroi.getArma().setValor(heroi.getArma().getValor() - getValor());

			if (vidaRestante <= 0) {
				return null;
			}
			else {
				setValor(vidaRestante);
				return this;
			}
		}
	}

	@Override
	public Inimigo clone() {
		return new Inimigo(super.clone());
	}
}
