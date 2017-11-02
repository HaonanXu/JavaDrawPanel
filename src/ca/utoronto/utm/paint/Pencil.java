package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Graphics2D;

public class Pencil extends Line {

	public Pencil(Point center, Color color, Point end) {
		super(center, color, end);
	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(this.getColor());
		int[] xPoints = new int[] {this.getStart().getX(), this.getEnd().getX()};
		int[] yPoints = new int[] {this.getStart().getY(), this.getEnd().getY()};
		g2d.drawPolyline(xPoints, yPoints, 2);
	}
}
