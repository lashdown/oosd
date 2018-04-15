package controllers;

import java.awt.Color;

import model.Player;
import model.interfaces.GameEngine;
import view.GUI;
import view.HexButton;

/**
 * The movement class handles the logic of a players move. It is first called when a player
 * selects a piece they wish to move, and the showAvailableMoves function graphically 
 * indicates the places in which the selected piece can move. The MoveListener is then 
 * added to those locations.
 * 
 * Once the user has selected a location to move to, the control is passed back this this
 * class and the movePiece function is called, which adds the user's piece to the selected
 * location, and removes it from it's current location. This in turn calls the playerTurn
 * function with the switchPlayer function as an argument, passing the control back to 
 * GameEngine with the new player.
 * 
 * @author laurence
 *
 */
public class Movement {
	private GameEngine ge;
	private Player player;

	Movement(GameEngine ge, Player player) {
		this.ge = ge;
		this.player = player;

	}

	public void showAvailableMoves(HexButton button, GUI GUI) {

		int minX = 0, maxX = 0;

		for (int i = -1; i < 2; i++) {

			if (button.getHexX() % 2 == 1 && i == 0) {
				maxX = 1;
			} else if (button.getHexX() % 2 == 1) {
				minX = -1;
				maxX = 0;
			}
			if (button.getHexX() % 2 == 0 && i == 0) {
				minX = -1;
			}

			else if (button.getHexX() % 2 == 0) {
				minX = 0;
				maxX = 1;
			}

			for (int j = minX; j <= maxX; j++) {

				try {

					HexButton check = GUI.getHexButtons()[button.getHexX() + i]
							[button.getHexY() + j];

					if (check.getPiece() == null) {
						check.setColor(Color.GREEN);
						MoveListen ml = new MoveListen(ge, button, check, player);
						check.addMouseListener(ml);
					}

				} catch (ArrayIndexOutOfBoundsException e) {

				}
			}

		}
		GUI.transferFocus();

	}

	public void movePiece(HexButton button, HexButton next) {
		next.setPiece(button.getPiece());
		button.setPiece(null);
		ge.playerTurn(ge.switchPlayer(player));

	}

}
