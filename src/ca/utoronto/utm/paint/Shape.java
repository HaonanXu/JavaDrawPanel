package ca.utoronto.utm.paint;

import java.awt.Color;

public class Shape {
	private Color color;
	private Point start;
	private String style;
	public Shape(Point start, Color color, String sty) {
		this.color = color;
		this.start = start;
		this.style = sty;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public String getStyle() {
		return this.style;
	}

	public Point getStart() {
		return start;
	}

	public void setStart(Point start) {
		this.start = start;
	}
	
	
}
