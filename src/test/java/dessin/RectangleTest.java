package dessin;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RectangleTest {
	@Test
	public void moveTest() {
		
		Rectangle r = new Rectangle("r", new Point(1,2), 2,3 ,"groupe1");
		assertEquals(r.getPoint().getX(), 1);
		assertEquals(r.getPoint().getY(), 2);
		
		r.move(5, 4);
		
		assertEquals(r.getPoint().getX(), 5);
		assertEquals(r.getPoint().getY(), 4);
	}
}
