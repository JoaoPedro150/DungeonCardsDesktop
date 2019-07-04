package com.tsi.app;

import com.tsi.grid.Grid;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

/**Esta classe é responsável por capturar os inputs do usuário e tratá-los com os comandos correspondentes*/
public class InputControl {

	private Jogo game;

	public InputControl(Jogo game) {
		this.game = game;
	}

	public void eventosDeTeclado(Scene scene) {

		
		EventHandler<KeyEvent> keyEvent = new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				if(game.isGameOver()) return; 
				
				if(event.getCode() == KeyCode.SPACE) {
					game.alterarModo();
					
				}
				else {
					switch(event.getCode()) {
					case DOWN:
						game.mover(Grid.BAIXO);
							break;
					case UP:
						game.mover(Grid.CIMA); break;
					case LEFT:
						game.mover(Grid.ESQUERDA); break;

					case RIGHT:
						game.mover(Grid.DIREITA); break;
					default:
						break;

					}
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
				game.alterarModo();
				//game.informacao();

			}
			contador.set(0);
		});

	}
}
