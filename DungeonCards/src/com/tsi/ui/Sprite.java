package com.tsi.ui;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite  {
    public static final String CAMINHO = "com/tsi/sprites";
    public static final double TAMANHO_X = 105, TAMANHO_Y = 105;
    private String nomeImagem;

    public Sprite(String nomeImagem) {
		this.nomeImagem = nomeImagem;
    }

    public ImageView getImageView() {
        return new ImageView(new Image(CAMINHO + File.separator + nomeImagem, TAMANHO_X, TAMANHO_Y, false, false));
    }

    public ImageView getImageView(double tam_x, double tam_y) {
        return new ImageView(new Image(CAMINHO + File.separator + nomeImagem, tam_x, tam_y, false, false));
    }

    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }
}
