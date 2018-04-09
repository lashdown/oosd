package model;

import java.util.ArrayList;



public class Player{

	private String name;
	private int score;
	private ArrayList<HexButton> pieces = new ArrayList<HexButton>();
	
	
	public Player(String name) {
		this.name = name;
	}
	
	public Player getPlayer(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<HexButton> getPieces() {
		return pieces;
	}

	public void setPieces(ArrayList<HexButton> pieces) {
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
	
	public void addPiece(HexButton piece) {
		pieces.add(piece);
	}
	
	public void removePiece(HexButton piece) {
		for(HexButton button : pieces) {
			if(button == piece) {
				pieces.remove(piece);
			}
		}
	}

}
