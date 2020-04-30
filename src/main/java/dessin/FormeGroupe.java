package dessin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FormeGroupe extends Forme implements Iterable<Forme>{
	private List<Forme> formes; 
	public FormeGroupe(String name) {
		super(name);
		formes = new ArrayList<Forme>();
	}

	public Iterator<Forme> iterator() {
		return formes.iterator();
	}

	@Override
	public void show() {
		for (Forme f : this.formes) {
			f.show();
		}
	}

	@Override
	public void move(int x, int y) {
		for (Forme f : this.formes) {
			f.move(x, y);
		}
		
	}
	
	public void addForme(Forme f) {
		this.formes.add(f);
	}
	
}
