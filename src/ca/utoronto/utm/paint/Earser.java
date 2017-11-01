package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Graphics2D;

public class Earser {

	public Graphics2D g2d;
	public Point point;
	
	public Earser(Point p, Graphics2D g) {
		this.g2d = g;
		this.point = p;
	}
	
	public void erase(Point p) {
		this.g2d.clearRect(p.x, p.y, 20, 30);
	}
}
