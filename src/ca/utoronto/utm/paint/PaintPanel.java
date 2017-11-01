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
	private Pencil pencil;
	private String style; // outline style or fill in style.
	private String repaintFlag;
    private BufferedImage grid;
    private Graphics2D gc;
    private Graphics2D g2;
	
	public PaintPanel(PaintModel model, View view){
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(300,300));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		this.style = "outline";
		
		this.repaintFlag = "";
		this.mode="";
		this.model = model;
		this.model.addObserver(this);
		this.view=view;
		this.grid = null;
		this.gc = null;
		this.g2 = null;
	}

	/**
	 *  View aspect of this
	 */
	public void paintComponent(Graphics g) {
		// Use g to draw on the JPanel, lookup java.awt.Graphics in
		// the javadoc to see more of what this can do for you!!
		
        super.paintComponent(g); //paint background
        this.g2 = (Graphics2D) g; // lets use the advanced api
		// Origin is at the top left of the window 50 over, 75 down

        if (this.grid == null)
        {
            int w = this.getWidth();
            int h = this.getHeight();
            this.grid = (BufferedImage) (this.createImage(w, h));
            this.gc = this.grid.createGraphics();
        }
        
		this.g2.drawString ("i="+i, 50, 75);
		i=i+1;

        this.g2.drawImage(this.grid, null, 0, 0);
	}
	
	public void draw() {
		switch (this.repaintFlag) {
			case "POINT":
				// Draw Lines
				ArrayList<Point> points = this.model.getPoints();
				for(int i=0;i<points.size()-1; i++){
					Point p1=points.get(i);
					Point p2=points.get(i+1);
					this.gc.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
				}
				
				break;
			case "CIRCLE":
				// Draw Circles
				ArrayList<Circle> circles = this.model.getCircles();

				for(Circle c: circles){
					int x = c.getStart().getX();
					int y = c.getStart().getY();
					int radius = c.getRadius();
					this.gc.setColor(c.getColor());
					if (c.getStyle() == "fill") {this.gc.fillOval(x, y, radius, radius);}
					if (c.getStyle() == "outline") {this.gc.drawOval(x, y, radius, radius);}	
				}
			
				break;
			case "RECT":
				//Draw Rectangle
				ArrayList<Rectangle> rectangle = this.model.getRectangle();
				for(Rectangle r: rectangle) {
					int x = r.getStart().getX();
					int y = r.getStart().getY();
					int height = r.getHeight();
					int width = r.getWidth();
					this.gc.setColor(r.getColor());
					if (r.getStyle() == "fill") {this.gc.fillRect(x, y, width, height);}
					if (r.getStyle() == "outline") {this.gc.drawRect(x, y, width, height);}	
				}
				
				break;
			case "SQUARE":
				//Draw Square
				ArrayList<Square> square = this.model.getSquare();
				for(Square s:square) {
					int x = s.getStart().getX();
					int y = s.getStart().getY();
					int length = s.getLength();
					this.gc.setColor(s.getColor());
					if (s.getStyle() == "fill") {this.gc.fillRect(x, y, length, length);}
					if (s.getStyle() == "outline") {this.gc.drawRect(x, y, length, length);}	
				}
				
				break;
			case "LINE":
				//Draw StraightLine
				ArrayList<Line> line = this.model.getLine();
				for(Line l: line) {
					int x = l.getStart().getX();
					int y = l.getStart().getY();
					int x1 = l.getEnd().getX();
					int y1 = l.getEnd().getY();
					this.gc.setColor(l.getColor());
					this.gc.drawLine(x, y, x1, y1);
				}
				
				break;
			case "ERASE":
				ArrayList<Point> erasePoint = this.model.getEarser();
				Color cl = this.gc.getColor();
				this.gc.setColor(this.getBackground());
				for (Point p : erasePoint) {
					this.gc.clearRect(p.x, p.y, 20, 20);
				}
				this.gc.setColor(cl);
				
				break;
			case "PENCIL":
				//Pencil
				ArrayList<Pencil> pencil = this.model.getPencil();
				for(Pencil p: pencil){
					int x = p.getStart().getX();
					int y = p.getStart().getY();
					int x1 = p.getEnd().getX();
					int y1 = p.getEnd().getY();
					this.gc.setColor(p.getColor());
					this.gc.drawLine(x, y, x1, y1);
				}
				
				break;
		}
		
		this.repaint();
	}
	
	public void setdrawColor(Color string) {
		this.color = string;
	}
	public void setstyle(String sty) {
		if (sty == "outline") {this.style = "outline";}
		if (sty == "fill") {this.style = "fill";}
	}
	
	public void update(Observable o, Object arg) {
		// Not exactly how MVC works, but similar.
		PaintModel changedModel = (PaintModel) o;
		this.repaintFlag = changedModel.getEvent();
		this.draw(); // Schedule a call to paintComponent
	}
	
	/**
	 *  Controller aspect of this
	 */
	public void setMode(String mode){
		this.mode = mode;
	}
		// MouseMotionListener below


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
			this.model.addEarsePoint(new Point(e.getX(),e.getY()));	
		} else if(this.mode == "Pencil") {
			Point start = new Point(e.getX(),e.getY());
			this.pencil.setStart(start);
			this.pencil = new Pencil(start, this.color, start);
			this.model.addPencil(this.pencil);
		}
	}
	
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
			this.model.addEarsePoint(new Point(e.getX(),e.getY()));
		}else if(this.mode=="Pencil"){
			Point start = new Point(e.getX(), e.getY());
			this.pencil=new Pencil(start, this.color, start);
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
