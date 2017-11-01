package ca.utoronto.utm.paint;

import javax.swing.*;  
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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
	private Color color;
	private String mode; // modifies how we interpret input (could be better?
	private Circle circle; // the circle we are building

	private Rectangle rectangle; //the rectangle we are building
	private Square square; //the square we are building
	private Line line; //the straight line we are building.
	private Earser earser;//the earser we are building
	private Pencil pencil; //the pencil we are building
	

	private Graphics2D g2d;
	private String style; // outline style or fill in style.

	public PaintPanel(PaintModel model, View view){
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(300,300));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		this.style = "outline";

		this.mode="";
		this.model = model;
		this.model.addObserver(this);
		
		this.view=view;
	}

	/**
	 *  View aspect of this
	 */
	public void paintComponent(Graphics g) {
		// Use g to draw on the JPanel, lookup java.awt.Graphics in
		// the javadoc to see more of what this can do for you!!
		
        super.paintComponent(g); //paint background
        Graphics2D g2d = (Graphics2D) g; // lets use the advanced api
        g2d.setBackground (Color.gray); 
		// Origin is at the top left of the window 50 over, 75 down

        g2d.drawString ("i="+i, 50, 75);
		i=i+1;

		// Draw Lines
		ArrayList<Point> points = this.model.getPoints();
		for(int i=0;i<points.size()-1; i++){
			Point p1=points.get(i);
			Point p2=points.get(i+1);
			g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		}
		//Draw StraightLine
		ArrayList<Line> line = this.model.getLine();
		for(Line l: this.model.getLine()) {
			int x = l.getStart().getX();
			int y = l.getStart().getY();
			int x1 = l.getEnd().getX();
			int y1 = l.getEnd().getY();
			g2d.setColor(l.getColor());
			g2d.drawLine(x, y, x1, y1);
		}
		// Draw Circles
		ArrayList<Circle> circles = this.model.getCircles();

		for(Circle c: circles){
			int x = c.getStart().getX();
			int y = c.getStart().getY();
			int radius = c.getRadius();
			g2d.setColor(c.getColor());
			if (c.getStyle() == "fill") {g2d.fillOval(x, y, radius, radius);}
			if (c.getStyle() == "outline") {g2d.drawOval(x, y, radius, radius);}	

		}
		
		//Draw Rectangle
		ArrayList<Rectangle> rectangle = this.model.getRectangle();
		for(Rectangle r: rectangle) {
			int x = r.getStart().getX();
			int y = r.getStart().getY();
			int height = r.getHeight();
			int width = r.getWidth();
			g2d.setColor(r.getColor());
			g2d.drawRect(x, y, width, height);
			if (r.getStyle() == "fill") {g2d.fillRect(x, y, width, height);}
			if (r.getStyle() == "outline") {g2d.drawRect(x, y, width, height);}	

		}
		
		//Draw Square
		ArrayList<Square> square = this.model.getSquare();
		for(Square s:square) {
			int x = s.getStart().getX();
			int y = s.getStart().getY();
			int length = s.getLength();
			g2d.setColor(s.getColor());
			g2d.drawRect(x, y, length, length);
			if (s.getStyle() == "fill") {g2d.fillRect(x, y, length, length);}
			if (s.getStyle() == "outline") {g2d.drawRect(x, y, length, length);}	
			
		}
		
		//eraser
//		ArrayList<Earser> earser = this.model.getEarser();
//		for(Earser e:earser){
//			int x = e.getStart().getX();
//			int y = e.getStart().getY();
//			int length = e.getLength();
//			g2d.setColor(this.getBackground());
//			g2d.clearRect(x, y, length, length);
//			
//		}
		
		//Pencil
		ArrayList<Pencil> pencil = this.model.getPencil();
		for(Pencil p: pencil){
			int x = p.getStart().getX();
			int y = p.getStart().getY();
			int x1 = p.getEnd().getX();
			int y1 = p.getEnd().getY();
			g2d.setColor(p.getColor());
			g2d.drawLine(x, y, x1, y1);
		}
		
		g2d.dispose();
	}
	
	public void setdrawColor(Color string) {
		this.color = string;
	}
	public void setstyle(String sty) {
		if (sty == "outline") {this.style = "outline";}
		if (sty == "fill") {this.style = "fill";}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// Not exactly how MVC works, but similar.
		this.repaint(); // Schedule a call to paintComponent
	}
	
	/**
	 *  Controller aspect of this
	 */
	public void setMode(String mode){
		this.mode = mode;
	}
	
	
	// MouseMotionListener below
	@Override
	public void mouseMoved(MouseEvent e) {
		if(this.mode == "Squiggle"){
			
		} else if(this.mode=="Circle"){
			
		} else if(this.mode == "Rectangle") {
			
		} else if(this.mode == "Line") {
			
		}
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		if(this.mode=="Squiggle"){
			this.model.addPoint(new Point(e.getX(), e.getY()));
		} else if(this.mode=="Circle"){
			int radius = Math.abs(this.circle.getStart().getX()-e.getX());
			this.circle.setRadius(radius);
			this.model.addCircle(this.circle);
		} else if(this.mode == "Rectangle") {
			int width = Math.abs(this.rectangle.getStart().getX()-e.getX());
			int height = Math.abs(this.rectangle.getStart().getY()-e.getY());
			this.rectangle.setHeight(height);
			this.rectangle.setWidth(width);
			this.model.addRectangle(this.rectangle);
			
		} else if(this.mode == "Square") {
			int length = Math.abs(this.square.getStart().getX()-e.getX());
			this.square.setLength(length);
			this.model.addSquare(this.square);
			
		} else if(this.mode == "Line") {
			Point end = new Point(e.getX(), e.getY());
			this.line.setEnd(end);
			this.model.addLine(line);

		} else if(this.mode == "Eraser") {
			Point start = new Point(e.getX(),e.getY());
			this.earser.setStart(start);
			this.earser = new Earser(start, this.getBackground(), 20);
			this.model.addEarser(this.earser);
	
		} else if(this.mode == "Pencil") {
			Point start = new Point(e.getX(),e.getY());
			this.pencil.setStart(start);
			this.pencil = new Pencil(start, this.color, start);
			this.model.addPencil(this.pencil);
		}
	}

	// MouseListener below
	@Override
	public void mouseClicked(MouseEvent e) {
		if(this.mode=="Squiggle"){
			
		} else if(this.mode=="Circle"){
			
		} else if(this.mode == "Rectangle") {
			
		} else if(this.mode == "Square") {
			
		} 
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(this.mode=="Squiggle"){
			
		} else if(this.mode=="Circle"){
			// Problematic notion of radius and centre!!
			Point centre = new Point(e.getX(), e.getY());
			this.circle=new Circle(centre, 0, this.color,this.style);
		} else if(this.mode=="Rectangle") {
			Point start1 = new Point(e.getX(),e.getY());
			this.rectangle = new Rectangle(start1,this.color,0, 0,this.style);
		} else if(this.mode == "Square") {
			Point start2 = new Point(e.getX(),e.getY());
			this.square = new Square(start2,this.color,0,this.style);
		} else if(this.mode == "Line") {
			Point start3 = new Point(e.getX(),e.getY());
			this.line = new Line(start3,this.color,start3);
		} else if(this.mode == "Eraser") {
			Point centre = new Point(e.getX(), e.getY());
			this.earser=new Earser(centre, this.getBackground(), 20);
			this.repaint();
		}else if(this.mode=="Pencil"){
			Point start = new Point(e.getX(), e.getY());
			this.pencil=new Pencil(start, this.color, start);

		}


	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(this.mode=="Squiggle"){
			
		} else if(this.mode=="Circle"){
			if(this.circle!=null){
				// Problematic notion of radius and centre!!
				int radius = Math.abs(this.circle.getStart().getX()-e.getX());
				this.circle.setRadius(radius);
				this.model.addCircle(this.circle);
				this.circle=null;
			}
		} else if(this.mode == "Rectangle") {
			if(this.rectangle != null) {
				int width = Math.abs(this.rectangle.getStart().getX()-e.getX());
				int height = Math.abs(this.rectangle.getStart().getY()-e.getY());
				this.rectangle.setHeight(height);
				this.rectangle.setWidth(width);
				this.model.addRectangle(this.rectangle);
				this.rectangle = null;
			}
		} else if(this.mode == "Square") {
			if(this.square != null) {
				int length = Math.abs(this.square.getStart().getX()-e.getX());
				this.square.setLength(length);
				this.model.addSquare(this.square);
				this.square = null;
			}
		} else if(this.mode == "Line") {
			if(this.line != null) {
				Point end = new Point(e.getX(), e.getY());
				this.line.setEnd(end);
				this.model.addLine(line);
				this.line = null;
			}
		} else if(this.mode == "Eraser") {
			this.earser.setLength(0);
			this.model.addEarser(this.earser);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(this.mode=="Squiggle"){
			
		} else if(this.mode=="Circle"){
			
		} else if(this.mode == "Rectangle"){
			
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(this.mode=="Squiggle"){
			
		} else if(this.mode=="Circle"){
			
		} else if(this.mode == "Rectangle") {
			
		}
	}
}
