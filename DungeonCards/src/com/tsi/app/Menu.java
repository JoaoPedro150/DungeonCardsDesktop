package com.tsi.app;

import java.awt.Toolkit;
import java.io.IOException;

import com.tsi.ui.Ajuda;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Menu {
	private Stage primaryStage;
	private Scene menuScene;
	
	public Menu(Stage stage) {
		inicializarJogo(stage);
	}
		private void estilizar() {
			//menuScene.getStylesheets().add(getClass().getResource("menu.css").toExternalForm());
			System.out.println("MENU ABRIU");
			
		}

		private void inicializarJogo(Stage stage) {
			BorderPane root;
			try {
				root = (BorderPane)FXMLLoader.load(getClass().getResource("menu.fxml"));
				menuScene = new Scene(root,0,0);

				primaryStage = stage;

				primaryStage.setScene(menuScene);
				
				estilizar();

			} catch (IOException e) {
				Toolkit.getDefaultToolkit().beep();
				
				Ajuda.alerta("Dungeon Cards" , 
						"O jogo não pôde ser carregado. Arquivos corrompidos.", AlertType.ERROR);
			
				e.printStackTrace();
			}

		}
	}

