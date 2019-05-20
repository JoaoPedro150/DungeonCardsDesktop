package com.tsi.app;

import java.io.IOException;

import com.tsi.exception.InteracaoException;
import com.tsi.exception.MovimentoException;
import com.tsi.grid.Grid;
import com.tsi.grid.Posicao;
import com.tsi.ui.CardPane;
import com.tsi.ui.Controle;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**Nesta classe encontra-se o método main*/
public class DungeonCards extends Application {
	private Scene scene;
	private static Stage stage;
	private Controle controle;
	private Jogo jogo;
	private boolean cursorLivre;

	private Posicao posicaoHeroi = null;

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
		stage.setMinWidth(500);
		stage.setMinHeight(600);
		stage.setTitle("Dungeon Cards");
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.getIcons().add(new Image("/com/tsi/sprites/GameIcon.png"));
		colorirCelula(null);
	}

	private void inicializar(Stage stage) {
		BorderPane root;
		try {
			root = (BorderPane)FXMLLoader.load(getClass().getResource("index.fxml"));
			scene = new Scene(root,0,0);

			DungeonCards.stage = stage;

			stage.setScene(scene);

			Platform.runLater(() ->  atualizar());

			controle = new Controle(this);
			jogo = new Jogo();

			estilizar();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public boolean interagir() {
		try {
			boolean movimentoHeroi = jogo.interagir();
			Platform.runLater(() ->  atualizar());


			return movimentoHeroi;
		}catch(InteracaoException e) {
			System.out.println(e);
			return false;
		}
	}

	private void atualizar() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				obterCard(new Posicao(i, j)).setCard(jogo.getGrid().getCard(new Posicao(i, j)));
		
		((Label)scene.lookup("#lblMoedas")).setText(jogo.getQtdMoedas() + "");
	}

	public void alterarModo() {
		//Muda a variável de instância
		cursorLivre = !cursorLivre;

		if(!cursorLivre) fecharInfo();

		else
			posicaoHeroi = jogo.getGrid().getPosicaoCursor().clone();

		colorirCelula(jogo.getGrid().getPosicaoCursor());
	}

	public void fecharInfo(){
		jogo.fecharInfo();
		Posicao posicaoAnterior = jogo.getGrid().getPosicaoCursor().clone();
		jogo.getGrid().moverCursor(posicaoHeroi);
		colorirCelula(posicaoAnterior);
	}



	private boolean moverCursor(int movimento) {

		try {
			Posicao posicaoAnterior = jogo.getGrid().getPosicaoCursor().clone();

			jogo.getGrid().moverCursor(movimento);

			colorirCelula(posicaoAnterior);

			return true;
		} catch (MovimentoException e) {
			System.out.println(e);
			return false;
		}
	}


	public boolean mover(int direcao) {
		//Tenta mover o cursor. Aqui verifica se excede os limites do grid
		if(!moverCursor(direcao)) return false;

		//Aqui vê se o cursor se move junto com o herói ou se está no modo livre.
		if(cursorLivre == false) {

			//Tenta interagir
			if(!interagir())
				//Se entrou aqui o herói não se moveu, então desfaz o movimento do jogador no cursor
				moverCursor(Grid.inverterDirecao(direcao));
		}

		else {
			//Se o cursor estiver livre, apenas exibe a informação da celula na
			//posicao atual do cursor
			jogo.informacao();
		}

		return true;

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
	private void colorirCelula(final Posicao posicaoAnterior) {
		String paneIDAnterior = null;
		//Apaga a cor de seleção da célula antes selecionada
		if(posicaoAnterior != null) {
			paneIDAnterior = formatarPaneTag(posicaoAnterior);

			scene.lookup(paneIDAnterior).getStyleClass().remove("colorBlockYellow");
			scene.lookup(paneIDAnterior).getStyleClass().remove("colorBlockRed");
		}

		//Acende a cor na célula do grid atual
		String paneID = formatarPaneTag(jogo.getGrid().getPosicaoCursor());

		//Colore o seletor de acordo com o contexto do cursor.
		scene.lookup(paneID).getStyleClass().add(cursorLivre ? "colorBlockRed" : "colorBlockYellow");
	}

	public static void main(String[] args) {
		launch(args);
	}

}
