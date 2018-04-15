package model.pieces;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Pikeman extends Piece {

	private int strength;
	private int moves;
	private Image image;
	private ImageIcon icon;
	
	public Pikeman() {
		try {
			image = ImageIO.read(new File("src/resources/spear.png"));
			icon = new ImageIcon(image);
		} catch(IOException e){
		
		}
	
	}
	
	@Override
	public ImageIcon getIcon() {
		return icon;
	}
	@Override
	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}
}
