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
				ArrayList<Point> points = this.model.getPoints();
				for(int i=0;i<points.size()-1; i++){
					Point p1=points.get(i);
					Point p2=points.get(i+1);
					this.gc.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
				}
				
				break;
			case "CIRCLE":
				Circle circle = this.model.getCircle();

				this.gc.setColor(circle.getColor());
				if (circle.getStyle() == "fill") this.gc.fillOval(circle.getStart().getX(), circle.getStart().getY(), circle.getRadius(), circle.getRadius());
				if (circle.getStyle() == "outline") this.gc.drawOval(circle.getStart().getX(), circle.getStart().getY(), circle.getRadius(), circle.getRadius());
				
				break;
			case "RECT":
				Rectangle rect = this.model.getRectangle();

				this.gc.setColor(rect.getColor());
				if (rect.getStyle() == "fill") this.gc.fillRect(rect.getStart().getX(), rect.getStart().getY(), rect.getWidth(), rect.getHeight());
				if (rect.getStyle() == "outline") this.gc.drawRect(rect.getStart().getX(), rect.getStart().getY(), rect.getWidth(), rect.getHeight());
				
				break;
			case "SQUARE":
				Square square = this.model.getSquare();
				
				this.gc.setColor(square.getColor());
				if (square.getStyle() == "fill") this.gc.fillRect(square.getStart().getX(), square.getStart().getY(), square.getLength(), square.getLength());
				if (square.getStyle() == "outline") this.gc.drawRect(square.getStart().getX(), square.getStart().getY(), square.getLength(), square.getLength());

				break;
			case "LINE":
				Line line = this.model.getLine();
				
				this.gc.setColor(line.getColor());
				this.gc.drawLine(line.getStart().getX(), line.getStart().getY(), line.getEnd().getX(), line.getEnd().getY());
				
				break;
			case "ERASE":
				Point point = this.model.getEarser();
				
				Color cl = this.gc.getColor();
				this.gc.setColor(this.getBackground());
				this.gc.clearRect(point.x, point.y, 20, 20);
				this.gc.setColor(cl);
				
				break;
			case "PENCIL":
				Pencil pencil = this.model.getPencil();

				this.gc.setColor(pencil.getColor());
				this.gc.drawLine(pencil.getStart().getX(), pencil.getStart().getY(), pencil.getEnd().getX(), pencil.getEnd().getY());
				
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
