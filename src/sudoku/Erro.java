package sudoku;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Erro extends JLabel {

	private static final long serialVersionUID = 1L;
	private static Erro instance;
	private static int numberErros;

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

	public static void setNumberEros(int erro) {
		numberErros = erro;
	}

	private void exibirMensagemErro(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
	}

	public void updateErros(int qtd_erros) {
		setNumberEros(getNumberErros() + qtd_erros);
		super.setText("Erro : " + getNumberErros() + " de  3");

		// Exibe a mensagem de erro
		if (getNumberErros() > 2) {
			exibirMensagemErro("GAME OVER!!!!\nLimite de erros atingido. O jogo será encerrado.");
			Relogio.getInstance().stopTimer();
			SudokuMain.getInstance().restartGame(true);
		}
	}

}