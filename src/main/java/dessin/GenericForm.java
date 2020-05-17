package dessin;

public abstract class GenericForm {
  protected String name;

  public abstract void show();
  
  public abstract void move(int x, int y);

  public String getNom() {
    return this.name;
  }
 

}
