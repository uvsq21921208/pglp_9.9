package dessin;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;

public class GroupeTest {
	@Test
	public void moveTest() {
		
		FormeGroupe g = new FormeGroupe("groupe1");
		
		
		Carre c = new Carre("c", 2, new Point(0,0),"groupe1");
		assertEquals(c.getPoint().getX(), 0);
		assertEquals(c.getPoint().getY(), 0);
		
		g.addForme(c);
		
		g.move(1, 2);
		Iterator<Forme> i = g.iterator();
		
		while(i.hasNext()) {
			Forme f = (Forme) i.next();
			f.show();
			
		}
		
	}
}
