package com.tsi.app;

import java.awt.Toolkit;
import java.net.URL;

import com.tsi.exception.InteracaoException;
import com.tsi.exception.MovimentoException;
import com.tsi.grid.Grid;
import com.tsi.grid.Posicao;
import com.tsi.ui.Ajuda;
import com.tsi.ui.CardPane;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**Nesta classe encontra-se o método main*/
public class Jogo {
	private static Scene gameScene;
	private InputControl inputControl;
	private LogicaJogo logicaJogo;
	private boolean cursorLivre;

	private static MediaPlayer musica;

	private Posicao posicaoHeroi = null;

	public Jogo() {
		BorderPane root;
		try {
			instanciarMusica();

			root = (BorderPane)FXMLLoader.load(getClass().getResource("/com/tsi/ui/fxml/game.fxml"));
			gameScene = new Scene(root,0,0);

			DungeonCards.getPrimaryStage().setScene(gameScene);

			inputControl = new InputControl(this);
			logicaJogo = new LogicaJogo();

			gameScene.getStylesheets().add(getClass().getResource("../ui/css/application.css").toExternalForm());
			colorirCelula(null);

			inputControl.eventosDeTeclado(gameScene);


			atualizar();

		} catch (Exception e) {
			Toolkit.getDefaultToolkit().beep();

			Ajuda.alerta("Dungeon Cards" ,
					"O jogo não pôde ser carregado. Arquivos corrompidos.", AlertType.ERROR);

			e.printStackTrace();
			System.exit(0);
		}

	}

	private void instanciarMusica() {
		URL resource = getClass().getResource("/com/tsi/audio/Swords&Dragons.mp3");
		musica = new MediaPlayer(new Media(resource.toString()));
		musica.setOnEndOfMedia(new Runnable() {
			public void run() {
				musica.seek(Duration.ZERO);
			}
		});
		musica.play();
		musica.setVolume(0.163);
	}

	public boolean interagir() {
		try {
			boolean movimentoHeroi = logicaJogo.interagir();
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
				obterCard(new Posicao(i, j)).setCard(logicaJogo.getGrid().getCard(new Posicao(i, j)));

		((Label)gameScene.lookup("#lblTotalMoedas")).setText(logicaJogo.getQtdMoedas() + "");
		((Label)gameScene.lookup("#lblMoedas")).setText(logicaJogo.getTotalQtdMoedas() + "");
	}

	public void alterarModo() {
		//Muda a variável de instância
		cursorLivre = !cursorLivre;

		if(!cursorLivre) fecharInfo();

		else
			posicaoHeroi = logicaJogo.getGrid().getPosicaoCursor().clone();

		colorirCelula(logicaJogo.getGrid().getPosicaoCursor());
	}

	public void fecharInfo(){
		logicaJogo.fecharInfo();
		Posicao posicaoAnterior = logicaJogo.getGrid().getPosicaoCursor().clone();
		logicaJogo.getGrid().moverCursor(posicaoHeroi);
		colorirCelula(posicaoAnterior);
	}



	private boolean moverCursor(int movimento) {

		try {
			Posicao posicaoAnterior = logicaJogo.getGrid().getPosicaoCursor().clone();

			logicaJogo.getGrid().moverCursor(movimento);

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
			logicaJogo.informacao();
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
		return (CardPane)gameScene.lookup(formatarPaneTag(posicao));
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

			gameScene.lookup(paneIDAnterior).getStyleClass().remove("colorBlockYellow");
			gameScene.lookup(paneIDAnterior).getStyleClass().remove("colorBlockRed");
		}

		//Acende a cor na célula do grid atual
		String paneID = formatarPaneTag(logicaJogo.getGrid().getPosicaoCursor());

		//Colore o seletor de acordo com o contexto do cursor.
		gameScene.lookup(paneID).getStyleClass().add(cursorLivre ? "colorBlockRed" : "colorBlockYellow");
	}


	public boolean isGameOver() {
		return logicaJogo.isGameOver();
	}

	public static void ativarMusica() {
		musica.play();
	}

	public static void desativarMusica() {
		musica.stop();
	}

	public static Scene getGameScene(){
		return gameScene;
	}
}
