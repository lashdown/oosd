/*******************************************************************************
 * Laurence Ashdown
 * OOSD Assignment
 * RMIT Semester 1 2018
 ******************************************************************************/
package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import model.interfaces.Hex;

public class HexButton extends JButton implements Hex{

	private static final long serialVersionUID = 1L;
	static int aSide = (int) (45 * Math.tan(Math.toRadians(30)));
	static int xPoints[] = {50, 90, 90, 50, 10, 10};
	static int yPoints[] = {5, aSide+5, 95-aSide, 95, 95-aSide, aSide+5};
    static BasicStroke stroke = new BasicStroke ( 5f );
    private Boolean selected = false;
    private Color color = Color.RED;
    private int x, y;
   
    public HexButton() {
    	
	}
    
    public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	
	public void drawHex(Graphics2D g, Color color) {
		g.setStroke(stroke);
		g.setColor (color);
        g.fillPolygon ( xPoints, yPoints, 6 );
        g.setColor (Color.DARK_GRAY);
        g.drawPolygon ( xPoints, yPoints, 6 );
        g.setBackground(color);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2D = ( Graphics2D ) g;
        drawHex(g2D, color);
        super.paintComponent(g);
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	/**
	 * @return the x
	 */
	public int getHexX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setHexX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getHexY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setHexY(int y) {
		this.y = y;
	}

	

	
}
