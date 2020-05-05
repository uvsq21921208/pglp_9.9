package Command;


import dessin.GenericForm;

public class CreationCommand implements Command {
	private GenericForm form;
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	public void setParamaters(GenericForm f){
		this.form = f;
	}

}
