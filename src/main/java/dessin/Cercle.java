package dessin;

public class Cercle extends Forme {
	private int rayon;
	private Point point;
	public Cercle(String name, int rayon, Point point) {
		super(name);
		this.rayon = rayon; 
		this.point = new Point(point.getX(), point.getY());
	}

	@Override
	public void show() {
		String message =  this.name + " : Cercle ((Rayon:" + rayon +") "
				 + ("(x:"+point.getX() + "," + "y:"+point.getY());
		Display display = new ConsoleDisplay();
		display.display(message);
	}

	@Override
	public void move(int x, int y) {
		// TODO Auto-generated method stub

	}

}
