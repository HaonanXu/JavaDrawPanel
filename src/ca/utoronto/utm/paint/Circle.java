package ca.utoronto.utm.paint;

import java.awt.Color;

public class Circle extends Shape{
	private int radius;
	public Circle(Point centre, int radius, Color color, String sty){
		super(centre, color,sty);
		this.radius = radius;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

}
