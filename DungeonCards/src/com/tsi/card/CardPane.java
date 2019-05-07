package com.tsi.card;


import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class CardPane extends BorderPane {
	private Card card;

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
		setTop(new Text("" + card.getValor()));

		BorderPane pane = new BorderPane(card.getSprite().getImagem(),
				null, null, new Text(card.getNome()), null);
		setCenter(pane);
		this.setVisible(true);
	}





}
