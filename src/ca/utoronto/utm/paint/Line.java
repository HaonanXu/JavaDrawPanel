package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Graphics2D;

public class Line extends Shape {
	private Point end;
	
	public Line(Point start, Color color,Point end) {
		super(start, color,"outline");
		this.end = end;
	}

	public Point getEnd() {
		return end;
	}

	public void setEnd(Point end) {
		this.end = end;
	}
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(this.getColor());
		g2d.drawLine(this.getStart().getX(), this.getStart().getY(), this.end.getX(), this.end.getY());
	}
}
