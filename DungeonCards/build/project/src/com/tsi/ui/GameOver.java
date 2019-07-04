package com.tsi.ui;

import java.io.IOException;

import com.tsi.app.DungeonCards;
import com.tsi.app.Jogo;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public abstract class GameOver {
	private Stage primaryStage;
	private BorderPane root;
	private Audio audio;
	private static GameOver gameOver;

	private GameOver(Stage primaryStage) {
		;
		try {
			audio = new Audio("gameover.wav");
			root = (BorderPane)FXMLLoader.load(getClass().getResource("fxml/gameover.fxml"));
			this.primaryStage = primaryStage;

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static GameOver getInstance(Stage primaryStage){
		return gameOver == null ? new GameOver(primaryStage){} : gameOver;
	}

	public void exibirGameOver(int qtdMoedas) {
		Scene scene = new Scene(root);

		((Label)scene.lookup("#lblQtdMoedas")).setText("" + qtdMoedas);
		((ImageView)scene.lookup("#botaoPlay")).addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> new Jogo());
		((ImageView)scene.lookup("#botaoMenu")).addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> DungeonCards.restart());
		scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			if (key.getCode() == KeyCode.RIGHT)
				new Jogo();
		});
		scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			if (key.getCode() == KeyCode.LEFT)
				DungeonCards.restart();
		});

		FadeTransition fadeOut = new FadeTransition(Duration.millis(2500), primaryStage.getScene().getRoot());
		fadeOut.setFromValue(1);
		fadeOut.setToValue(0);
		audio.play();
		fadeOut.play();
		fadeOut.setOnFinished((value) -> { primaryStage.setScene(scene); });
	}



	public void esconderGameOver() {
		final FadeTransition fadeIn = new FadeTransition(Duration.millis(2500), primaryStage.getScene().getRoot());
		fadeIn.setFromValue(0.0);
		fadeIn.setToValue(1.0);

		fadeIn.play();
	}





}
