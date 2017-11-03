package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * 
 * @author wangyang xia
 *
 */
public class Square extends Shape{
	private Point currentPoint;
	
	public Square(Point start, Color color, String style) {
		super(start,color,style);
		this.currentPoint = start;
		System.out.println("new");
	}
	
	public void setCurrentPoint(Point point) {
		this.currentPoint = point;
	}
	
	public Point getCurrentPoint() {
		return this.currentPoint;
	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(this.getColor());

		int xDiff = Math.abs(this.currentPoint.getX() - this.getStart().getX());
		int yDiff = Math.abs(this.currentPoint.getY() - this.getStart().getY());
		int length = Math.max(xDiff, yDiff);
		
		Point drawPoint = null;
		int x1 = this.getStart().getX();
		int y1 = this.getStart().getY();
		int x2 = this.currentPoint.getX();
		int y2 = this.currentPoint.getY();
		
        if (x1 > x2)
        {
            int z = 0;
            z = x1;
            x1 = x2;
            x2 = z;
        }
        if (y1 > y2)
        {
            int z = 0;
            z = y1;
            y1 = y2;
            y2 = z;
        }
        

		
		if (this.getStyle() == "fill") g2d.fillRect(x1, y1, length, length);
		if (this.getStyle() == "outline") g2d.drawRect(x1, y1, length, length);
	}
}
