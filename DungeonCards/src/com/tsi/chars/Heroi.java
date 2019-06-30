package com.tsi.chars;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.tsi.card.Card;
import com.tsi.item.Arma;
import com.tsi.item.Pocao;
import com.tsi.ui.Sprite;

public class Heroi extends Card implements Serializable{
	private static final long serialVersionUID = 1L;

	private transient Arma arma;

	private transient Pocao pocao;

	private int maxVida;

	private int qtdMoedas;

	private transient int qtdMoedasPartida;

	public Heroi(int vida) {
		maxVida = vida;
		setValor(vida);
		setSprite(new Sprite("Heroi.png"));
		setNome("Herói");
		setInformacao("O cavaleiro adora tomar chá enquanto lê um bom livro. Ele também gosta de matar monstros e possui muita vida.");
	}

	@Override
	public String getNome() {
		// + ((arma == null) ? "" : " (" + arma.getValor() + ")")
		return super.getNome();
	}

	@Override
	public void setValor(int valor) {
		if (valor < maxVida)
			super.setValor(valor);
		else
			super.setValor(maxVida);
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
		qtdMoedasPartida += valor;
		qtdMoedas += valor;
	}

	public int getQtdMoedas() {
		return qtdMoedas;
	}
	private void writeObject(ObjectOutputStream oos) throws IOException {
		oos.defaultWriteObject();
	}
	private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
		ois.defaultReadObject();
	}

	public static class ArquivoHeroi{
		private final String nomeArquivo = "stats.dat";

		public boolean salvarHeroi(Heroi heroi) {
			try(ObjectOutputStream objectInputStream = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {

				objectInputStream.writeObject(heroi);

				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}

		public Heroi carregarHeroi() {
			try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(nomeArquivo))) {

				Heroi heroi = (Heroi) objectInputStream.readObject();

				return heroi;
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
		}

	}


	public int getQtdMoedasPartida() {
		return qtdMoedasPartida;
	}

	public void setQtdMoedasPartida(int qtdMoedasPartida) {
		this.qtdMoedasPartida = qtdMoedasPartida;
	}
}
