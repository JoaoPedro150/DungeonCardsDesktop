package com.tsi.card;

public abstract class CardDeAtaque extends CardInteragivel {
	public enum TipoArma{FOGO, GELO, VENENO};

    private TipoArma tipoArma;
    private int alcance;

	public CardDeAtaque() {
	}
    public CardDeAtaque(Card card) {
		super(card);
	}
    public TipoArma getTipoArma() {
		return tipoArma;
	}
    public void setTipoArma(TipoArma tipoArma) {
		this.tipoArma = tipoArma;
	}
    public int getAlcance() {
		return alcance;
	}
    public void setAlcance(int alcance) {
		this.alcance = alcance;
	}
}
