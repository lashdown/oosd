package model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import model.interfaces.Board;
import model.interfaces.GameEngine;


public class GUI implements Board{

	private HexButton[][] hexButtons;
	private GameEngine ge;
	private int size;
    private JScrollPane pane = null;
    private JTextArea textArea = null;
    
	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public GUI() {
		createPane();
		// TODO Auto-generated constructor stub 
	}

	@Override
	public JPanel createBoard(GameEngine engine, int size) {
		this.ge = engine;
		JPanel hexBoard = new JPanel();
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
        hexBoard.setBackground(Color.CYAN);
		return hexBoard;
	}

	
	
	@Override
	public JToolBar createToolbar() {
		JToolBar toolbar = new JToolBar();
		toolbar.setRollover(true);
	    JButton button = new JButton("Load/Reset Pieces");
	    button.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) {
	    		  
	    		  ge.loadStartingPieces();
	    	  } 
	    	});
	    toolbar.add(button);
	    toolbar.addSeparator();
	    toolbar.setFloatable(false);
		return toolbar;
	}
	
	
	

	
	public HexButton getHex(HexButton button) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if(hexButtons[i][j] == button) {
					return button;
				}
			}
		}
		return null;
	}
	

	public HexButton[][] getHexButtons(){
		return this.hexButtons;
	}
	
	public void setHexButtons(HexButton[][] hexButtons) {
		this.hexButtons = hexButtons;
	}
	
	public void setGamePiece(ImageIcon icon, int x, int y) {
		hexButtons[x][y].setIcon(icon);
	}
	
	public void createPane() {
		textArea = new JTextArea(5, 0);
		textArea.setEditable(false);
		textArea.setAutoscrolls(true);
		
		pane = new JScrollPane(textArea);
		
	}
	
	public void appendConsole(Player player) {
		getTextArea().append(player.getName()+ "'s pieces: ");
		for(HexButton button : player.getPieces()) {
			getTextArea().append("[" + Integer.toString(button.getHexX())
					+", " + Integer.toString(button.getHexY()) + "]  ");
		}
		getTextArea().append("\n");
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}

	public JScrollPane getPane() {
		return pane;
	}

	public void setPane(JScrollPane pane) {
		this.pane = pane;
	}
	
	public void transferFocus() {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				hexButtons[i][j].transferFocus();
			}
		}
	}
	
	public void setRed() {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				hexButtons[i][j].transferFocus();
				hexButtons[i][j].setColor(Color.RED);
				
			}
		}
	}
}
