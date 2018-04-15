package controllers;

import java.awt.Color;

import model.Player;
import model.interfaces.GameEngine;
import view.GUI;
import view.HexButton;

public class Movement {
	private GameEngine ge;
	private Player player;

	Movement(GameEngine ge, Player player) {
		this.ge = ge;
		this.player = player;

	}

	public void showAvailableMoves(HexButton button, GUI GUI) {

		int minX = 0, maxX = 0, doubleMove = 2;

		for (int i = -1; i < doubleMove; i++) { // Will always be this, one up one down

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

					HexButton check = GUI.getHexButtons()[button.getHexX() + i][button.getHexY() + j];

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
