package com.tsi.app;

import java.io.IOException;

import com.tsi.exception.InteracaoException;
import com.tsi.exception.MovimentoException;
import com.tsi.grid.Posicao;
import com.tsi.ui.CardPane;
import com.tsi.ui.Controle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**Nesta classe encontra-se o método main*/
public class DungeonCards extends Application {
	private Scene scene;
	private static Stage stage;
	private Controle controle;
	private Jogo jogo;

	@Override
	public void start(Stage primaryStage) throws Exception {

		inicializar(primaryStage);

		controle.eventosDeTeclado(scene);

		stage.show();
	}


	public static Stage getStage(){
		return stage;
	}

	private void estilizar() {
		stage.setWidth(500);
		stage.setHeight(600);
		stage.setTitle("Dungeon Cards");
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		colorirCelula(null);
	}

	private void inicializar(Stage stage) {
		BorderPane root;
		try {
			root = (BorderPane)FXMLLoader.load(getClass().getResource("index.fxml"));
			scene = new Scene(root,0,0);

			DungeonCards.stage = stage;

			stage.setScene(scene);

			controle = new Controle(this);
			jogo = new Jogo();

			atualizar();

			estilizar();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void interagir() {
		try {
			jogo.interagir();
			atualizar();

		}catch(InteracaoException e) {
			System.out.println(e);
		}
	}

	private void atualizar() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				obterCard(new Posicao(i, j)).setCard(jogo.getGrid().getCard(new Posicao(i, j)));
	}

	public void informacao() {
		System.out.println("Informacao");
	}


	public boolean moverCursor(int movimento) {

		try {
			Posicao posicaoAnterior = new Posicao(jogo.getGrid().getPosicaoCursor().getX(),
					jogo.getGrid().getPosicaoCursor().getY());

			jogo.getGrid().moverCursor(movimento);

			colorirCelula(posicaoAnterior);

			return true;
		} catch (MovimentoException e) {
			System.out.println(e);
			return false;
		}

	}

	/*AVISO: Os métodos abaixo podem não estar nas classes corretas. Temos que pensar onde encaixá-los.*/

	/**Transforma uma posição do grid na tag FXML do painel que corresponde a essa posição.
	 * Por exemplo: Posicão (x=0, y=2), corresponde ao painel de tag "#pane20";
	 * @param posicao a ser acessada no painel
	 * @return tag FXML da posição fornecida.
	 */
	private String formatarPaneTag(Posicao posicao) {
		return String.format("#pane%d%d", posicao.getY(),  posicao.getX());
	}

	private CardPane obterCard(Posicao posicao){
		return (CardPane)scene.lookup(formatarPaneTag(posicao));
	}

	/**Produz a indicação visual do cursor, colorindo a posição que este atualmente se encontra.
	 *
	 * @param posicaoAnterior posição anterior que deve ser descolorida no grid.
	 * Pode ser passado <b>null</b> caso não se deseje apagar a posição anteriormente colorida
	 */
	private void colorirCelula(Posicao posicaoAnterior) {

		//Apaga a cor de seleção da célula antes selecionada
		if(posicaoAnterior != null) {
			String paneIDAnterior = formatarPaneTag(posicaoAnterior);
			scene.lookup(paneIDAnterior).getStyleClass().remove("colorBlock");
		}

		//Acende a cor na célula do grid atual
		String paneID = formatarPaneTag(jogo.getGrid().getPosicaoCursor());
		scene.lookup(paneID).getStyleClass().add("colorBlock");

	}

	public static void main(String[] args) {
		launch(args);
	}

}
