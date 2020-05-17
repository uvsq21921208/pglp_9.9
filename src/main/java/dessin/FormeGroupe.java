package dessin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FormeGroupe extends GenericForm implements Iterable<Forme> {
  private List<Forme> formes;
  private String nom;
  
  /**
   * Public constructor.
   * @param nom name.
   */
  
  public FormeGroupe(String nom) {
    formes = new ArrayList<Forme>();
    this.nom = nom;
  }

  public Iterator<Forme> iterator() {
    return formes.iterator();
  }

  /**
   * show the forme.
   */
  public void show() {
    for (Forme f : this.formes) {
      f.show();
    }
  }

  /**
   * move the forme.
   * @param x x coordinates.
   * @param y y cooridnates.
   */
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
