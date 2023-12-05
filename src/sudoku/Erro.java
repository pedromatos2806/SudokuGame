package sudoku;


import javax.swing.JLabel;


public class Erro extends JLabel {

	private static final long serialVersionUID = 1L;
	private static Erro instance;
	private static int numberErros;
	private Erro() {
		updateErros(0);
	}
	
	public static Erro getInstance() {
		if(instance == null)
			instance = new Erro();
		return instance;
	} 
	
	public static int getNumberErros() {
		return numberErros;
	}
	public static void setNumberEros(int erro) {
		numberErros=erro;
	}
	public void updateErros(int qtd_erros) {
		setNumberEros(getNumberErros() + qtd_erros);
		super.setText("Erro : "+ getNumberErros() +" de  3");
		if(getNumberErros() > 2) {
			Relogio.getInstance().stopTimer();
		}

	}
	
}