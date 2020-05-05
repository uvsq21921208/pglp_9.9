package Command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import dessin.Carre;
import dessin.Cercle;
import dessin.Forme;
import dessin.FormeGroupe;

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
	
	public Command nextCommand(String userText) {
		String[] result = userText.split("\\s+");
		
		if (!(result[0].toLowerCase().equals("move"))) {
			switch(result[1].toLowerCase()) {
			case "cercle":
			
				Command command =  (Command) commands.get("cercle");
				
				
				break;
			case "triangle":
				break;
			case "carre":
				break;
			case "rectangle":
				break;
				
			}
			
		}
		
		return null;
	}
	
	public void showDessin() {
		
	}
	
	private class CarreCreation extends CreationCommand implements Command{
	
		private Forme f;
		@Override
		public void execute() {
			// TODO Auto-generated method stub
			
			DrawingTUI.this.formes.add(new Carre(((Carre)f).getNom(), ((Carre)f).getCote(), ((Carre)f).getPoint(), ((Carre)f).getGroupeid()));
		}
		
	}
	
	private class RectangleCreation extends CreationCommand implements Command{
		private Forme f;
		@Override
		public void execute() {
			// TODO Auto-generated method stub
			DrawingTUI.this.formes.add(new Rectangle(((Rectangle)f).getNom(), ((Rectangle)f).getPoint(), ((Rectangle)f).getHeight(), ((Rectangle)f).getWidth(), ((Rectangle)f).getGroupeid()));
		}
		
	}
	
	private class CercleCreation extends CreationCommand implements Command{
		private Forme f;
		@Override
		public void execute() {
			// TODO Auto-generated method stub
			DrawingTUI.this.formes.add(new Cercle(((Cercle)f).getNom(), ((Cercle)f).getRayon(), ((Cercle)f).getCentre(), ((Cercle)f).getGroupeid()));
		}
		
	}

	private class TriangleCreation extends CreationCommand implements Command{

		private Forme f;
		@Override
		public void execute() {
			DrawingTUI.this.formes.add(new Triangle(((Triangle)f).getNom(), ((Triangle)f).getGroupeid(), ((Triangle)f).getA(),((Triangle)f).getB(), ((Triangle)f).getC()));
			
		}
		
	}
	
	private class FormeDeplacement extends MovementCommand {
		private int x;
		private int y;
		private Forme f;
		public void setParameters(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public void execute() {
			// TODO Auto-generated method stub
			f.move(x, y);
		}
		
	}
	private class FormeGroupeDeplacement  extends MovementCommand implements Command{
		private int x;
		private int y;
		private FormeGroupe f;
		public void setParameters(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public void execute() {
			f.move(x, y);
			
		}
		
	}
	

	
	
}
