package com.tsi.ui;


import com.tsi.card.Card;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class CardPane extends BorderPane {
	private Card card;

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;

		if (card != null) {
			setTop(new Text("" + card.getValor()));
			BorderPane pane;

			if (card.getSprite() != null) {
				pane = new BorderPane(card.getSprite().clone().getImagem(), null, null, new Text(card.getNome()), null);
			}
			else {
				pane = new BorderPane(null, null, null, new Text(card.getNome()), null);
			}

			setCenter(pane);
			this.setVisible(true);
		}
		else {
			setTop(new Text(""));
			setCenter(new BorderPane(null, null, null, null, null));
			this.setVisible(true);
		}
	}





}
