package dessin;

public abstract class Forme extends GenericForm{
	

	protected String groupeid;
	public Forme(String name, String groupeid) {
		this.name = name;
		this.groupeid = groupeid;
 
	}
	public abstract void show();
	

	public abstract void move(int x, int y);

	public String getNom() {
		// TODO Auto-generated method stub
		return this.name;
	}
	public String getGroupeid() {
		return this.groupeid;
	}

}
