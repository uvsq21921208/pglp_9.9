package dessin;

public abstract class Forme{
	
	String name;
	public Forme(String name) {
		this.name = name;
	}
	public abstract String show();
	

	public abstract void move(int x, int y);
}
