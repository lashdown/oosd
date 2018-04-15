package controllers;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Player;
import model.interfaces.GameEngine;
import view.GUI;
import view.HexButton;

public class TurnListen implements MouseListener{
	private HexButton button;
	private GameEngine ge;
	private GUI GUI;
	private Player player;
	
	public TurnListen(GameEngine ge, GUI GUI, HexButton button, Player player){
		this.ge = ge;
		this.button = button;
		this.GUI = GUI;
		this.player = player;
	}
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
		else {
			button.setColor(Color.BLUE);
			Movement move = new Movement(ge, player);
			move.showAvailableMoves(button, GUI);
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {

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

}
