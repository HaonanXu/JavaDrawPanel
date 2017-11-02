package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Graphics2D;

public class Shape {
	private Color color;
	private Point start;
	private String style;
	
	public Shape(Point start, Color color, String style) {
		this.color = color;
		this.start = start;
		this.style = style;
	}

	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public String getStyle() {
		return this.style;
	}

	public Point getStart() {
		return this.start;
	}

	public void setStart(Point start) {
		this.start = start;
	}
	
	public void draw(Graphics2D g2d) {}
}
