package com.tsi.ui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class Ajuda {
	private BorderPane root;
	private Stage primaryStage;
	private Popup popUp;

	private static Ajuda ajuda;

	private Ajuda(Stage primaryStage) {
		try {
			popUp = new Popup();
			root = (BorderPane)FXMLLoader.load(getClass().getResource("/com/tsi/app/ajuda.fxml"));
			this.primaryStage = primaryStage;

			popUp.getContent().add(root);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Ajuda getInstance(final Stage primaryStage){
		return ajuda == null ? new Ajuda(primaryStage) : ajuda;
	}

	public void exibirAjuda(String texto, String titulo) {
		Label top = (Label) root.getTop(),
				center = (Label) root.getCenter();

		top.setText(titulo);
		center.setText(texto);


		if(!popUp.isShowing()) popUp.show(primaryStage);

	}

	public void esconderAjuda() {
		popUp.hide();
	}


}
