package com.tsi.app;


import java.io.IOException;

import com.tsi.app.Jogo;
import com.tsi.ui.Audio;
import com.tsi.ui.Instrucoes;					 

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**Nesta classe encontra-se o método main*/
public class DungeonCards extends Application {
	private static Scene scene;
	private static Stage primaryStage;

	private static Audio musica;
	private static boolean exibirInstrucoes = true;
	@Override
	public void start(Stage stage) {

		primaryStage = stage;

		instanciarMusica();

		AnchorPane root = null;
		try {
			root = (AnchorPane)FXMLLoader.load(getClass().getResource("../ui/fxml/telaInicial.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		scene = new Scene(root);

		//Método que constói a janela principal comum a todas as cenas
		estilizarJanela();

		// Define evento de click no botao Play
		setBotaoPlayAction(scene, "botaoPlay");

		primaryStage.setScene(scene);
		primaryStage.show();
		scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			if (key.getCode() == KeyCode.ENTER) {
				key.consume();
				eventoIniciarJogo();
				
				
			
			}
		});
	}
	private void instanciarMusica() {
		musica = new Audio("TheFireCamp.mp3");
		musica.play();
	}

	public static void restart() {

		AnchorPane root = null;
		try {
			root = (AnchorPane)FXMLLoader.load(DungeonCards.class.getResource("../ui/fxml/telaInicial.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		musica.play();
		scene = new Scene(root);

		// Define evento de click no botao Play
		setBotaoPlayAction(scene, "botaoPlay");

		primaryStage.setScene(scene);

		scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			if (key.getCode() == KeyCode.ENTER)
				eventoIniciarJogo();
		});
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static Scene getScene() {
		return scene;
	}

	public static void setBotaoPlayAction(Scene scene, String id) {
		((ImageView)scene.lookup("#" + id)).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				eventoIniciarJogo();
				event.consume();
			}




		});
	}
	private static void eventoIniciarJogo() {
		Instrucoes instrucoes = Instrucoes.getInstance(DungeonCards.getPrimaryStage(), scene);
		
		instrucoes.exibir();
		//instrucoes.setExibindo(true);
		if(!exibirInstrucoes) {
			iniciarJogo();
			
		}
		
		
	}
		public static void iniciarJogo() {
			musica.stop();
			new Jogo();

		}		  
		public static void main(String[] args) {
			launch(args);
		}

		private void estilizarJanela() {
			primaryStage.setWidth(500);
			primaryStage.setHeight(600);
			primaryStage.setMinWidth(500);
			primaryStage.setMinHeight(600);
			primaryStage.setTitle("Dungeon Cards");

			primaryStage.getIcons().add(new Image("/com/tsi/sprites/GameIcon.png"));
		}
		public static boolean isInstrucoesExibindo() {
			return exibirInstrucoes;
		}

		public static void setInstrucoesExibindo(boolean instrucoesExibindo) {
			DungeonCards.exibirInstrucoes = instrucoesExibindo;
		}

	}

