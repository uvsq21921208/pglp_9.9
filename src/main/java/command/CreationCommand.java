package command;

import dessin.Forme;


public class CreationCommand implements Command {
  protected Forme forme;

  @Override
    public void execute() {
        // TODO Auto-generated method stub
        
  }

  public void setForm(Forme f) {
    this.forme = f;
  }

}
