/*******************************************************************************
 * Laurence Ashdown
 * OOSD Assignment
 * RMIT Semester 1 2018
 ******************************************************************************/
package view.interfaces;

import model.Player;
import model.interfaces.GameEngine;
import view.HexButton;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;



public interface Board{

	public abstract JPanel createBoard(GameEngine engine, int size);

	public abstract JToolBar createToolbar();
	
	public HexButton[][] getHexButtons();
	
	public void setHexButtons(HexButton[][] hexButtons);
	
	public HexButton getHex(HexButton button);
	
	public JScrollPane getPane();
	
	public void setPane(JScrollPane pane);
	
	public JTextArea getTextArea();
	
	public void setTextArea(JTextArea textArea);
	
	public void appendConsole(Player player);
	
	public void setRed();
	
	public void transferFocus();
	
	public void clearBoard();
	
	public void clearTextArea();
	
	public void assignStartPieces(ArrayList<Player> players);

}
