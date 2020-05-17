package command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class CreationCommandTest {

	
	
	@Test
	public void carreCreationCommandTest() {
		
		DrawingTui drawing = new DrawingTui();
		Command command = drawing.nextCommand("car carre 1 1 1");
		command.execute();
		assertEquals(drawing.getFormes().get(0).getNom(), "car");
		
		 
	}
	
	@Test
	public void cercleCreationCommandTest() {
		
		DrawingTui drawing = new DrawingTui();
		Command command = drawing.nextCommand("cer cercle 1 1 1");
		command.execute();
		assertEquals(drawing.getFormes().get(0).getNom(), "cer");
		
		 
	}
	
	@Test
	public void triangleCreationCommandTest() {
		
		DrawingTui drawing = new DrawingTui();
		Command command = drawing.nextCommand("tri triangle 1 1 2 2 3 3");
		command.execute();
		assertEquals(drawing.getFormes().get(0).getNom(), "tri");
		
		 
	}
	
	@Test
	public void rectangleCreationCommandTest() {
		DrawingTui drawing = new DrawingTui();
		Command command = drawing.nextCommand("rec cercle 1 1 0 0");
		command.execute();
		assertEquals(drawing.getFormes().get(0).getNom(), "rec");	
		 
	}
	
	@Test 
	public void wrongCommandTest() {
		DrawingTui drawing = new DrawingTui();
		Command command = drawing.nextCommand("sdazdzaezae");
		assertNull(command);
	}
}
