package model.interfaces;

import java.awt.event.MouseListener;

import model.HexButton;
import model.Player;

public interface GameEngine {
	
	public abstract Boolean startGame(int size);

	public abstract void loadStartingPieces();
	
	public void playerTurn(Player player);
	
	public void removeListener();
	
	public MouseListener addAvailListener(Player player, HexButton button, HexButton check);
}
