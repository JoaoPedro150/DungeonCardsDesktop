package com.tsi.ui;

import java.io.File;

import com.tsi.card.Card;
import com.tsi.chars.Heroi;
import com.tsi.item.Arma;
import com.tsi.itemespecial.Bau;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class CardPane extends BorderPane {
	private Card card;

	private Text cardName = new Text();
	private Text cardValue = new Text();
	private ImageView valueIcon = new ImageView(new Image(Sprite.CAMINHO + File.separator + "CoracaoIcon.png", 27, 22, false, false));
	private HBox hBox = new HBox();
	private Node armaSprite;

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

		if (card != null) {
			if(card instanceof Heroi && ((Heroi) card).getArma() != null){
				Arma arma = ((Heroi) card).getArma();
				Node topPane = definirValorArma(arma);
				armaSprite = arma.getSprite().getImageView(55, 70);
				setCenter(new BorderPane(new StackPane(card.getSprite().getImageView(),armaSprite), null, null , cardName,topPane));
				corrigirAlinhamentoDaArma();
			} else if (card instanceof Arma) {
				Arma arma = ((Arma) card);
				Node topPane = definirValorArma(arma);
				setCenter(new BorderPane(card.getSprite().getImageView(), null, null , cardName,topPane));
			}
			else
				setCenter(new BorderPane(card.getSprite().getImageView(), null, null, cardName,null));

			this.setVisible(true);
			
			setCardStyle();
			
			if (!(card instanceof Arma || card instanceof Bau))
				setTop(hBox);
			
		}
	}

	private Node definirValorArma(Arma arma) {
		StackPane node = new StackPane();
		Text valor = new Text("" + arma.getValor());
		valor.setStyle("-fx-font: 14px 'OCR A Extended';");
		valor.setTranslateX(19);
		node.getChildren().addAll(new ImageView(new Image(Sprite.CAMINHO+File.separator+"EspadaIcon.png", 26, 21, false, false)),valor);
		//node.setTranslateY(-60);
		node.setTranslateX(2);
		node.setPadding(new Insets(-129, -13, 0, 0));
		return node;
	}

	private void corrigirAlinhamentoDaArma() {
		armaSprite.setTranslateX(-12);
		armaSprite.setTranslateY(-7);

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

		Node arma = getRight();
		if(arma != null)arma.setTranslateX(500);


		Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);
        hBox = new HBox(region, cardValue, valueIcon);

	}

	public void setCardName(String name){
		cardName.setText(name);
	}
}
