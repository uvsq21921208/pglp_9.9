package dessin;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CarreTest {
	
	
	
	@Test
	public void moveTest() {
		Carre c = new Carre("c", 2, new Point(1,2),"groupe1");
		assertEquals(c.getPoint().getX(), 1);
		assertEquals(c.getPoint().getY(), 2);
		
		c.move(5, 4);
		
		assertEquals(c.getPoint().getX(), 5);
		assertEquals(c.getPoint().getY(), 4);
	}
}

