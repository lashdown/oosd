/*******************************************************************************
 * Laurence Ashdown
 * OOSD Assignment
 * RMIT Semester 1 2018
 ******************************************************************************/
package model;

import java.util.ArrayList;

import model.pieces.Piece;



public class Player{

	private String name;
	private int score;
	private ArrayList<Piece> pieces = new ArrayList<Piece>();
	
	
	public Player(String name) {
		this.name = name;
	}
	
	public Player getPlayer(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Piece> getPieces() {
		return pieces;
	}

	public void setPieces(ArrayList<Piece> pieces) {
		this.pieces = pieces;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addPiece(Piece piece) {
		pieces.add(piece);
	}
	
	public void removePiece(Piece piece) {
		for(Piece find : pieces) {
			if(find == piece) {
				pieces.remove(piece);
			}
		}
	}

}
