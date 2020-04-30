package dessin;

public class Rectangle extends Forme {
	private Point point;
	private int height;
	private int width;
	public Rectangle(String name, Point point, int h, int w) {
		super(name);
		this.point = new Point(point.getX(), point.getY());
		height = h;
		width = w;
	}

	@Override
	public String show() {
		return this.name + " : Rectangle ((h:" + height +",w:"+ width + "))"
						 + ("(x:"+point.getX() + "," + "y:"+point.getY());
						
	}

	@Override
	public void move(int x, int y) {
		
		this.point.move(x, y);
		
	}

}
