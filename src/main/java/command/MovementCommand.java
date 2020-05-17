package command;

import dessin.GenericForm;

public abstract class MovementCommand implements Command {
  protected GenericForm forme;
  protected int px;
  protected int py;

  public void setParameters(int x, int y) {
    this.px = x;
    this.py = y;
  }

  @Override
  public void execute() {
  // TODO Auto-generated method stub
  
  }
 
  public void setForm(GenericForm f) {
    this.forme = f;
  }

}
