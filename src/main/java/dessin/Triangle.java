package dessin;

public class Triangle extends Forme {

	private Point a;
	private Point b;
	private Point c;
	
	public Triangle(String name) {
		super(name);
		this.a = new Point(a.getX(), a.getY());
		this.b = new Point(b.getX(), b.getY());
		this.c = new Point(c.getX(), c.getY());
		
	}

	@Override
	public String show() {
		return this.name + " : Triangle (a:(" + a.getX()+ ","+a.getY()+")"
										+ "(b:(" + b.getX() +","+b.getY()+")"
										+ "(c:(" + c.getX() +","+c.getY()+")";
				
	}
	@Override
	public void move(int x, int y) {
		this.a.move(x, y);
		this.b.move(x, y);
		this.c.move(x, y);
		

	}

}
