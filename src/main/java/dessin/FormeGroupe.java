package dessin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FormeGroupe extends GenericForm implements Iterable<Forme> {
	private List<Forme> formes;
	private String nom;
	public FormeGroupe(String nom) {
		
		formes = new ArrayList<Forme>();
		this.nom = nom;
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
	
	public String getNom() {
		return this.nom;
	}
	
	
}
