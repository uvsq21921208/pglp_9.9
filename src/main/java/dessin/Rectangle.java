package dessin;

public class Rectangle extends Forme {
	private Point point;
	private int height;
	private int width;
	public Rectangle(String name, Point point, int h, int w, String groupeid) {
		super(name, groupeid);
		this.point = new Point(point.getX(), point.getY());
		height = h;
		width = w;
	}

	@Override
	public void show() {
		String message = this.name + " : Rectangle ((h:" + height +",w:"+ width + "))"
						 + ("(x:"+point.getX() + "," + "y:"+point.getY());
		Display display = new ConsoleDisplay();
		display.display(message);		
	}

	@Override
	public void move(int x, int y) {
		
		this.point.move(x, y);
		
	}
	public int getHeight() {
		return this.height;
	}
	public int getWidth() {
		return this.width;
	}
	public Point getPoint() {
		return this.point;
	}

}
