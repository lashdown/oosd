package model.pieces;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Horsemen extends Piece {

	private int strength;
	private int moves;
	private Image image;
	private ImageIcon icon;
	
	public Horsemen() {
		try {
			image = ImageIO.read(new File("src/resources/horse.png"));
			icon = new ImageIcon(image);
		} catch(IOException e){
		
		}
	
	}
	
	public ImageIcon getIcon() {
		return icon;
	}
	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}
}
