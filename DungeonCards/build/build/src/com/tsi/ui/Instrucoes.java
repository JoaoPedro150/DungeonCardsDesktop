package com.tsi.ui;

import java.io.IOException;

import com.tsi.app.DungeonCards;

import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public abstract class Instrucoes {
	private BorderPane root;
	private Stage primaryStage;
	private Scene proximaScene; 
	private static boolean exibindo;
	private static Instrucoes ajuda;

	/**Define onde onde será mostrada a janela dentro do stage*/
	public static int POSICAO_INFERIOR = 1, POSICAO_SUPERIOR = 2;

	private Instrucoes(Stage primaryStage, Scene proximaScene) {
		try {
			root = (BorderPane)FXMLLoader.load(getClass().getResource("/com/tsi/ui/fxml/instruction.fxml"));
			this.primaryStage = primaryStage;	
			this.proximaScene = proximaScene;


		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Instrucoes getInstance(Stage primaryStage, Scene proximaScene){
		return ajuda == null ? new Instrucoes(primaryStage, proximaScene) {} : ajuda;
	}

	public void exibir() {

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(exibindo)
					esconder();
				else
					exibindo = true;

			}

		});
		
	
	}

	public void esconder() {
		final FadeTransition fadeIn = new FadeTransition(Duration.millis(2500), proximaScene.getRoot());

		fadeIn.setFromValue(0.0);
		fadeIn.setToValue(1.0);

		fadeIn.play();

		DungeonCards.setInstrucoesExibindo(false);
		DungeonCards.iniciarJogo();
	}

	public void setExibindo(boolean exibindo) {
		
		Instrucoes.exibindo = exibindo;
	}

	public static boolean isExibindo() {
		return exibindo;
	}




}
