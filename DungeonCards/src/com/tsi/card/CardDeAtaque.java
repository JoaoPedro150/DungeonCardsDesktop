package com.tsi.card;

public abstract class CardDeAtaque extends CardInteragivel {
    public enum TipoArma{FOGO, GELO, VENENO};

    private TipoArma tipoArma;

    public TipoArma getTipoArma() {
		return tipoArma;
	}
    public void setTipoArma(TipoArma tipoArma) {
		this.tipoArma = tipoArma;
	}
}
