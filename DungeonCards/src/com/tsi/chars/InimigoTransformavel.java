package com.tsi.chars;

import java.util.Random;

import com.tsi.card.Card;

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
		Inimigo inimigo = (Inimigo) super.interagir(heroi);

		if (inimigo == null) {
			inimigoATransformar.setValor(new Random().nextInt((getValor() > 1) ? getValor() - 1 : getValor()) + 1);
			return inimigoATransformar.clone();
		}
		else
			return inimigo;
	}

	@Override
	public Inimigo clone() {
		return new InimigoTransformavel(super.clone(), inimigoATransformar.clone());
	}
}
