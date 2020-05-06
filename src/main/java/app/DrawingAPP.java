package app;
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
	String message = "Welcome to the drawing application, type a command";
	display.showMessage(message);
	while (true) {
		
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
