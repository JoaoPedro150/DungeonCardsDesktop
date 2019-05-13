package com.tsi.ui;

import java.io.File;

import javafx.scene.media.AudioClip;

public class Audio {
	public static final String CAMINHO = "/com/tsi/audio";
	private String nomeAudio;
	private AudioClip audioClip;

	public Audio(String nomeAudio) {
		setNomeAudio(nomeAudio);
	}

	public void play(){
		audioClip = new AudioClip(getClass().getResource(CAMINHO + File.separator + nomeAudio).toString());
		audioClip.play();
	}

	public String getNomeAudio() {
		return nomeAudio;
	}

	public void setNomeAudio(String nomeAudio) {
		this.nomeAudio = nomeAudio;
	}

}
