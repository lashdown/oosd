package controllers;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Player;
import model.interfaces.GameEngine;
import view.HexButton;

public class MoveListen implements MouseListener {
	
	private HexButton button, check;
	private GameEngine ge;
	private Player player;
	
	public MoveListen(GameEngine ge, HexButton button, HexButton check, Player player) {
		this.button = button;
		this.check = check;
		this.ge = ge;
		this.player = player;

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		button.setColor(Color.RED);
		Movement move = new Movement(ge, player);
		move.movePiece(button, check);
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		check.setColor(Color.BLUE);
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		check.setColor(Color.GREEN);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
