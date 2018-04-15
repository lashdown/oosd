package view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class Background extends JPanel{

	private Image bgImage;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void setBgImage(Image bgImage) {
		this.bgImage = bgImage;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bgImage, 0, 0, null);
	}
}
