/*******************************************************************************
 * Laurence Ashdown
 * OOSD Assignment
 * RMIT Semester 1 2018
 ******************************************************************************/
package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

import model.pieces.Piece;
import view.interfaces.Hex;

/**
 * The HexButton class is an extension of JButton which is the basis for the hexagons on
 * which the game is played. Using trigonometry the hexagons are drawn based on a 100px
 * sized button.
 * 
 * The buttons also store a player's game piece, and using the setPiece function, sets the
 * piece and also takes it's corresponding icon, and applies it to the HexButton.
 * @author laurence
 *
 */

public class HexButton extends JButton implements Hex{

	private static final long serialVersionUID = 1L;
	static int aSide = (int) (45 * Math.tan(Math.toRadians(30)));
	static int xPoints[] = {50, 90, 90, 50, 10, 10};
	static int yPoints[] = {5, aSide+5, 95-aSide, 95, 95-aSide, aSide+5};
    static BasicStroke stroke = new BasicStroke ( 5f );
    private Color color = Color.RED;
    private int x, y;
    private Piece piece;
   
   
    public HexButton() {
    	
	}
    
    @Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	
	@Override
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

	/**
	 * @return the x
	 */
	@Override
	public int getHexX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	@Override
	public void setHexX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	@Override
	public int getHexY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	@Override
	public void setHexY(int y) {
		this.y = y;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		if(piece != null) {
			this.piece = piece;
			this.setIcon(piece.getIcon());
		}
		else {
			this.setIcon(null);
			this.piece = null;
		}
		
		
	}
	

	

	
}
