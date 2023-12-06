package sudoku;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * The main Sudoku program
 */
public class SudokuMain extends JFrame {

	private static final long serialVersionUID = 1L; // to prevent serial warning
	private static SudokuMain instance;
	private static String nomeJogador;

	// private variables
	private static GameBoardPanel board = new GameBoardPanel();
	private static JButton btnNewGame = new JButton("Iniciar Jogo");
	private static JButton btnRestartGame = new JButton();
	private static JComboBox<String> cmbNiveis = new JComboBox<String>();;
	private static JLabel lblNiveis = new JLabel("Escolha um Nivel :");;
	private static JLabel lblSudoku = new JLabel(new ImageIcon(SudokuMain.class.getResource("/images/sudoku.png")));
	private static JLabel lblName = new JLabel("Digite seu nome :");
	private static JTextField txtName;
	private static JLabel lblJogador = new JLabel();
	private static JLabel lblImageJogador = new JLabel();
	
	// Constructor
	public SudokuMain() {

		Container cp = getContentPane();
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		// Botão Start Game
		btnNewGame.setBounds(155, 357, 117, 25);
		getContentPane().add(btnNewGame);

		// Label de Escolher Níveis:
		lblNiveis.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNiveis.setBounds(128, 275, 178, 30);
		getContentPane().add(lblNiveis);
		lblNiveis.setForeground(new Color(0, 128, 192));
		lblNiveis.setHorizontalAlignment(SwingConstants.CENTER);

		// Label Digite seu nome :
		lblName.setForeground(new Color(0, 128, 192));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblName.setBounds(130, 190, 178, 30);
		getContentPane().add(lblName);

		// ComboBox de Níveis
		cmbNiveis.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbNiveis.setBounds(115, 304, 191, 22);
		cmbNiveis.addItem("Fácil");
		cmbNiveis.addItem("Médio");
		cmbNiveis.addItem("Difícil");
		cmbNiveis.setForeground(new Color(0, 128, 192));
		cmbNiveis.setBackground(new Color(255, 255, 255));
		getContentPane().add(cmbNiveis);

		// Label de Img SUDOKU:
		lblSudoku.setVisible(true);
		lblSudoku.setBounds(450, 0, 480, 700);
		getContentPane().add(lblSudoku);

		// Botão Restart Game:
		btnRestartGame.setIcon(new ImageIcon(SudokuMain.class.getResource("/images/restart.png")));
		btnRestartGame.setBounds(655, 90, 51, 51);
		getContentPane().add(btnRestartGame);

		// Label Nome do Jogador
		lblJogador.setFont(new Font("Comic Sans MS", Font.ITALIC, 22));
		lblJogador.setBounds(655, 60, 400, 30);
		getContentPane().add(lblJogador);
		lblJogador.setForeground(Color.BLACK);
		lblJogador.setHorizontalAlignment(SwingConstants.LEFT);
		
		
		// Label Imagem nome do Jogador 
		lblImageJogador.setIcon(new ImageIcon(SudokuMain.class.getResource("/images/player.png")));
		lblImageJogador.setSize(51, 51);
		lblImageJogador.setLocation(604, 55);
		getContentPane().add(lblImageJogador);
		lblImageJogador.setVisible(false);
		
		// Pane Board:
		board.setBounds(0, 50, 435, 462);
		board.setVisible(false);
		cp.add(board);

		// Centralizar o board usando o layout absolute:
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) screenSize.getWidth();
		int screenHeight = (int) screenSize.getHeight();

		int boardWidth = board.getWidth();
		int boardHeight = board.getHeight();

		int centerX = (screenWidth - boardWidth) / 2;
		int centerY = (screenHeight - boardHeight) / 2;

		board.setBounds(centerX, centerY, boardWidth, boardHeight);

		// TextField Nome do Jogador:
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		txtName.setBounds(115, 220, 200, 30);
		getContentPane().add(txtName);
		txtName.setColumns(10);

		// Add a button to the south to re-start the game via board.newGame()
		// ......
		btnRestartGame.setVisible(false);
		// Initialize the game board to start the game
		board.newGame(null);

		// Ação do botão de Start Game
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (cmbNiveis.getSelectedItem().equals("Fácil")) {
					board.newGame(SudokuConstantsNivel.FACIL);
				} else if (cmbNiveis.getSelectedItem().equals("Médio")) {
					board.newGame(SudokuConstantsNivel.MEDIO);
				} else if (cmbNiveis.getSelectedItem().equals("Difícil")) {
					board.newGame(SudokuConstantsNivel.DIFICIL);
				}

				startGame(true);

			}
		});

		// Ação do Botão Restart Game:
		btnRestartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				restartGame(true);
			}
		});

		// Cronômetro
		Relogio.getInstance().setBounds(660, 20, 90, 20);
		Relogio.getInstance().setForeground(Color.BLACK);
		Relogio.getInstance().setVisible(false);
		cp.add(Relogio.getInstance());
		
		
		// Erro
		Erro.getInstance().setBounds(645,40,90,20);
		Erro.getInstance().setForeground(Color.RED);
		Erro.getInstance().setVisible(false);
		cp.add(Erro.getInstance());
		
		pack(); // Pack the UI components, instead of using setSize()
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // to handle window-closing
		setTitle("Sudoku");
		setVisible(true);
	}

	
	
	public static void startGame(boolean bool) {
		if (bool == true) {
			try {
				board.setVisible(true);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			btnRestartGame.setVisible(true);
			Erro.getInstance().setVisible(true);
			Relogio.getInstance().setVisible(true);
			lblJogador.setVisible(true);
			lblImageJogador.setVisible(true);
			Relogio.getInstance().startTimer();
			cmbNiveis.setVisible(false);
			btnNewGame.setVisible(false);
			lblNiveis.setVisible(false);
			lblSudoku.setVisible(false);
			lblName.setVisible(false);
			txtName.setVisible(false);
			nomeJogador = transformarString(txtName.getText());
			lblJogador.setText(nomeJogador);

		}
	}

	public static void restartGame(boolean bool) {
		if (bool == true) {
			board.setVisible(false);
			btnRestartGame.setVisible(false);
			Erro.getInstance().setVisible(false);
			lblJogador.setVisible(false);
			lblImageJogador.setVisible(false);
			Erro.setNumberEros(0);
			cmbNiveis.setVisible(true);
			btnNewGame.setVisible(true);
			lblNiveis.setVisible(true);
			lblSudoku.setVisible(true);
			lblName.setVisible(true);
			txtName.setVisible(true);
			lblJogador.setVisible(false);
			Relogio.getInstance().setVisible(false);
			Relogio.getInstance().stopTimer();
		}
	}

	public static SudokuMain getInstance() {
		if (instance == null)
			instance = new SudokuMain();
		return instance;
	}
	
	//Esse método serve para colocar letras maiusculas nos nomes próprios!
    public static String transformarString(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder resultado = new StringBuilder();
        boolean transformarParaMaiuscula = true;

        for (char caractere : input.toCharArray()) {
            if (Character.isWhitespace(caractere)) {
                resultado.append(caractere);
                transformarParaMaiuscula = true;
            } else if (transformarParaMaiuscula) {
                resultado.append(Character.toUpperCase(caractere));
                transformarParaMaiuscula = false;
            } else {
                resultado.append(Character.toLowerCase(caractere));
            }
        }

        return resultado.toString();
    }
	
    /** The entry main() entry method */
	public static void main(String[] args) {
		// [TODO 1] Check "Swing program template" on how to run
		// the constructor of "SudokuMain"
		// .........
			SudokuMain frame = getInstance();
			frame.setVisible(true);

	}
}