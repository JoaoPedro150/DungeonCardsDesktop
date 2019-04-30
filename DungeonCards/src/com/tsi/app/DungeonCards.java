package com.tsi.app;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**Nesta classe encontra-se o método main*/
public class DungeonCards extends Application {
	private Scene scene;
	private Stage stage;
	private StackPane stackPane;
	

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		inicializar(primaryStage);
		
		eventosDeTeclado();


		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();

	}

	private void inicializar(Stage stage) {
		stackPane = new StackPane();
		scene = new Scene(stackPane, 500, 600);
		this.stage = stage;
		
	}

	private void eventosDeTeclado() {
		Text text = new Text("Nada");

		EventHandler<KeyEvent> keyEvent = new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.DOWN) {
					text.setText("Baixo");
				}

				if(event.getCode() == KeyCode.UP) {
					text.setText("Cima");
				}

				if(event.getCode() == KeyCode.LEFT) {
					text.setText("Esquerda");
				}

				if(event.getCode() == KeyCode.RIGHT) {
					text.setText("Direita");
				}

				if(event.getCode() == KeyCode.SPACE) {
					text.setText("Espaço");
				}

			}
		};

		scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent);

		/*Capturando o evento de segurar a tecla espaço*/

		//Variavel para contar quanto tempo uma tecla foi segurada
		SimpleIntegerProperty contador = new SimpleIntegerProperty(0);

		//Objeto que guarda uma combinacao de tecla
		KeyCombination keyCombination = new KeyCodeCombination(KeyCode.SPACE);

		/*Se uma tecla for pressionada na cena e esta for espaço. Aumenta o contador.
        Segurar a tecla faz com que o contador aumente mais que simplesmente apertar*/
		scene.setOnKeyPressed(espaco -> {
			contador.set(keyCombination.match(espaco) ? contador.get() + 1 : 0);

		});
		/*Quando a tecla espaço é solta, no evento capturado, verifica-se se a tecla pressionda
		 * é espaço, então, verifica se o contador tem uma quantidade suficiente para se considerar
		 * que a tecla foi segurada.*/
		scene.setOnKeyReleased(espaco -> {
			if(keyCombination.match(espaco) && contador.get() > 1.5) {
				text.setText("Espaço segurado e solto");

			}
			contador.set(0);
		});





		stackPane.getChildren().add(text);


	}

	public static void main(String[] args) {
		launch(args);
	}

}
