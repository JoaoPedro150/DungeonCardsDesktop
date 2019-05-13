package com.tsi.ui;



import java.io.File;

import com.tsi.card.Card;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

public class CardPane extends BorderPane {
	private Card card;

	private Text cardName = new Text();
	private Text cardValue = new Text();
	private ImageView valueIcon = new ImageView(new Image(Sprite.CAMINHO + File.separator + "CoracaoIcon.png", 27, 22, false, false));
	private HBox hBox = new HBox();

	public Card getCard() {
		return card;

	}

	public void setCard(Card card) {
		if(card == null){
			setTop(new Text(""));
			setCenter(new BorderPane(null, null, null, null, null));
			this.setVisible(true);
		}
		this.card = card;
		cardName.setText(card.getNome());
		cardValue.setText("" + card.getValor());
		setCardStyle();

		if (card != null) {
			setTop(hBox);
			BorderPane pane;

			if (card.getSprite() != null)
				pane = new BorderPane(card.getSprite().getImagem(), null, null, cardName, null);
			else
				pane = new BorderPane(null, null, null, cardName, null);


			setCenter(pane);
			this.setVisible(true);
		}
	}

	private void setCardStyle() {
		cardName.setId("card_name");
		cardValue.setId("card_value");


		cardName.setStyle("#card_name {-fx-fill: white; -fx-font: 12px 'OCR A Extended' ; }");
		setAlignment(cardName, Pos.CENTER);
		cardName.setTranslateY(-20);
		cardValue.setStyle("#card_value {-fx-fill: white; -fx-font: 14px 'OCR A Extended' ; }");

		cardValue.setTranslateX(-5);
		cardValue.setTranslateY(11);
		valueIcon.setTranslateX(-5);
		valueIcon.setTranslateY(7);

		Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);
        hBox = new HBox(region, cardValue, valueIcon);

	}

	public void setCardName(String name){
		cardName.setText(name);
	}

	@Override
	public CardPane clone() {
		CardPane c = new CardPane();
		c.setCard(card);
		c.setCardName("AAAAAAAA");
		return c;
	}









}
