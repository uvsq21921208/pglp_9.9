package dessin;

public class Triangle extends Forme {

	private Point a;
	private Point b;
	private Point c;
	
	public Triangle(String name, String groupeid, Point a, Point b, Point c) {
		super(name, groupeid, "Triangle");
		this.a = new Point(a.getX(), a.getY());
		this.b = new Point(b.getX(), b.getY());
		this.c = new Point(c.getX(), c.getY());
		
	}

	@Override
	public void show() {
		String message = this.name + " : Triangle (a:(" + a.getX()+ ","+a.getY()+")"
										+ "(b:(" + b.getX() +","+b.getY()+")"
										+ "(c:(" + c.getX() +","+c.getY()+")";
		Display display = new ConsoleDisplay();
		display.display(message);
	}
	@Override
	public void move(int x, int y) {
		this.a.move(x + a.getX(), y + a.getY());
		this.b.move(x + b.getX(), y + b.getY());
		this.c.move(x + c.getX(), y + c.getY());
		

	}
	
	public Point getA() {
		return this.a;
	}
	public Point getB() {
		return this.b;
	}
	public Point getC() {
		return this.c;
	}

}
