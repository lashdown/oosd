/*******************************************************************************
 * Laurence Ashdown
 * OOSD Assignment
 * RMIT Semester 1 2018
 ******************************************************************************/
package model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import model.interfaces.Board;
import model.interfaces.GameEngine;

public class GameEngineImpl implements GameEngine {

	private Board GUI = new GUI();
	private int size;
	private Player player1 = new Player("Joe");
	private Player player2 = new Player("Bob");
	private Image imgGun, imgMan, imgHelmet, imgShield, imgArrow, imgBow, imgHorse, imgSpear;
	@SuppressWarnings("unused")
	private ImageIcon gun, man, helmet, shield, arrow, bow, horse, spear;

	public GameEngineImpl() {
		
		// Load images and set icon images.
		
		try {
			imgGun = ImageIO.read(new File("src/resources/gun.png"));
			imgMan = ImageIO.read(new File("src/resources/man.png"));
			imgShield = ImageIO.read(new File("src/resources/shield.png"));
			imgHelmet = ImageIO.read(new File("src/resources/helmet.png"));
			imgArrow = ImageIO.read(new File("src/resources/arrow.png"));
			imgBow = ImageIO.read(new File("src/resources/bow.png"));
			imgHorse = ImageIO.read(new File("src/resources/horse.png"));
			imgSpear = ImageIO.read(new File("src/resources/spear.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gun = new ImageIcon(imgGun);
		man = new ImageIcon(imgMan);
		shield = new ImageIcon(imgShield);
		helmet = new ImageIcon(imgHelmet);
		arrow = new ImageIcon(imgArrow);
		bow = new ImageIcon(imgBow);
		horse = new ImageIcon(imgHorse);
		spear = new ImageIcon(imgSpear);
	}

	@Override
	public Boolean startGame(int size) {
		this.size = size;
		JFrame frame = new JFrame("Hex Board");
		Container contentPane = frame.getContentPane();

		contentPane.add(GUI.createBoard(this, size), BorderLayout.CENTER);
		contentPane.add(GUI.createToolbar(), BorderLayout.NORTH);
		contentPane.add(GUI.getPane(), BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationByPlatform(true);
		frame.setSize(size * 91, size * 87 + 70);
		frame.setVisible(true);
		loadStartingPieces();
		playerTurn(player1);
		return null;
	}

	@Override
	public void loadStartingPieces() {
		
		player1.getPieces().clear();
		player2.getPieces().clear();
		
		// Manually set both player starting units
		
		GUI.getHexButtons()[0][2].setIcon(horse);
		GUI.getHexButtons()[0][size-3].setIcon(horse);
		GUI.getHexButtons()[0][3].setIcon(bow);
		GUI.getHexButtons()[0][size-4].setIcon(bow);
		GUI.getHexButtons()[0][4].setIcon(shield);
		
		GUI.getHexButtons()[8][2].setIcon(helmet);
		GUI.getHexButtons()[8][size-3].setIcon(helmet);
		GUI.getHexButtons()[8][3].setIcon(spear);
		GUI.getHexButtons()[8][size-4].setIcon(spear);
		GUI.getHexButtons()[8][4].setIcon(gun);
		
		// Load starting units into player ArrayList, player1, player2
		
			for (int i = 0; i < size; i++) {
				if (i > 1 && i < size - 2) {
					player1.addPiece(GUI.getHexButtons()[0][i]);
					player2.addPiece(GUI.getHexButtons()[size - 1][i]);
				}
			}
		
		GUI.getTextArea().setText(null);
		
		GUI.getTextArea().append(player1.getName() + "'s pieces: ");
		for(HexButton button : player1.getPieces()) {
			GUI.getTextArea().append("[" + Integer.toString(button.getHexX())
					+", " + Integer.toString(button.getHexY()) + "]  ");
		}
		GUI.getTextArea().append("\n");
		GUI.getTextArea().append(player2.getName() + "'s pieces: ");
		for(HexButton button : player2.getPieces()) {
			GUI.getTextArea().append("[" + Integer.toString(button.getHexX())
					+", " + Integer.toString(button.getHexY()) + "]  ");
		}
		GUI.getTextArea().append("\n");
	}

	public void playerTurn(Player player) {
		
		GUI.getTextArea().append(player.getName() + "'s turn.\n");
		for (HexButton button : player.getPieces()) {
			if (button == GUI.getHex(button)) {
				button.addMouseListener(addTurnListener(player, button));
			}
		}
	}

	protected void showAvailableMoves(HexButton button, Player player) {
		// TODO Auto-generated method stub
		int minX = 0,  maxX = 0, doubleMove = 2;
		if(button.getIcon() == horse) {
			doubleMove = 3;
		}
		for (int i = -1; i < doubleMove; i++) { //Will always be this, one up one down
			
			if(button.getHexX()%2==1 && i == 0) {
				maxX = 1;
			}
			else if(button.getHexX()%2==1) {
				minX = -1;
				maxX = 0;
			}
			if(button.getHexX()%2==0 && i == 0) {
				minX = -1;
			}
			
			else if(button.getHexX()%2==0) {
				minX = 0;
				maxX = 1;	
			}
			
			
			for (int j = minX; j <= maxX; j++) {
				
				
				try {
					
					HexButton check= GUI.getHexButtons()[button.getHexX() + i]
							[button.getHexY() + j];
					
					if (check.getIcon() == null) {
						check.setColor(Color.GREEN);
						
						check.addMouseListener(addAvailListener(player, button, check));
					}
				} catch (ArrayIndexOutOfBoundsException e) {

				}
			}

		}
		GUI.transferFocus();
	}

	protected void movePiece(Player player, HexButton button, HexButton next) {
		// TODO Auto-generated method stub
		GUI.setRed();
		ArrayList<HexButton> removeList = new ArrayList<HexButton>();
		removeList.add(button);
		player.getPieces().removeAll(removeList);
		player.addPiece(next);
		next.setIcon(button.getIcon());
		button.setIcon(null);
		GUI.appendConsole(player);
		
		playerTurn(switchPlayer(player));
	}
	
	public Player switchPlayer(Player player) {
		removeListener();
		if(player == player1) {
			player = player2;
		}
		else {
			player = player1;
		}
		
		return player;
	}

	@Override
	public void removeListener() {
		for(int i = 0; i<size; i++) {
			for(int j = 0; j < size; j++) {
				
				MouseListener ml[] = GUI.getHexButtons()[i][j].getMouseListeners();
				for(int k = 0; k < ml.length; k++) {
					GUI.getHexButtons()[i][j].removeMouseListener(ml[k]);
					
				}
			}
		}
		GUI.setRed();
	}
	
	public MouseListener addTurnListener(Player player, HexButton button) {
		MouseListener turnListen = new MouseListener() {

			@Override
			public void mouseEntered(MouseEvent e) {

				if (button.getColor() != Color.BLUE) {
					button.setColor(Color.GREEN);
				} 
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				if(button.getColor() == Color.BLUE) {
					button.setColor(Color.GREEN);
				}
				// TODO Auto-generated method stub
				else {
					button.setColor(Color.BLUE);
					showAvailableMoves(button, player);
				}
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (button.getColor() != Color.BLUE) {
					button.setColor(Color.RED);
				}
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
			
		};
		return turnListen;
	}

	@Override
	public MouseListener addAvailListener(Player player, HexButton button, HexButton check) {
		MouseListener availListen = new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				button.setColor(Color.RED);
				movePiece(player, button, check);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				check.setColor(Color.BLUE);
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				check.setColor(Color.GREEN);
				check.repaint();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		return availListen;
	}
}
