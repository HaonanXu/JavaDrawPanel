package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * 
 * @author wangyang xia
 *
 */
public class Square extends Shape{
	private int length;
	
	public Square(Point s, Color color, int l, String sty) {
		super(s,color,sty);
		this.length = l;
	}
	
	public int getLength() {
		return length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(this.getColor());
		if (this.getStyle() == "fill") g2d.fillRect(this.getStart().getX(), this.getStart().getY(), this.length, this.length);
		if (this.getStyle() == "outline") g2d.drawRect(this.getStart().getX(), this.getStart().getY(), this.length, this.length);
	}
}
