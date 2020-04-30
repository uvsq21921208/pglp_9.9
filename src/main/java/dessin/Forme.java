package dessin;

public abstract class Forme{
	
	String name;
	public Forme(String name) {
		this.name = name;
	}
	public abstract void show();
	

	public abstract void move(int x, int y);

	public String getNom() {
		// TODO Auto-generated method stub
		return this.name;
	}

}
