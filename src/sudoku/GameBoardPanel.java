package sudoku;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class GameBoardPanel extends JPanel {
   private static final long serialVersionUID = 1L;  // to prevent serial warning

   // Define named constants for UI sizes
   public static final int CELL_SIZE = 60;   // Cell width/height in pixels
   public static final int BOARD_WIDTH  = CELL_SIZE * SudokuConstants.GRID_SIZE;
   public static final int BOARD_HEIGHT = CELL_SIZE * SudokuConstants.GRID_SIZE;
                                             // Board width/height in pixels
   
   // Define properties
   /** The game board composes of 9x9 Cells (customized JTextFields) */
   private Cell[][] cells = new Cell[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
   /** It also contains a Puzzle with array numbers and isGiven */
   private Puzzle puzzle = new Puzzle();
   
   /** Constructor */
   public GameBoardPanel() {
      super.setLayout(new GridLayout(SudokuConstants.GRID_SIZE, SudokuConstants.GRID_SIZE));  // JPanel

      // Allocate the 2D array of Cell, and added into JPanel.
      for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
         for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
            cells[row][col] = new Cell(row, col);
            super.add(cells[row][col]);   // JPanel
         }
      }

      // [TODO 3] Allocate a common listener as the ActionEvent listener for all the
      //  Cells (JTextFields)
      // .........
      CellInputListener listener = new CellInputListener();
      // [TODO 4] Adds this common listener to all editable cells
      // .........
      for (int row = 0 ; row  < SudokuConstants.GRID_SIZE; ++row) {
    	   for (int col = 0 ;  col < SudokuConstants.GRID_SIZE; ++col) {
    	      if (cells[row][col].isEditable()) {
    	         cells[row][col].addActionListener(listener);
    	      }
    	   }
    	}
      super.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
   }

   /**
    * Generate a new puzzle; and reset the gameboard of cells based on the puzzle.
    * You can call this method to start a new game.
    */
   public void newGame(SudokuConstantsNivel sudokuConstantsNivel ) {
      // Generate a new puzzle
      puzzle.newPuzzle(sudokuConstantsNivel);

      // Initialize all the 9x9 cells, based on the puzzle.
      for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
         for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
            cells[row][col].newGame(puzzle.numbers[row][col], puzzle.isGiven[row][col]);
         }
      }
   }

   /**
    * Return true if the puzzle is solved
    * i.e., none of the cell have status of TO_GUESS or WRONG_GUESS
    */
   public boolean isSolved() {
      for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
         for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
            if (cells[row][col].status == CellStatus.TO_GUESS || cells[row][col].status == CellStatus.WRONG_GUESS) {
               return false;
            }
         }
      }
      return true;
   }

   public boolean temMaisDeUmCaractere(String text) {
	    String texto = text.trim(); //Obtém o texto e remove espaços em branco no início/fim
	    return texto.length() > 1;
	}
   
   // [TODO 2] Define a Listener Inner Class for all the editable Cells
   private class CellInputListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
         // Get a reference of the JTextField that triggers this action event
    	  Cell sourceCell = (Cell) e.getSource();
			try {
				//validar se tem mais de um caractere na célula
				if (temMaisDeUmCaractere(sourceCell.getText())) {
					JOptionPane.showMessageDialog(null, "Você digitou mais de um caractere!\nDigite um número inteiro entre 1 e 9");
					sourceCell.setText("");
					return;
				}
				//verificar na célula se há um numero 0
				if(Integer.parseInt(sourceCell.getText())== 0) {
					JOptionPane.showMessageDialog(null, "O numero 0 não entra no jogo!\nDigite um número inteiro entre 1 e 9");
					sourceCell.setText("");
					return;
				}
				// Retrieve the int entered
				int numberIn = Integer.parseInt(sourceCell.getText());
				
				// For debugging
				System.out.println("You entered :" + numberIn);

				/*
				 * [TODO 5] (later - after TODO 3 and 4) Check the numberIn against
				 * sourceCell.number. Update the cell status sourceCell.status, and re-paint the
				 * cell via sourceCell.paint().
				 */
				//isso aqui é pra pintar a célula se for a correta
				if (numberIn == sourceCell.number) {
					sourceCell.status = CellStatus.CORRECT_GUESS;
				} else {
					//pinta a célula de vermelho caso esteja errada
					sourceCell.status = CellStatus.WRONG_GUESS;
					Erro.getInstance().updateErros(1);
					if(Erro.getNumberErros() >= 3) {
						JOptionPane.showMessageDialog(null, "Você errou mais de 3 vezes, ou seja, GAME OVER!!!");
						SudokuMain.restartGame(true);
					}
					
				}

				sourceCell.paint(); // re-paint this cell based on its status

				/*
				 * [TODO 6] (later) Check if the player has solved the puzzle after this move,
				 * by calling isSolved(). Put up a congratulation JOptionPane, if so.
				 */

				if (isSolved()) {
					JOptionPane.showMessageDialog(null, "Congratulations, you won exactly on time: "+ Relogio.getTime() );
				}
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "Digite um número inteiro entre 1 e 9");
				sourceCell.setText("");
			}
      }
   }
}