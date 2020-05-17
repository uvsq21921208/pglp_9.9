package dessin;

public abstract class Forme extends GenericForm {
 

  protected String groupeid;
  protected String type;
  /**
   * public constructor.
   * @param name name.
   * @param groupeid groupeid.
   * @param type type.
   */
  
  public Forme(String name, String groupeid, String type) {
    this.type = type;
    this.name = name;
    this.groupeid = groupeid;
 
  }

  public abstract void show();

  public abstract void move(int x, int y);

  public String getNom() {
    return this.name;
  
  }

  public String getGroupeid() {
    return this.groupeid;
  }

  public String getType() {
    return this.type;
  }

  public void setGroupeid(String string) {
    this.groupeid = string;
  
  }

}
