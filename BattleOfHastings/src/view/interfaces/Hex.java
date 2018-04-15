/*******************************************************************************
 * Laurence Ashdown
 * OOSD Assignment
 * RMIT Semester 1 2018
 ******************************************************************************/
package view.interfaces;

import java.awt.Color;
import java.awt.Graphics2D;

public interface Hex{
	
	public Color getColor();
	
	public void setColor(Color color);
	
	public void drawHex(Graphics2D g, Color color);
	
	public int getHexX();
	
	public void setHexX(int x);
	
	public int getHexY();
	
	public void setHexY(int y);
}
