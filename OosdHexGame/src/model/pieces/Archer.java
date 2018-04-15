package model.pieces;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Archer extends Piece {

	private int strength;
	private int moves;
	private Image image;
	private ImageIcon icon;
	
	public Archer() {
		try {
			image = ImageIO.read(new File("src/resources/bow.png"));
			icon = new ImageIcon(image);
		} catch(IOException e){
		
		}
	
	}
}
