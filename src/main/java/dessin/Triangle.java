package dessin;

public class Triangle extends Forme {

  private Point ap;
  private Point bp;
  private Point cp;

  /**
   * public constructor.
   * @param name name.
   * @param groupeid groupeid.
   * @param a point a.
   * @param b point b.
   * @param c point c.
   */
  public Triangle(String name, String groupeid, Point a, Point b, Point c) {
    super(name, groupeid, "Triangle");
    this.ap = new Point(a.getX(), a.getY());
    this.bp = new Point(b.getX(), b.getY());
    this.cp = new Point(c.getX(), c.getY());
  
  }

  @Override
  public void show() {
    String message = this.name + " : Triangle (a:(" + ap.getX() + "," + ap.getY() + ")"
          + "(b:(" + bp.getX() + "," + bp.getY() + ")"
          + "(c:(" + cp.getX() + "," + cp.getY() + ")";
    Display display = new ConsoleDisplay();
    display.display(message);
  }

  @Override
  public void move(int x, int y) {
    this.ap.move(x + ap.getX(), y + ap.getY());
    this.bp.move(x + bp.getX(), y + bp.getY());
    this.cp.move(x + cp.getX(), y + cp.getY());
  

  }
 
  public Point getA() {
    return this.ap;
  }

  public Point getB() {
    return this.bp;
  }

  public Point getC() {
    return this.cp;
  }

}
