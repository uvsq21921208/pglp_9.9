package dessin;

public class Cercle extends Forme {
	private int rayon;
	private Point centre;
	public Cercle(String name, int rayon, Point point, String groupeid) {
		super(name, groupeid);
		this.rayon = rayon; 
		this.centre = new Point(point.getX(), point.getY());
	
	}

	@Override
	public void show() {
		String message =  this.name + " : Cercle ((Rayon:" + rayon +") "
				 + ("(x:"+centre.getX() + "," + "y:"+centre.getY());
		Display display = new ConsoleDisplay();
		display.display(message);
	}

	@Override
	public void move(int x, int y) {
		// TODO Auto-generated method stub

	}

	public int getRayon() {
		// TODO Auto-generated method stub
		return this.rayon;
	}

	public Point getCentre() {
		// TODO Auto-generated method stub
		return this.centre;
	}
	

}
