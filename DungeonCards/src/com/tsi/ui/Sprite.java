package com.tsi.ui;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite  {
    public static final String CAMINHO = "com/tsi/sprites";
    public static final double TAMANHO_X = 105, TAMANHO_Y = 105;
    private String nomeImagem;
    private ImageView imageView;

    public Sprite(String nomeImagem) {
    	if (nomeImagem != null) {
    		this.nomeImagem = nomeImagem;
    		imageView = new ImageView(new Image(CAMINHO + File.separator + nomeImagem, TAMANHO_X, TAMANHO_Y, false, false));
    	}
    }

    public ImageView getImagem() {
        return imageView;
    }

    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

    @Override
    public Sprite clone() {
        return new Sprite(nomeImagem);
    }
}
