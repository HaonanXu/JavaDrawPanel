package ca.utoronto.utm.paint;

import javax.swing.*;  

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/

class PaintPanel extends JPanel implements Observer, MouseMotionListener, MouseListener  {
	private static final Color Color = null;
	private int i=0;
	private PaintModel model; // slight departure from MVC, because of the way painting works
	private View view; // So we can talk to our parent or other components of the view
	
	// Draw panel function tools
	private Color color;
	private String mode; // modifies how we interpret input (could be better?
	private Circle circle; 
	private Rectangle rectangle; 
	private Square square;
	private Line line; 
	private String style; // outline style or fill in style.
	
	public PaintPanel(PaintModel model, View view){
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(300,300));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		this.style = "outline";

		this.mode="";
		this.model = model;
		this.model.addObserver(this);
		this.view=view;
		this.color = Color.BLACK;
	}

	/**
	 *  View aspect of this
	 */
	public void paintComponent(Graphics g) {
		// Use g to draw on the JPanel, lookup java.awt.Graphics in
		// the javadoc to see more of what this can do for you!!
		
        super.paintComponent(g); //paint background
        Graphics2D g2d = (Graphics2D) g; // lets use the advanced api
		// Origin is at the top left of the window 50 over, 75 down
        
        g2d.drawString ("i="+i, 50, 75);
		i=i+1;

		this.draw(g2d);
		
		g2d.dispose();
	}
	
	public void draw(Graphics2D g2d) {
		ArrayList<Shape> shapes = this.model.getShape();
		
		for (Shape s : shapes) {
			s.draw(g2d);
		}
	}
	
	public void setdrawColor(Color string) {
		this.color = string;
	}
	
	public void setstyle(String sty) {
		if (sty == "outline") {this.style = "outline";}
		if (sty == "fill") {this.style = "fill";}
	}
	
	public void update(Observable o, Object arg) {
		if (o instanceof PaintModel) this.repaint();
	}
	
	/**
	 *  Controller aspect of this
	 */
	public void setMode(String mode){
		this.mode = mode;
	}

	public void mouseDragged(MouseEvent e) {
		Point point = new Point(e.getX(),e.getY());
		
		switch (this.mode) {
		case "Squiggle":
			this.model.addShape(new Line(point, this.color, point));
			
			break;
		case "Circle":
			int radius = Math.abs(this.circle.getStart().getX()-e.getX());
			this.circle.setRadius(radius);
			this.model.addShape(this.circle);
			
			break;
		case "Rectangle":
			int width = Math.abs(this.rectangle.getStart().getX()-e.getX());
			int height = Math.abs(this.rectangle.getStart().getY()-e.getY());
			this.rectangle.setHeight(height);
			this.rectangle.setWidth(width);
			this.model.addShape(this.rectangle);
			
			break;
		case "Square":
			int length = Math.abs(this.square.getStart().getX()-e.getX());
			this.square.setLength(length);
			this.model.addShape(this.square);
			
			break;
		case "Line":
			Point end = new Point(e.getX(), e.getY());
			this.line.setEnd(end);
			this.model.addShape(line);
			
			break;
		case "Eraser":
			this.model.addShape(new Earser(point, this.getBackground(), null));
			
			break;
		case "Pencil":
			this.model.addShape(new Pencil(point, this.color, point));
			
			break;
		}
	}
	
	public void mousePressed(MouseEvent e) {
		Point point = new Point(e.getX(),e.getY());

		switch (this.mode) {
			case "Squiggle":
				this.model.addShape(new Line(point, this.color, point));
				
				break;
			case "Circle":
				this.circle=new Circle(point, 0, this.color,this.style);
				
				break;
			case "Rectangle":
				this.rectangle = new Rectangle(point, this.color, 0, 0, this.style);
				
				break;
			case "Square":
				this.square = new Square(point, this.color, 0, this.style);
				
				break;
			case "Line":
				this.line = new Line(point, this.color, point);
				
				break;
			case "Eraser":
				this.model.addShape(new Earser(point, this.getBackground(), null));
				
				break;
			case "Pencil":
				this.model.addShape(new Pencil(point, this.color, point));
				
				break;
		}
	}
	
	public void mouseMoved(MouseEvent e) {
	}
	
	public void mouseClicked(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
}
