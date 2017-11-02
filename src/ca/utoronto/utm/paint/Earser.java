package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Graphics2D;

public class Earser extends Shape {


	public Earser(Point start, Color color, String style) {
		super(start, color, style);
	}
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(this.getColor());
		g2d.clearRect(this.getStart().getX(), this.getStart().getY(), 20, 30);
	}
}
