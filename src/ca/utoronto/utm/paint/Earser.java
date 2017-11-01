package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Graphics2D;

public class Earser {

	public Graphics2D g2d;
	public Color color;
	
	public Earser(Graphics2D g, Color color) {
		this.g2d = g;
		this.color = color;
	}
	
	public void erase(Point p) {
		this.g2d.setColor(this.color);
		this.g2d.clearRect(p.x, p.y, 20, 30);
	}
}
