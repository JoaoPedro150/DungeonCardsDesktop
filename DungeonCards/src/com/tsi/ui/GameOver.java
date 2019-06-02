package com.tsi.ui;

import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

public abstract class GameOver {
	private BorderPane root;
	private Stage primaryStage; 
	private Popup popUp;
	private Audio audio;
	private static GameOver gameOver;

	private GameOver(Stage primaryStage) {
		try {
			audio = new Audio("gameover.wav");
			popUp = new Popup();
			root = (BorderPane)FXMLLoader.load(getClass().getResource("/com/tsi/app/gameover.fxml"));
			this.primaryStage = primaryStage;
			popUp.getContent().add(root);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static GameOver getInstance(final Stage primaryStage){
		return gameOver == null ? new GameOver(primaryStage){} : gameOver;
	}

	public void exibirGameOver() {
		
		if(!popUp.isShowing()) {
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Centralizando o popup.
			popUp.show(primaryStage, (primaryStage.getScene().getWidth() + 30),
					((primaryStage.getScene().getHeight() - popUp.getHeight()) / 2) -20);
					
		    
			//popUp.show(primaryStage)
			audio.play();
			final FadeTransition fadeOut    = new FadeTransition(Duration.millis(3000), primaryStage.getScene().getRoot());
			fadeOut.setFromValue(1.0);
			fadeOut.setToValue(0.0);
			fadeOut.play();

		}

	}



	public void esconderGameOver() {
		final FadeTransition fadeIn = new FadeTransition(Duration.millis(3000), primaryStage.getScene().getRoot());
		fadeIn.setFromValue(0.0);
		fadeIn.setToValue(1.0);

		fadeIn.play();

		popUp.hide();
	}





}
