package dessin;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TriangleTest {
	@Test
	public void moveTest() {
		Triangle t = new Triangle("t", "groupe1", new Point(0,0),new Point(1,2), new Point(3,4));
		assertEquals(t.getA().getX(), 0);
		assertEquals(t.getA().getY(), 0);
		
		assertEquals(t.getB().getX(), 1);
		assertEquals(t.getB().getY(), 2);
		
		assertEquals(t.getC().getX(), 3);
		assertEquals(t.getC().getY(), 4);
		
		t.move(5, 4);
		
		assertEquals(t.getA().getX(), 5);
		assertEquals(t.getA().getY(), 4);
		
		assertEquals(t.getB().getX(), 6);
		assertEquals(t.getB().getY(), 6);
		
		assertEquals(t.getC().getX(), 8);
		assertEquals(t.getC().getY(), 8);
		
	}
}
