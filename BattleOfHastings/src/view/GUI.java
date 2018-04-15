/*******************************************************************************
 * Laurence Ashdown
 * OOSD Assignment
 * RMIT Semester 1 2018
 ******************************************************************************/
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

import model.Player;
import model.interfaces.GameEngine;
import model.pieces.Piece;

/** 
 * The GUI class handles the creation of the board and tool bar / info panel.
 * It also handles the displaying of player pieces and holds the array of 
 * HexButtons that make up the board.
 * 
 * There are a number of utility functions such as clearBoard(), removes all pieces,
 * transferFocus() which iterates through all the HexButtons and gives focus to them in
 * order to properly update the display of modified buttons, and setRed() to recolor the
 * board.
 * 
 * @author laurence
 * 
 */


public class GUI implements view.interfaces.Board{

	private HexButton[][] hexButtons;
	private GameEngine ge;
	private int size;
    private JScrollPane pane = null;
    private JTextArea textArea = null;
    
	@Override
	public JTextArea getTextArea() {
		return textArea;
	}

	@Override
	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public GUI() {
		createPane();
	}

	@Override
	public JPanel createBoard(GameEngine engine, int size) {
		this.ge = engine;
		Background hexBoard = new Background();
		hexButtons = new HexButton[size][size];
		hexBoard.setLayout(new BorderLayout());
		this.size = size;
        int offsetX = 0;
        int offsetY = 10;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
            	HexButton b = new HexButton();
            	b.setBorderPainted(false);
            	b.setMargin(new Insets(0,0,0,0));
            	b.setContentAreaFilled(false);
            	b.setFocusPainted(false);
                hexButtons[i][j] = b;
                
            	hexButtons[i][j].setBounds(offsetX, offsetY, 100, 100);
            	hexButtons[i][j].setPreferredSize(new Dimension(100, 100));
            	offsetX += 90;
            	if((j == 0 || (j == size - 1 && i%2 == 0))&& i != (size)/2) {
            		hexButtons[i][j].setVisible(false);
            	}
            	if((j == 1 || j == size - 1) && (i < size/4 || i > 3*size/4)) {
            		hexButtons[i][j].setVisible(false);
            	}
            	if(j == size - 2 && (i == 0 || i == size -1)) {
            		hexButtons[i][j].setVisible(false);
            	}
            	if(b.isVisible()) {
            		b.setHexX(i);
            		b.setHexY(j);
            	}
            	hexBoard.add(hexButtons[i][j]);
                }
            if(i%2 == 0) {
                offsetX = -45;
            } else {
                offsetX = 0;
            }
            offsetY += 75;
            
        }
        hexBoard.setVisible(true);
        Image bg;
		try {
			bg = ImageIO.read(new File("src/resources/hill2.png"));
			hexBoard.setBgImage(bg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return hexBoard;
	}

	@Override
	public JToolBar createToolbar() {
		JToolBar toolbar = new JToolBar();
		toolbar.setRollover(true);
	    JButton button = new JButton("Load/Reset Pieces");
	    button.addActionListener(new ActionListener() { 
	    	  @Override
			public void actionPerformed(ActionEvent e) {
	    		  clearBoard();
	    		  clearTextArea();
	    		  ge.loadStartingPieces();
	    	  } 
	    	});
	    toolbar.add(button);
	    toolbar.addSeparator();
	    toolbar.setFloatable(false);
		return toolbar;
	}
	
	
	@Override
	public void clearBoard() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				hexButtons[i][j].setPiece(null);;
			}
		}
		
	}
	


	@Override
	public HexButton[][] getHexButtons(){
		return this.hexButtons;
	}
	
	@Override
	public void setHexButtons(HexButton[][] hexButtons) {
		this.hexButtons = hexButtons;
	}
	
	@Override
	public void assignStartPieces(ArrayList<Player> players) {
		int x = 2, y = 0;
		for(Player player : players) {
			x = 2;
			for(Piece piece : player.getPieces()) {
				hexButtons[y][x].setPiece(piece);
				x++;
			}
			consolePieces(player);
			y = 8;
		}
	}
	
	public void createPane() {
		textArea = new JTextArea(5, 0);
		textArea.setEditable(false);
		textArea.setAutoscrolls(true);
		
		pane = new JScrollPane(textArea);
		
	}
	
	public void consolePieces(Player player) {
		textArea.append(player.getName()+ "'s pieces: ");
		for(HexButton button : ge.getPlayerPieceLocations(player)) {
			textArea.append("["+button.getHexX()+", " + button.getHexY() + "] ");
		}
		textArea.append("\n");
	}
	@Override
	public void appendConsole(Player player) {
		textArea.append(player.getName()+ "'s turn.\n");
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}


	@Override
	public JScrollPane getPane() {
		return pane;
	}

	@Override
	public void setPane(JScrollPane pane) {
		this.pane = pane;
	}
	
	
	@Override
	public void transferFocus() {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				hexButtons[i][j].transferFocus();
			}
		}
	}
	
	@Override
	public void setRed() {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				hexButtons[i][j].transferFocus();
				hexButtons[i][j].setColor(Color.RED);
				
			}
		}
	}
	
	@Override
	public void clearTextArea() {
		textArea.setText(null);
		
	}
}
