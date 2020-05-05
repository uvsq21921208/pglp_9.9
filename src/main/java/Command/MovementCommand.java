package Command;

import dessin.GenericForm;

public abstract class MovementCommand implements Command{
	private GenericForm form;
	public void setParameters(int x, int y) {
		
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
	public void setForm(GenericForm f) {
		this.form = f;
	}

}
