package Command;


import dessin.Forme;


public class CreationCommand implements Command {
	protected Forme f;
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	public void setForm(Forme f){
		this.f = f;
	}

}
