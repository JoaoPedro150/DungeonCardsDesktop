package com.tsi.chars;

import java.util.Random;

import com.tsi.card.Card;
import com.tsi.itemespecial.Moeda;

public class InimigoTransformavel<T extends Card> extends Inimigo {
	T inimigoATransformar;

	public InimigoTransformavel(Card card, T inimigoATransformar) {
		super(card);
		this.inimigoATransformar = inimigoATransformar;
		setInformacao("Transforma-se em " + this.inimigoATransformar.getNome());
	}

	public Card getInimigoATransformar() {
		return inimigoATransformar;
	}

	public void setInimigoATransformar(T inimigoATransformar) {
		this.inimigoATransformar = inimigoATransformar;
	}

	@Override
	public Card interagir(Heroi heroi) {
		if (heroi.getArma() != null) {
			Card card = super.interagir(heroi);

			if (card != null && card instanceof Moeda) {
				inimigoATransformar.setValor(new Random().nextInt((getValor() > 1) ? getValor() - 1 : getValor()) + 1);
				return inimigoATransformar.clone();
			}
			else
				return card;
		}
		else
			return super.interagir(heroi);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Inimigo clone() {
		return new InimigoTransformavel(super.clone(), inimigoATransformar.clone());
	}
}
