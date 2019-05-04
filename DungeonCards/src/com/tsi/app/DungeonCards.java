package com.tsi.app;

import java.io.IOException;

import com.tsi.exception.InteracaoException;
import com.tsi.exception.MovimentoException;
import com.tsi.grid.Grid;
import com.tsi.grid.Posicao;
import com.tsi.ui.Controle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**Nesta classe encontra-se o método main*/
public class DungeonCards extends Application {
	private Scene scene;
	private Stage stage;
	private Grid grid;
	private Controle controle;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		inicializar(primaryStage);
		
		controle.eventosDeTeclado(scene);
		
		stage.show();
	}
	
	private void estilizar() {

		stage.setWidth(500);
		stage.setTitle("Dungeon Cards");
		stage.setHeight(600);
		stage.setWidth(500);
		stage.setTitle("Dungeon Cards");
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	}

	private void inicializar(Stage stage) {
		BorderPane root;
		try {
			root = (BorderPane)FXMLLoader.load(getClass().getResource("gridPane.fxml"));
			scene = new Scene(root,0,0);
			
			this.stage = stage;
			estilizar();
			stage.setScene(scene);
			
			controle = new Controle(this);
			grid = new Grid();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	

	public void interagir() {
		// TODO Auto-generated method stub
		//System.out.println("Espaço");
		try {
			grid.interagir();
		}catch(InteracaoException e) {
			System.out.println(e);
		}
	}

	public void informacao() {
		System.out.println("Informacao");
	}

	
	public boolean moverCursor(int movimento) {
	
		try {
			Posicao posicao = grid.moverCursor(movimento);
			System.out.println(posicao);
			//cursor.acender(posicao);
			return true;
		} catch (MovimentoException e) {
			System.out.println(e);
			return false;
		}
		
	}
	

	public static void main(String[] args) {
		launch(args);
	}

}
