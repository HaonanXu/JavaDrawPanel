package ca.utoronto.utm.paint;

import java.awt.Color;

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
}
