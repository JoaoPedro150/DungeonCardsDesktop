package com.tsi.app.telaInicial;


import java.io.IOException;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**Nesta classe encontra-se o método main*/
public class TelaInicial extends Application {
	private Scene scene;
	private Stage primaryStage;
	@Override
	public void start(Stage stage) {

		primaryStage = stage;

		AnchorPane root = null;
		try {
			root = (AnchorPane)FXMLLoader.load(getClass().getResource("telaInicial.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("ruth rocha " + root);

		scene = new Scene(root, 801, 602);

		primaryStage.setScene(scene);
		primaryStage.show();
		//definirBotoes();
	}
	private void definirBotoes() {
		Region pane = (Region)scene.lookup("#jogarBtn");
		System.out.println(pane);
		pane.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println("fefe");

			}
		});

	}
	public static void main(String[] args) {
		launch(args);
	}
}
