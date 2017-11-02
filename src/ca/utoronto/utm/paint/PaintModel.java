package ca.utoronto.utm.paint;

import java.util.ArrayList;
import java.util.Observable;


public class PaintModel extends Observable {
	private ArrayList<Point> points=new ArrayList<Point>();
	private Circle circle = null;
	private Rectangle rectangle = null;
	private Square square = null;
	private Line line = null;
	private Point earsePoint = null;
	private Pencil pencil = null;
	private String event;
	
	public void addPoint(Point p){
		this.points.add(p);
		this.event = "POINT";
		
		this.setChanged();
		this.notifyObservers();
	}
	
	public ArrayList<Point> getPoints(){
		return points;
	}
	
	public void addCircle(Circle c){
		this.circle = c;
		this.event = "CIRCLE";
		
		this.setChanged();
		this.notifyObservers();
	}
	
	public Circle getCircle(){
		return this.circle;
	}
	
	public void addRectangle(Rectangle r) {
		this.rectangle = r;
		this.event = "RECT";
		
		this.setChanged();
		this.notifyObservers();
	}
	
	public Rectangle getRectangle(){
		return this.rectangle;
	}
	
	public void addSquare(Square s) {
		this.square = s;
		this.event = "SQUARE";
		
		this.setChanged();
		this.notifyObservers();
	}
	
	public Square getSquare(){
		return this.square;
	}
	
	void addLine(Line l) {
		this.line = l;
		this.event = "LINE";
		
		this.setChanged();
		this.notifyObservers();
	}
	
	public Line getLine(){
		return this.line;
	}
	
	public void addEarsePoint(Point point) {
		this.earsePoint = point;
		this.event = "ERASE";
		
		this.setChanged();
		this.notifyObservers();
	}
	
	public Point getEarser(){
		return this.earsePoint;
	}
	
	public void addPencil(Pencil pencil) {
		this.pencil = pencil;
		this.event = "PENCIL";
		
		this.setChanged();
		this.notifyObservers();
	}
	
	public Pencil getPencil(){
		return this.pencil;
	}
	
	public String getEvent() {
		return this.event;
	}
}
