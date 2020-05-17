package dessin;

public class Carre extends Forme {
  private Point point;
  int cote;
  /**
   * Public constructor.
   * @param name name.
   * @param cote cote.
   * @param point point.
   * @param groupeid groupeid.
   */

  public Carre(String name, int cote, Point point, String groupeid) {
    super(name, groupeid, "Carre");
    this.cote = cote;
    this.point = new Point(point.getX(), point.getY());
  }

  @Override
  public void show() {
    String message = this.name + " : Carre (cote:" + cote + ", "
        + ("(x:" + point.getX() + "," + "y:" + point.getY()) + "))";
    Display display = new ConsoleDisplay();
    display.display(message);
  }

  @Override
  public void move(int x, int y) {
    this.point.move(x, y);
  }

  public int getCote() {
    return this.cote;
  }

  public Point getPoint() {
    return this.point;
  }

}
