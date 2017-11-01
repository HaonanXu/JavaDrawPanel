package ca.utoronto.utm.paint;

import java.awt.Color;

public class Line extends Shape{
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

}
