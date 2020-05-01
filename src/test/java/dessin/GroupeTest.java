package dessin;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;

public class GroupeTest {
	@Test
	public void moveTest() {
		
		FormeGroupe g = new FormeGroupe("groupe1");
		
		
		Carre c = new Carre("c", 2, new Point(0,0),"groupe1");
		Triangle t = new Triangle("t", "groupe1", new Point(0,0),new Point(1,2), new Point(3,4));
		Rectangle r = new Rectangle("r", new Point(1,2), 2,3 ,"groupe1");
		Cercle cer = new Cercle("c", 2, new Point(1,2),"groupe1");
		
		g.addForme(c);
		g.addForme(t);
		g.addForme(r);
		g.addForme(cer);
		
		g.move(1, 2);
		Iterator<Forme> i = g.iterator();
		
		while(i.hasNext()) {
			Forme f = (Forme) i.next();
			if (f instanceof Triangle) {
				Point A = ((Triangle) f).getA();
				Point B = ((Triangle) f).getB();
				Point C = ((Triangle) f).getC();
				assertEquals(A.getX(), 1);
				assertEquals(A.getY(), 2);
				
				assertEquals(B.getX(), 2);
				assertEquals(B.getY(), 4);
				
				assertEquals(C.getX(), 4);
				assertEquals(C.getY(), 6);
			}
			
			if (f instanceof Cercle) {
				Point p = ((Cercle) f).getCentre();
				assertEquals(p.getX(), 1);
				assertEquals(p.getY(), 2);
			}
	
			if (f instanceof Rectangle) {
				Point p = ((Rectangle) f).getPoint();
				assertEquals(p.getX(), 1);
				assertEquals(p.getY(), 2);
			}
			if (f instanceof Carre) {
				Point p = ((Carre) f).getPoint();
				assertEquals(p.getX(), 1);
				assertEquals(p.getY(), 2);
			}
	
		}
		
	}
}
