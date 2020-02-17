package mountain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class Mountain extends Fractal{
	private Point a, b, c;
	private double dev;
	private HashMap<Side, Point> m;

	
	public Mountain(double dev, Point a, Point b, Point c) {
		super();
		this.dev = dev;
		this.a = a;
		this.b = b;
		this.c = c;
		m = new HashMap<Side, Point>();
		}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Mountain";
	}

	@Override
	public void draw(TurtleGraphics turtle) {
		
		newTriangle(turtle, order, a, b, c);
	}
	
	public void newTriangle(TurtleGraphics turtle, int order, Point a, Point b, Point c) {
		 if(order == 0) {
			 turtle.penDown();
			 turtle.moveTo(a.getX(), a.getY());
			 turtle.forwardTo(b.getX(), b.getY());
			 turtle.forwardTo(c.getX(), c.getY());
			 turtle.forwardTo(a.getX(), a.getY());

			 
		 }else {
			 
			 Point[] con = {a, b, c, a};
			 Point[] mid = new Point[3];
			 
			 
			for(int i = 0; i < mid.length; i++) {
				mid[i] = new Point(0,0);
				Side t = new Side(con[i], con[i + 1]);
				 if(m.containsKey(t)) {
					 mid[i] = m.get(t);
					 m.remove(t);
				 }
				 if(mid[i].hashCode() == 0) {
					 mid[i] = new Point((int)((con[i].getX() + con[i+1].getX())/2.0), (int)((con[i].getY() + con[i+1].getY())/2.0  + RandomUtilities.randFunc(dev/(2*(this.order + 1 - order)))));
					 m.put(new Side(con[i], con[i + 1]), mid[i]);
				 }
			}
			 
			
			 newTriangle(turtle, order - 1, a, mid[0], mid[2]);
			 newTriangle(turtle, order - 1, mid[0], b, mid[1]);
			 newTriangle(turtle, order - 1, mid[2], mid[1], c);
			 newTriangle(turtle, order - 1, mid[0], mid[1], mid[2]);

		}
		
	}
	
	class Side{
		public Point p1, p2;
		
		public Side(Point p1, Point p2){
			this.p1 = p1;
			this.p2 = p2;
			
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Side) {
				Side p = (Side) obj;
				return p1.equals(p.p2) || p2.equals(p.p1);
			} else {
				return false;
			}
		}
		
		public int hashCode() {
			return p1.hashCode() + p2.hashCode();
		}
	}

}
