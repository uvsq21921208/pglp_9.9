package dessin;

public class Cercle extends Forme {
  private int rayon;
  private Point centre;
  /**
   * Public constructor.
   * @param name name.
   * @param rayon rayon.
   * @param point point.
   * @param groupeid groupeid.
   */

  public Cercle(String name, int rayon, Point point, String groupeid) {
    super(name, groupeid, "Cercle");
    this.rayon = rayon; 
    this.centre = new Point(point.getX(), point.getY());
 
  }

  @Override
  public void show() {
    String message =  this.name + " : Cercle ((Rayon:" + rayon + ") "
        + ("(x:" + centre.getX() + "," + "y:" + centre.getY()) + "))";
    Display display = new ConsoleDisplay();
    display.display(message);
  }

  @Override
  public void move(int x, int y) {
    this.centre.move(x, y);

  }

  public int getRayon() {
    return this.rayon;
  }

  public Point getCentre() {
    return this.centre;
  }
 

}
