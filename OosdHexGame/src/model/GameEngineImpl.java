/*******************************************************************************
 * Laurence Ashdown
 * OOSD Assignment
 * RMIT Semester 1 2018
 ******************************************************************************/
package model;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import controllers.TurnListen;
import model.interfaces.GameEngine;
import model.pieces.Footman;
import model.pieces.Horsemen;
import model.pieces.Piece;
import view.GUI;
import view.HexButton;

public class GameEngineImpl implements GameEngine {

	private view.GUI GUI = new GUI();
	private int size;
	private Player player1 = new Player("Joe");
	private Player player2 = new Player("Bob");
	private ArrayList<Player> players = new ArrayList<Player>();
	
	public GameEngineImpl() {
		players.add(player1);
		players.add(player2);
		
		// Load images and set icon images.
	}

	@Override
	public Boolean startGame(int size) {
		this.size = size;
		JFrame frame = new JFrame("Battle of Hastings");
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
		
		// Add player(s) starting units
		
		for(int i = 0; i < 5; i++) {
			player1.addPiece(new Footman());
			player2.addPiece(new Horsemen());
		}
		
		GUI.assignStartPieces(players);
	}
	
	public ArrayList<HexButton> getPlayerPieceLocations(Player player){
		ArrayList<HexButton> buttonList = new ArrayList<HexButton>();
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				for(Piece piece : player.getPieces()) {
					if(GUI.getHexButtons()[i][j].getPiece() == piece) {
						buttonList.add(GUI.getHexButtons()[i][j]);
					}
				}
			}
		}
		return buttonList;
		
	}
	
	public void playerTurn(Player player) {
		removeAllListeners();
		addMouseListeners(player);
	}
	
	public void addMouseListeners(Player player) {
		for(HexButton button : getPlayerPieceLocations(player)) {
			TurnListen tl = new TurnListen(this, GUI, button, player);
			button.addMouseListener(tl);
		}
	}
	public void removeAllListeners() {
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
	
	public void removeMouseListeners(Player player) {
		for(HexButton button : getPlayerPieceLocations(player)) {
			MouseListener ml[] = button.getMouseListeners();
			for(int i = 0; i < ml.length; i++) {
				button.removeMouseListener(ml[i]);
			}
			
		}
	}
	
	public Player switchPlayer(Player player) {
		if(player == player1) {
			player = player2;
		}
		else {
			player = player1;
		}
		
		return player;
	}


	
}
