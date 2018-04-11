package model;

import model.interfaces.GamePiece;

public class Piece implements GamePiece {

	private int moves;
	private int strength;
	private int tier;
	
	@Override
	public GamePiece mergePieces(GamePiece piece1, GamePiece piece2) {
		// TODO Auto-generated method stub
		return null;
	}
	public int getMoves() {
		return moves;
	}
	public void setMoves(int moves) {
		this.moves = moves;
	}
	public int getStrength() {
		return strength;
	}
	public void setStrength(int strength) {
		this.strength = strength;
	}
	public int getTier() {
		return tier;
	}
	public void setTier(int tier) {
		this.tier = tier;
	}
}
