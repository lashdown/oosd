/*******************************************************************************
 * Laurence Ashdown
 * OOSD Assignment
 * RMIT Semester 1 2018
 ******************************************************************************/
package model.interfaces;

import java.util.ArrayList;

import model.Player;
import view.HexButton;

public interface GameEngine {
	
	public abstract Boolean startGame(int size);

	public abstract void loadStartingPieces();
	
	public void playerTurn(Player player);
	
	public Player switchPlayer(Player player);

	public ArrayList<HexButton> getPlayerPieceLocations(Player player);
}
