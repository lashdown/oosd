package model.interfaces;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

import model.HexButton;
import model.Player;

public interface Board{

	public abstract JPanel createBoard(GameEngine engine, int size);

	public abstract JToolBar createToolbar();
	
	public HexButton[][] getHexButtons();
	
	public void setHexButtons(HexButton[][] hexButtons);
	
	public void setGamePiece(ImageIcon icon, int x, int y);
	
	//public void showAvailableMoves(HexButton button, ArrayList<HexButton> buttons);
	
	public HexButton getHex(HexButton button);
	
	public JScrollPane getPane();
	
	public void setPane(JScrollPane pane);
	
	public JTextArea getTextArea();
	
	public void setTextArea(JTextArea textArea);
	
	public void appendConsole(Player player);
	
	public void setRed();
	
	public void transferFocus();

}
