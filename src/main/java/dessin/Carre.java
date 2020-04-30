package dessin;

public class Carre extends Forme {
	private Point point;
	int cote;
	public Carre(String name, int cote, Point point) {
		super(name);
		this.cote = cote;
		this.point = new Point(point.getX(), point.getY());
	}

	@Override
	public String show() {
		return this.name + " : Carre ((cote:" + cote +") "
				 + ("(x:"+point.getX() + "," + "y:"+point.getY());
	}

	@Override
	public void move(int x, int y) {
		// TODO Auto-generated method stub

	}

}