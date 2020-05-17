package command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DeletionCommandTest {


	@Test
	public void deleteCommandTest() {
		
		DrawingTui drawing = new DrawingTui();
		Command command = drawing.nextCommand("car carre 1 1 1");
		command.execute();
		assertEquals(drawing.getFormes().get(0).getNom(), "car");
		command = drawing.nextCommand("delete car");
		command.execute();
		assertTrue(drawing.getFormes().isEmpty());
		 
	}
	@Test
	public void deleteAllCommandTest() {
		DrawingTui drawing = new DrawingTui();
		Command command = drawing.nextCommand("car carre 1 1 1");
		command.execute();
		command = drawing.nextCommand("cer cercle 1 1 1");
		command.execute();
		command = drawing.nextCommand("deleteall car cercle");
		command.execute();
		assertTrue(drawing.getFormes().isEmpty());
	}
	
}
