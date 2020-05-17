package dessin;

public class Point {
  private int px;
  private int py;
  /**
   * Public constructor.
   * @param x x coordinates
   * @param y y cooridnates
   */
  
  public Point(int x, int y) {
    this.px = x;
    this.py = y;
  }
  /**
   * Move the pount.
   * @param x x coordinates
   * @param y y cooridnates
   */
  
  public void move(int x, int y) {
    this.px = x;
    this.py = y;
  }

  public int getX() {
    return this.px;
  }

  public int getY() {
    return this.py;
  }
}
