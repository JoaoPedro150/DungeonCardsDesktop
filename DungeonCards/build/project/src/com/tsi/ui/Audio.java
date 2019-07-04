package com.tsi.ui;

import java.io.File;

import javafx.scene.media.AudioClip;

public class Audio {
	public static final String CAMINHO = "/com/tsi/audio";
	private String nomeAudio;
	private AudioClip audioClip;

	public Audio(String nomeAudio) {
		setNomeAudio(nomeAudio);
		audioClip = new AudioClip(getClass().getResource(CAMINHO + File.separator + nomeAudio).toString());
	}

	public void play(){
		audioClip.play();
	}
	
	public void stop(){
		audioClip.stop();
	}
	
	
	public void ajustarVolume(double volume) {
		audioClip.setVolume(volume);
	}

	public String getNomeAudio() {
		return nomeAudio;
	}

	public void setNomeAudio(String nomeAudio) {
		this.nomeAudio = nomeAudio;
	}

}
