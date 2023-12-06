package sudoku;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Erro extends JLabel {

	private static final long serialVersionUID = 1L;
	private static Erro instance;
	private static  int numberErros;

	private Erro() {
		updateErros(0);
	}

	public static Erro getInstance() {
		if (instance == null)
			instance = new Erro();
		return instance;
	}

	public static int getNumberErros() {
		return numberErros;
	}

	public static void setNumberErros(int erro) {
		numberErros = erro;
	}

	private static void exibirMensagemErro(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
	}

	public void updateErros(int qtd_erros) {
		if (qtd_erros > 0) {
			setNumberErros(getNumberErros() + qtd_erros);
			super.setText("Erro : " + getNumberErros() + " de  3");
		}

		// Exibe o alerta de erro!
		if (getNumberErros() > 2) {
			exibirMensagemErro("GAME OVER!!!!\nLimite de erros atingido. O jogo será encerrado.");
			Relogio.getInstance().stopTimer();
			SudokuMain.restartGame(true);
		}
	}

	public static void updateErrosRestart(int qtd_erros) {
		if(qtd_erros == 0)
		setNumberErros(qtd_erros);
		instance.setText("Erro : " + getNumberErros() + " de  3");
	}

}