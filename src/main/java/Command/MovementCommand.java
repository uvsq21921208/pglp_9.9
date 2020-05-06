package Command;

import dessin.GenericForm;

public abstract class MovementCommand implements Command{
	protected GenericForm f;
	protected int x;
	protected int y;
	public void setParameters(int x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
	public void setForm(GenericForm f) {
		this.f = f;
	}

}
