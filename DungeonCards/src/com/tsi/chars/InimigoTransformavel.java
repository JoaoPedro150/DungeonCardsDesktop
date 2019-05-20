package com.tsi.chars;

import java.util.Random;

import com.tsi.card.Card;
import com.tsi.itemespecial.Moeda;

public class InimigoTransformavel extends Inimigo {
	Inimigo inimigoATransformar;

	public InimigoTransformavel(Card card, Inimigo inimigoATransformar) {
		super(card);
		this.inimigoATransformar = inimigoATransformar;
		setInformacao("Transforma-se em " + this.inimigoATransformar.getNome());
	}

	public Inimigo getInimigoATransformar() {
		return inimigoATransformar;
	}

	public void setInimigoATransformar(Inimigo inimigoATransformar) {
		this.inimigoATransformar = inimigoATransformar;
	}

	@Override
	public Card interagir(Heroi heroi) {
		if (heroi.getArma() != null) {
			Card card = super.interagir(heroi);

			if (card != null && card instanceof Moeda) {
				inimigoATransformar.setValor(new Random().nextInt((getValor() > 1) ? getValor() - 1 : getValor()) + 1);
				inimigoATransformar.vidaInicial = card.getValor();
				return inimigoATransformar.clone();
			}
			else
				return card;
		}
		else
			return super.interagir(heroi);
	}

	@Override
	public Inimigo clone() {
		return new InimigoTransformavel(super.clone(), inimigoATransformar.clone());
	}
}
