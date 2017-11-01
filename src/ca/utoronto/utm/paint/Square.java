package ca.utoronto.utm.paint;

import java.awt.Color;

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
}
