package ca.utoronto.utm.paint;

import java.util.ArrayList;
import java.util.Observable;


public class PaintModel extends Observable {
	private ArrayList<Point> points=new ArrayList<Point>();
	private ArrayList<Circle> circles=new ArrayList<Circle>();
	private ArrayList<Rectangle> rectangle = new ArrayList<Rectangle>();
	private ArrayList<Square> square = new ArrayList<Square>();
	private ArrayList<Line> line = new ArrayList<Line>();
	private ArrayList<Earser> earser = new ArrayList<Earser>();
	private ArrayList<Pencil> pencil = new ArrayList<Pencil>();
	
	public void addPoint(Point p){
		this.points.add(p);
		this.setChanged();
		this.notifyObservers();
	}
	public ArrayList<Point> getPoints(){
		return points;
	}
	
	public void addCircle(Circle c){
		this.circles.add(c);
		
		this.setChanged();
		this.notifyObservers();
	}
	public ArrayList<Circle> getCircles(){
		return circles;
	}
	
	public void addRectangle(Rectangle r) {
		this.rectangle.add(r);
		this.setChanged();
		this.notifyObservers();
	}
	public ArrayList<Rectangle> getRectangle(){
		return rectangle;
	}
	
	public void addSquare(Square s) {
		this.square.add(s);
		this.setChanged();
		this.notifyObservers();
	}
	public ArrayList<Square> getSquare(){
		return square;
	}
	
 void addLine(Line line) {
		this.line.add(line);
		this.setChanged();
		this.notifyObservers();
	}
	
	public ArrayList<Line> getLine(){
		return line;
	}
	
	public void addEarser(Earser earser) {
		this.earser.add(earser);
		this.setChanged();
		this.notifyObservers();
	}
	public ArrayList<Earser> getEarser(){
		return earser;
	}
	
	public void addPencil(Pencil pencil) {
		this.pencil.add(pencil);
		this.setChanged();
		this.notifyObservers();
	}
	public ArrayList<Pencil> getPencil(){
		return pencil;
	}
	
	
}
