package com.tsi.card;

import com.tsi.grid.Posicao;
import com.tsi.ui.Sprite;


public class Card {
	/**Objeto sprite que representa o card*/
	private Sprite sprite;

    /**Nome do Card */
    private String nome = "Vazio";

    /**  Valor do Card, pode representar qualquer valor necess�rio ao card*/
    private int valor;

    /**Uma mensagem informativa sobre o card.*/
    private String informacao;

    /**Tipo do card, bom ou ruim*/
    public enum TipoCard{BOM, RUIM};

    private TipoCard tipo;

    /** Posi��o na matriz do jogo.*/
    private Posicao posicao;

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public String getInformacao() {
		return informacao;
	}

	public void setInformacao(String informacao) {
		this.informacao = informacao;
	}

	public Posicao getPosicao() {
		return posicao;
	}

	public void setPosicao(Posicao posicao) {
		this.posicao = posicao;
	}

	public TipoCard getTipoCard() {
		return tipo;
	}

	public void setTipoCard(TipoCard tipo) {
		this.tipo = tipo;
	}

	@Override
	public Card clone() {
		Card card = new Card();
		card.setInformacao(informacao);
		card.setNome(nome);

		if (posicao != null)
			card.setPosicao(posicao.clone());

		if (sprite != null)
			card.setSprite(sprite.clone());

		if (tipo != null)
			card.setTipoCard(TipoCard.valueOf(tipo.toString()));

		card.setValor(valor);

		return card;
	}
}
