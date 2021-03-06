package app;

import command.Command;
import command.DrawingTui;
import dessin.Forme;
import io.Display;
import io.Input;
import io.UserDisplay;
import io.UserInput;
import java.util.NoSuchElementException;

/**
* Singleton application.
* @author Mouttie
*
*/
public enum DrawingApp {
/**
*Application.
*/
DrawingAPP;
  /**
  * run method.
  * @param args arguments.
  */

  public void run(final String[] args) {
    UserDisplay display = new Display();
    UserInput input = new Input();
    DrawingTui drawing = new DrawingTui();
    String message = "Welcome to the drawing application, type a command";
    display.showMessage(message);
    String userString = "";
    int count = 0;
    while (true) {
      userString = input.readValue();
      Command command = drawing.nextCommand(userString);
      if (command != null) {
    
        String commandString = command.getClass().getSimpleName();
        try {
          command.execute();
               
        } catch (NoSuchElementException e) { 
          System.out.println("here");
          message = "Forme doesn't exist";
          display.showMessage(message);
        }
        switch (commandString.toLowerCase()) {
          case "formedeplacement":

            message = "The following forme has been moved : ";
            Forme forme = drawing.getMovedFormes().get(0);
            message += forme.getNom() + " ";   
            display.showMessage(message);
            message = "New coordinates : ";
            display.showMessage(message);
            forme.show();
            drawing.deleteFormesMoved();
        
            break;
          case "formegroupedeplacement":
            StringBuffer buf = new StringBuffer();
            message = "The following forme has been moved : ";
            for (Forme f : drawing.getMovedFormes()) {
              buf.append(f.getNom() + " ");
            }
            message += buf.toString();
               
            display.showMessage(message);
                   
            message = "New coordinates : ";
            display.showMessage(message);
            for (Forme f : drawing.getMovedFormes()) {
              f.show();
            }
            drawing.deleteFormesMoved();
            break;
          case "showallcommand":
            break;
          case "formedeleteionall":
            message = "All formes have been deleted.";
            display.showMessage(message);   
            count = 0;
            break;
          case "showcommand":
    
            break;
          case "formedeletion":
            message = "The following formes have been deleted ";
            buf = new StringBuffer();
            for (Forme f : drawing.getDeletedFormes()) {
              buf.append(f.getNom() + " ");
            }
            message += buf.toString();
            display.showMessage(message);
            count = drawing.getFormes().size();
            drawing.deleteFormesDeleted();
            break;
          case "quit":
            message = "You are quiting";
            display.showMessage(message);
            return;
          case "savecommand":
            message = "Your drawing has been saved !";
            display.showMessage(message);
            break;
          case "loadcommand":
            message = "Your drawing have been loaded";
            display.showMessage(message);
            break;
          case "helpcommand":
            break;
          default:
            message = drawing.getFormes().get(count).getType()
              + " " + drawing.getFormes().get(count).getNom()
              +  " has been created.";
            display.showMessage(message);
            drawing.getFormes().get(count).show();
            count += 1;
            break;
            
        }
      } else {
        message = "Please enter a valid command or type help";
        display.showMessage(message);
      }
        
        
    }
  }

  /**
  * Main method.
  * @param args arguments.
  */
  public static void main(final String[] args) {
    DrawingAPP.run(args);
  }

}
