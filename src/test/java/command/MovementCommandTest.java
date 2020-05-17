package command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import dessin.Carre;
import dessin.Cercle;
import dessin.Rectangle;
import dessin.Triangle;

public class MovementCommandTest {

	@Test
	public void CarreMoveCommand() {
		
		DrawingTui drawing = new DrawingTui();
		Command command = drawing.nextCommand("car carre 1 1 1");
		command.execute();
		assertEquals(drawing.getFormes().get(0).getNom(), "car");
		Carre car = (Carre) drawing.getFormes().get(0);
		command = drawing.nextCommand("move car 5 5");
		
		assertEquals(car.getPoint().getX(), 1);
		assertEquals(car.getPoint().getY(), 1);
		
		command.execute();
		
		assertEquals(car.getPoint().getX(), 5);
		assertEquals(car.getPoint().getY(), 5);
		
		 
	}
	
	@Test
	public void CercleMoveCommand() {
		
		DrawingTui drawing = new DrawingTui();
		Command command = drawing.nextCommand("cer cercle 1 1 1");
		command.execute();
		assertEquals(drawing.getFormes().get(0).getNom(), "cer");
		Cercle cer = (Cercle) drawing.getFormes().get(0);
		command = drawing.nextCommand("move cer 5 5");
		
		assertEquals(cer.getCentre().getX(), 1);
		assertEquals(cer.getCentre().getY(), 1);
		
		command.execute();
		
		assertEquals(cer.getCentre().getX(), 5);
		assertEquals(cer.getCentre().getY(), 5);
		
		 
	}
	
	@Test
	public void TriangleMoveCommand() {
		
		DrawingTui drawing = new DrawingTui();
		Command command = drawing.nextCommand("tri triangle 0 0 2 2 3 3");
		command.execute();
		Triangle tri = (Triangle) drawing.getFormes().get(0);
		command = drawing.nextCommand("move tri 5 5");
		
		assertEquals(tri.getA().getX(), 0);
		assertEquals(tri.getA().getY(), 0);
		
		command.execute();
		
		assertEquals(tri.getA().getX(), 5);
		assertEquals(tri.getA().getY(), 5);
		
	}
	
	@Test
	public void RectangleMoveCommand() {
		DrawingTui drawing = new DrawingTui();
		Command command = drawing.nextCommand("rec rectangle 1 1 4 2");
		command.execute();
		Rectangle rec = (Rectangle) drawing.getFormes().get(0);
		command = drawing.nextCommand("move rec 5 5");
		
		assertEquals(rec.getPoint().getX(), 1);
		assertEquals(rec.getPoint().getY(), 1);
		
		command.execute();
		
		assertEquals(rec.getPoint().getX(), 5);
		assertEquals(rec.getPoint().getY(), 5);
			 
	}
	
	@Test
	public void groupeMovement() {
		DrawingTui drawing = new DrawingTui();
		Command command = drawing.nextCommand("rec rectangle 1 1 4 2");
		command.execute();
		
		command = drawing.nextCommand("cer cercle 1 4 2");
		command.execute();
		
		command = drawing.nextCommand("tri triangle 1 1 4 2 6 6");
		command.execute();
		
		command = drawing.nextCommand("moveall rec tri cer 6 6");
		
		
		Rectangle rec = (Rectangle) drawing.getFormes().get(0);
		Cercle cer = (Cercle) drawing.getFormes().get(1);
		Triangle tri = (Triangle) drawing.getFormes().get(2);
		
		
		assertEquals(rec.getPoint().getX(), 1);
		assertEquals(rec.getPoint().getY(), 1);
		assertEquals(tri.getA().getX(), 1);
		assertEquals(tri.getA().getY(), 1);
		assertEquals(cer.getCentre().getX(), 1);
		assertEquals(cer.getCentre().getY(), 4);

		
	
		
		command.execute();
		
		assertEquals(rec.getPoint().getX(), 6);
		assertEquals(rec.getPoint().getY(), 6);
		assertEquals(tri.getA().getX(), 7);
		assertEquals(tri.getA().getY(), 7);
		
		assertEquals(cer.getCentre().getX(), 6);
		assertEquals(cer.getCentre().getY(), 6);
		
		
	}

}
