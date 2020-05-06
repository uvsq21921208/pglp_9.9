package Command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import dessin.Carre;
import dessin.Cercle;
import dessin.Forme;
import dessin.FormeGroupe;
import dessin.GenericForm;
import dessin.Point;
import dessin.Rectangle;
import dessin.Triangle;

public class DrawingTUI {
	
	private List<Forme> formes;
	private List<FormeGroupe> groupes;
	private Map<String, Command> commands;
	public DrawingTUI() {
		commands = new HashMap<String, Command>();
		formes = new ArrayList<Forme>();
		groupes = new ArrayList<FormeGroupe>();
		commands.put("carre", new CarreCreation());
		commands.put("cercle", new CercleCreation());
		commands.put("triangle", new TriangleCreation());
		commands.put("rectangle", new RectangleCreation());
        commands.put("move", new FormeDeplacement());
        commands.put("moveGroupe", new FormeGroupeDeplacement());
		
	}
	
	
	private CreationCommand getCreationCmd(String[] result) {
		CreationCommand command = null;
		Forme f = null;
		
		switch(result[1].toLowerCase()) {
		case "cercle":
		int x = Integer.parseInt(result[2]);
		int y = Integer.parseInt(result[3]);
		String name = result[0];
		String groupeid = "g1";
		int rayon = Integer.parseInt(result[4]);
		
		f = new Cercle(name, rayon, new Point(x, y), groupeid);
		command = (CercleCreation) commands.get("cercle");
	
			
			break;
		case "triangle":
			groupeid = "g1";
			command =  (TriangleCreation) commands.get("triangle");
			name = result[0];
			Point A = new Point(Integer.parseInt(result[2]), Integer.parseInt(result[3]));
			Point B = new Point(Integer.parseInt(result[4]), Integer.parseInt(result[5]));
			Point C = new Point(Integer.parseInt(result[6]), Integer.parseInt(result[7]));
			f = new Triangle(name, groupeid, A, B, C);
		
			break;
		case "carre":
			x = Integer.parseInt(result[2]);
			y = Integer.parseInt(result[3]);
			name = result[0];
			groupeid = "g1";
			int cote = Integer.parseInt(result[4]);
			
			f = new Carre(name, cote, new Point(x, y), groupeid);
			command = (CarreCreation) commands.get("carre");
		
			
			break;
		case "rectangle":
			name = result[0];
			x = Integer.parseInt(result[2]);
			y = Integer.parseInt(result[3]);
			int h = Integer.parseInt(result[4]);
			int w = Integer.parseInt(result[5]);
			groupeid = "g1";
			f = new Rectangle(name, new Point(x, y), h, w, groupeid);
			command = (RectangleCreation) commands.get("rectangle");
			
			
			break;
			default:
				return null;
		
		}
		command.setForm(f);
		return command;
	}
	
	public FormeDeplacement getMovementCmd(String[] result) {
		
		GenericForm f = getFormeByName(this.formes, result[1]);
		FormeDeplacement command = (FormeDeplacement) this.commands.get("move");
		command.setForm(f);
		int x = Integer.parseInt(result[2]);
		int y = Integer.parseInt(result[3]);
		System.out.println(x);
		System.out.println(y);
		command.setParameters(x, y);
		return command;
	}
	private Forme getFormeByName(final List<Forme> list, final String name){
	    return list.stream().filter(o -> o.getNom().equals(name)).findFirst().get();
	}
	public Command nextCommand(String userText) {
		userText = userText.replaceAll("[=)(,]", " ");
		String[] result = userText.split("\\s+");
		Command command = null;

		
		switch(result[0].toLowerCase()) {
		case "move":
			command = getMovementCmd(result);
			System.out.println("move");
			break;
		case "merge":
			System.out.println("merge");
			break;
		default:
			command = getCreationCmd(result);
			break;


		}
		return command;
	}
	
	public void showDessin() {
		
	}
	
	private class CarreCreation extends CreationCommand implements Command{
	
	
		@Override
		public void execute() {
			// TODO Auto-generated method stub
			
			DrawingTUI.this.formes.add(new Carre(((Carre)f).getNom(), ((Carre)f).getCote(), ((Carre)f).getPoint(), ((Carre)f).getGroupeid()));
		}
		
	}
	
	private class RectangleCreation extends CreationCommand implements Command{
	
		@Override
		public void execute() {
			// TODO Auto-generated method stub
			DrawingTUI.this.formes.add(new Rectangle(((Rectangle)f).getNom(), ((Rectangle)f).getPoint(), ((Rectangle)f).getHeight(), ((Rectangle)f).getWidth(), ((Rectangle)f).getGroupeid()));
		}
		
	}
	
	private class CercleCreation extends CreationCommand implements Command{
		
		@Override
		public void execute() {
			// TODO Auto-generated method stub
			DrawingTUI.this.formes.add(new Cercle(((Cercle)f).getNom(), ((Cercle)f).getRayon(), ((Cercle)f).getCentre(), ((Cercle)f).getGroupeid()));
		}
		
	}

	private class TriangleCreation extends CreationCommand implements Command{

		
		@Override
		public void execute() {
			DrawingTUI.this.formes.add(new Triangle(((Triangle)f).getNom(), ((Triangle)f).getGroupeid(), ((Triangle)f).getA(),((Triangle)f).getB(), ((Triangle)f).getC()));
			
		}
		
	}
	
	private class FormeDeplacement extends MovementCommand implements Command{
	
		public void setParameters(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public void execute() {
	
			f.move(x, y);
		}
		
	}
	private class FormeGroupeDeplacement  extends MovementCommand implements Command{

	
		public void setParameters(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public void execute() {
			f.move(x, y);
			
		}
		
	}
	
	public List<Forme> getFormes(){
		return this.formes;
	}

	
	
}
