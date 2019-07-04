package com.tsi.chars;

import java.util.Random;

import com.tsi.card.Card;
import com.tsi.card.CardInteragivel;
import com.tsi.card.Cards;
import com.tsi.itemespecial.Moeda;

public class Inimigo extends CardInteragivel {
	protected int vidaInicial;
	
	public Inimigo() {
		inimigoComum();
	}

	public Inimigo(Card card) {
		super(card);
		inimigoComum();
	}

	private void inimigoComum() {
		setTipoCard(TipoCard.RUIM);
		setInformacao("Inimigo comum. Causa dano igual aos seus pontos de vida.");
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
				Moeda moeda = Cards.getMoeda();
				
				moeda.setValor(new Random().nextInt((vidaInicial / 2) + 1) + (vidaInicial / 2) + 1);
				
				return moeda;
			}
			else {
				setValor(vidaRestante);
				return this;
			}
		}
	}
	
	@Override
	public void setValor(int valor) {
		vidaInicial = valor;
		super.setValor(valor);
	}

	@Override
	public Inimigo clone() {
		Inimigo inimigo = new Inimigo(super.clone());
		inimigo.vidaInicial = vidaInicial;
		return inimigo;
	}
}
