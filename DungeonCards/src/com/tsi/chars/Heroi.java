package com.tsi.chars;

import com.tsi.card.Card;
import com.tsi.item.Arma;
import com.tsi.item.Pocao;
import com.tsi.ui.Sprite;

public class Heroi extends Card {

	private Arma arma;

	private Pocao pocao;

	private int qtdMoedas;

	public Heroi() {
		setValor(10);
		setNome("Herói");
		setSprite(new Sprite("Esqueleto.png"));
		setInformacao("O cavaleiro adora tomar chá enquanto lê um bom livro. Ele também gosta de matar monstros e possui muita vida.");
	}

	public Arma getArma() {
		return arma;
	}

	public void setArma(Arma arma) {
		this.arma = arma;
	}

	public Pocao getPocao() {
		return pocao;
	}

	public void setPocao(Pocao pocao) {
		this.pocao = pocao;
	}

	public void adicionarMoedas(int valor) {
		qtdMoedas += valor;
	}

	public int getQtdMoedas() {
		return qtdMoedas;
	}
}
