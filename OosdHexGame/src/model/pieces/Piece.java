package model.pieces;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Piece {
	
	private int strength;
	private int moves;
	private Image image;
	private ImageIcon icon;
	private int[] location;
	
	public ImageIcon getIcon() {
		return icon;
	}
	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}
	public int[] getLocation() {
		return location;
	}
	public void setLocation(int[] location) {
		this.location = location;
	}
	
	
}
