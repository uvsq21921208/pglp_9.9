package app;
import Command.Command;
import Command.CreationCommand;
import Command.DrawingTUI;
import dessin.Forme;
import io.Display;
import io.Input;
import io.UserDisplay;
import io.UserInput;

/**
*
* @author Mouttie
* Singleton application.
*/
public enum DrawingAPP {
/**
*Application.
*/
DrawingAPP;
/**
*
* @param args arguments.
*/
public void run(final String[] args) {
    UserDisplay display = new Display();
    UserInput input = new Input();
    DrawingTUI drawing = new DrawingTUI();
	String message = "Welcome to the drawing application, type a command";
	display.showMessage(message);
	String userString = "";
	int count = 0;
	while (true) {
		userString = input.readValue();
		Command command = drawing.nextCommand(userString);
		if (command != null) {
			command.execute();
			if (command instanceof CreationCommand) {
			message = drawing.getFormes().get(count).getType()+" "+drawing.getFormes().get(count).getNom()
					+  " has been created.";
			display.showMessage(message);
			drawing.getFormes().get(count).show();
			
			count += 1;
			} else {
				drawing.deleteFormesMoved();
				if (drawing.getMovedFormes().size() > 1) {
					message = "These formes has been moved : ";
					for (Forme f : drawing.getMovedFormes()) {
						message += f.getNom() + " ";
					}
					display.showMessage(message);
					
					message = "New coordinates : ";
					display.showMessage(message);
					for (Forme f : drawing.getMovedFormes()) {
						f.show();
					}
					
				}else {
					
				}
				
			}
		} else {
			message = "Please enter a valid command";
			display.showMessage(message);
		}
		
		
	}
}
/**
*
* @param args arguments.
*/
 



public static void main(final String[] args) {
	   DrawingAPP.run(args);
}

}
