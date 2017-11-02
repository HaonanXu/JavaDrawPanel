package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * 
 * @author wangyang xia
 *
 */
public class Rectangle extends Shape{
	private int width;
	private int height;
	
	public Rectangle(Point s,Color c, int h, int w, String sty) {
		super(s,c,sty);
		this.height = h;
		this.width = w;
	}


	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(this.getColor());
		if (this.getStyle() == "fill") g2d.fillRect(this.getStart().getX(), this.getStart().getY(), this.width, this.height);
		if (this.getStyle() == "outline") g2d.drawRect(this.getStart().getX(), this.getStart().getY(), this.width, this.height);
	}
}
