package dessin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FormeGroupe implements Iterable<Forme>{
	private List<Forme> formes; 
	public FormeGroupe(String name) {
		
		formes = new ArrayList<Forme>();
	}

	public Iterator<Forme> iterator() {
		return formes.iterator();
	}

	
	public void show() {
		for (Forme f : this.formes) {
			f.show();
		}
	}

	
	public void move(int x, int y) {
		for (Forme f : this.formes) {
			f.move(x, y);
		}
		
	}
	
	public void addForme(Forme f) {
		this.formes.add(f);
	}
	
}
