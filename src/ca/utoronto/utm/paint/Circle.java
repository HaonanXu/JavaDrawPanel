package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Graphics2D;

public class Circle extends Shape {
	private int radius;
	public Circle(Point centre, int radius, Color color, String style){
		super(centre, color, style);
		this.radius = radius;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(this.getColor());
		
		if (this.getStyle() == "fill") g2d.fillOval(this.getStart().getX(), this.getStart().getY(), this.radius, this.radius);
		if (this.getStyle() == "outline") g2d.drawOval(this.getStart().getX(), this.getStart().getY(), this.radius, this.radius);
	}
}
