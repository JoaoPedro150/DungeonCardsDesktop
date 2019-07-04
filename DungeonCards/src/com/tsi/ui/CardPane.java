package com.tsi.ui;

import com.tsi.card.Card;
import com.tsi.chars.Heroi;
import com.tsi.item.Arma;
import com.tsi.itemespecial.Bau;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class CardPane extends BorderPane {
	
	private Card card;
	private Label cardName;
	private Label cardValue;
	private ImageView valueIcon;
	private HBox hBox;
	private Node armaSprite;

	public CardPane() {
		hBox = new HBox();
		cardName = new Label();
		cardValue = new Label();
		System.out.println(Sprite.CAMINHO + "/" + "CoracaoIcon.png");
		valueIcon = new ImageView(new Image(Sprite.CAMINHO + "/" + "CoracaoIcon.png", 27, 22, false, false));
	}
	
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
		cardName.setWrapText(true);
		cardName.setTextAlignment(TextAlignment.CENTER);
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
				((Region) topPane).setPadding(new Insets(-108, -13, 0, 0));
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
		Label valor = new Label("" + arma.getValor());
		valor.setStyle("-fx-font: 14px 'OCR A Extended';");
		valor.setTranslateX(19);
		node.getChildren().addAll(new ImageView(new Image(Sprite.CAMINHO+ "/" +"EspadaIcon.png", 26, 21, false, false)),valor);
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


		cardName.setStyle("#card_name {-fx-text-fill: white; -fx-font: 12px 'OCR A Extended' ; }");
		setAlignment(cardName, Pos.CENTER);
		cardName.setTranslateY(-20);
		cardValue.setStyle("#card_value {-fx-text-fill: white; -fx-font: 14px 'OCR A Extended' ; }");

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

	public void animacaoTamanho(boolean reverse){
		setCache(true);
		setCacheShape(true);
		setCacheHint(CacheHint.SPEED);
		Platform.runLater(()->{
			ScaleTransition st = new ScaleTransition(Duration.millis(200), this);
			st.setFromX(1);
			st.setFromY(1);
			st.setToX(0);
			st.setToY(0);
			st.setCycleCount(2);
		    st.setAutoReverse(reverse);
		    st.play();
		});


	}

	public void animacaoPosicao(double origemX, double origemY, double destinoX, double destinoY){
		setCache(true);
		setCacheShape(true);
		setCacheHint(CacheHint.SPEED);
		Platform.runLater(()->{
			TranslateTransition tt = new TranslateTransition(Duration.millis(200), this);
			tt.setFromX(origemX);
			tt.setFromY(origemY);
			tt.setToX(destinoX);
			tt.setToY(destinoY);
			tt.setCycleCount(1);
		    tt.setAutoReverse(true);
		    tt.play();
		});

	}

	public void animacaoDano(){
		setCache(true);
		setCacheShape(true);
		setCacheHint(CacheHint.SPEED);
		Platform.runLater(()->{
			TranslateTransition tt = new TranslateTransition(Duration.millis(40), this);
			tt.setFromX(10);
			tt.setFromY(0);
			tt.setToX(-10);
			tt.setFromX(0);
			tt.setCycleCount(6);
		    tt.setAutoReverse(true);
		    tt.play();

		});
	}


}
