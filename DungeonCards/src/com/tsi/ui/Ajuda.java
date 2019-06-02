package com.tsi.ui;

import java.io.IOException;

import com.tsi.grid.Posicao;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

public abstract class Ajuda {
	private BorderPane root;
	private Stage primaryStage; 
	private Popup popUp;

	private static Ajuda ajuda;
	
	/**Define onde onde será mostrada a janela dentro do stage*/
	public static int POSICAO_INFERIOR = 1, POSICAO_SUPERIOR = 2;
	
	private Ajuda(Stage primaryStage) {
		try {
			popUp = new Popup();
			root = (BorderPane)FXMLLoader.load(getClass().getResource("fxml/ajuda.fxml"));
			this.primaryStage = primaryStage;
			popUp.getContent().add(root);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Ajuda getInstance(final Stage primaryStage){
		return ajuda == null ? new Ajuda(primaryStage){} : ajuda;
	}

	public void exibirAjuda(String texto, String titulo, int posicao) {
		Label top = (Label) root.getTop(),
				center = (Label) root.getCenter();

		top.setText(titulo);
		center.setText(texto);
		
		Posicao pos = definirPosicao(posicao);
		if(!popUp.isShowing()) popUp.show(primaryStage, pos.getX(), pos.getY());

	}

	private Posicao definirPosicao(int posicao) {
		Posicao p = new Posicao();
		if(posicao == POSICAO_INFERIOR) {
			p.setX((int) (primaryStage.getWidth() - primaryStage.getX()));
			p.setY((int) primaryStage.getY());
			
		}
		
		else {
			p.setX((int)primaryStage.getX());
			p.setY((int) primaryStage.getY());
		}
		
		return p;
	}

	public void esconderAjuda() {
		popUp.hide();
	}
	
	public static void alerta(String titulo, String texto, AlertType tipo) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setTitle(titulo);
		alert.setContentText(texto);
		alert.showAndWait();
	}
	
	


}
