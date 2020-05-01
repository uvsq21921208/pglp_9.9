package dessin;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CercleTest {

	@Test
	public void moveTest() {
		Cercle c = new Cercle("c", 2, new Point(1,2),"groupe1");
		assertEquals(c.getCentre().getX(), 1);
		assertEquals(c.getCentre().getY(), 2);
		
		c.move(5, 4);
		
		assertEquals(c.getCentre().getX(), 5);
		assertEquals(c.getCentre().getY(), 4);
	}
}
