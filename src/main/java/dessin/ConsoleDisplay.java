package dessin;

public class ConsoleDisplay implements Display {

  public ConsoleDisplay() {
  }

  public void display(String message) {
    System.out.println(message);
  }

}
