package Command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import dessin.Carre;
import dessin.Cercle;
import dessin.Forme;
import dessin.FormeGroupe;
import dessin.GenericForm;
import dessin.Point;
import dessin.Rectangle;
import dessin.Triangle;
import io.Display;


public class DrawingTUI {
	private static int groupeNumber = 0;
	private List<Forme> formes;
	private List<FormeGroupe> groupes;
	private Map<String, Command> commands;
	private List<Forme> movedFormes;
	private List<Forme> deletedFormes;
	public DrawingTUI() {
		commands = new HashMap<String, Command>();
		formes = new ArrayList<Forme>();
		movedFormes = new ArrayList<Forme>();
		groupes = new ArrayList<FormeGroupe>();
		commands.put("carre", new CarreCreation());
		commands.put("cercle", new CercleCreation());
		commands.put("triangle", new TriangleCreation());
		commands.put("rectangle", new RectangleCreation());
        commands.put("move", new FormeDeplacement());
        commands.put("moveall", new FormeGroupeDeplacement());
        commands.put("showall", new ShowAllCommand());
        commands.put("deleteall", new FormeDeleteionAll());
        commands.put("show", new ShowCommand());
        commands.put("delete", new FormeDeletion());
        commands.put("quit", new Quit());
		
	}
	
	
	private CreationCommand getCreationCmd(String[] result) {
		CreationCommand command = null;
		Forme f = null;
		try {
			switch(result[1].toLowerCase()) {
				case "cercle":
					int x = Integer.parseInt(result[2]);
					int y = Integer.parseInt(result[3]);
					String name = result[0];
					String groupeid = "null";
					int rayon = Integer.parseInt(result[4]);
			
					f = new Cercle(name, rayon, new Point(x, y), groupeid);
					command = (CercleCreation) commands.get("cercle");

					break;
				case "triangle":
					groupeid = "null";
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
					groupeid = "null";
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
					groupeid = "null";
					f = new Rectangle(name, new Point(x, y), h, w, groupeid);
					command = (RectangleCreation) commands.get("rectangle");
			
			
					break;
				default:
					return null;
			}
		    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
				return null;
			}
		command.setForm(f);
		return command;
	}
	
	
	
	private FormeDeplacement getMovementCmd(String[] result) {
		try {
		GenericForm f = getFormeByName(this.formes, result[1]);
		FormeDeplacement command = (FormeDeplacement) this.commands.get("move");
		command.setForm(f);
		movedFormes.add((Forme)f);
		
		int x = Integer.parseInt(result[2]);
		int y = Integer.parseInt(result[3]);
		command.setParameters(x, y);
		return command;
		} catch(ArrayIndexOutOfBoundsException | NumberFormatException e) {
			return null;
		}
	
	}
	
	private FormeGroupeDeplacement getGroupeMovementCmd(String[] result) {
		FormeGroupe groupeForme = new FormeGroupe("gr"+groupeNumber);
		FormeGroupeDeplacement command = (FormeGroupeDeplacement) this.commands.get("moveall");
		int i = 1;
		try {
		while (i != result.length - 2) {
			Forme forme = getFormeByName(this.formes, result[i]);
			movedFormes.add(forme);
			forme.setGroupeid("gr"+groupeNumber);
			groupeForme.addForme(forme);
			i += 1;
		}
	
			int x = Integer.parseInt(result[i]);
			int y = Integer.parseInt(result[i+1]);
			command.setParameters(x, y);
			command.setForm(groupeForme);
			groupeNumber += 1;
			return command;
		} catch (ArrayIndexOutOfBoundsException | NumberFormatException e){
			return null;
		}

		
		
		
		
	}

	private Forme getFormeByName(final List<Forme> list, final String name){
		try {
			return list.stream().filter(o -> o.getNom().equals(name)).findFirst().get();
		} catch (NoSuchElementException e) {
			String message = "Forme "+ name +" doesn't exist";
			Display display = new Display();
			display.showMessage(message);
			return null;
		}
		
	}
	
	private ShowCommand getShowCommand(String[] result) {
		FormeGroupe g = new FormeGroupe("null");
		if (result.length == 1) {
			return null;
		}

		for(int i = 1; i < result.length; i++) {
			
			Forme element = getFormeByName(this.formes, result[i]);
			if (element == null) {
				return null;
			}else {
				g.addForme(element);
			}
		}
	
	
		
		ShowCommand command = (ShowCommand) this.commands.get("show");
		command.setForme(g);
		return command;
	}
	private FormeDeletion getDeleteCommand(String[] result) {
		FormeDeletion command = (FormeDeletion) this.commands.get("delete");

		List<Forme> listOfFormes = new ArrayList<Forme>();
		for(int i = 1; i < result.length;i++) {
			listOfFormes.add(getFormeByName(this.formes, result[i]));
			deletedFormes = new ArrayList<Forme>(listOfFormes);
		}
		command.setForme(listOfFormes);
		return command;
	}
	public Command nextCommand(String userText) {
		userText = userText.replaceAll("[=)(,]", " ");
		String[] result = userText.split("\\s+");
		Command command = null;

		
		switch(result[0].toLowerCase()) {
		case "move":
			command = getMovementCmd(result);
	
			break;
		case "moveall":
			command = getGroupeMovementCmd(result);
			
			break;
		case "save":
			System.out.println("save");
			break;
		case "delete":
			command = getDeleteCommand(result);
			break;
		case "deleteall":
			command = this.commands.get("deleteall");
			break;
		case "show":
			command = getShowCommand(result);
			break;
		case "showall":
			if (result.length == 1) {
				return null;
			}
			command = this.commands.get("showall");
			break;
		case "quit":
			command = this.commands.get("quit");
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
	private static class Quit implements Command {
	    /**
	    * execute method implementation.
	    */
	    public void execute() {
	      return;
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
	private class FormeDeletion implements Command{
		
		private List<Forme> listOfFormes;
		
		public void setForme(List<Forme> formes) {
			listOfFormes = formes;
		}
		
		public void execute() {
			for(Forme f : listOfFormes) {
			for(Iterator<Forme> iterator = DrawingTUI.this.formes.iterator(); iterator.hasNext(); ) {
			    if(iterator.next().getNom().equals(f.getNom()));
			        iterator.remove();
			}
			}
		}
		
		
	}
	private class FormeDeleteionAll implements Command{
		
		@Override
		public void execute() {
			DrawingTUI.this.formes.clear();
		}
	}
	
	private class ShowCommand implements Command{
		private FormeGroupe g;
		
		public void setForme(FormeGroupe g) {
			this.g = g;
		}
		@Override
		public void execute() {
			this.g.show();
			
		}
		
	}
	private class ShowAllCommand implements Command{
		@Override
		public void execute() {
			for (Forme f : DrawingTUI.this.formes) {
				f.show();
			}
			
		}
		
	}
	public List<Forme> getFormes(){
		return this.formes;
	}


	public List<Forme> getMovedFormes() {
		// TODO Auto-generated method stub
		return this.movedFormes;
	}


	/**
	 * @return the deletedFormes
	 */
	public List<Forme> getDeletedFormes() {
		return deletedFormes;
	}


	public void deleteFormesDeleted() {
		this.deletedFormes.clear();
		
	}
	public void deleteFormesMoved() {
		this.movedFormes.clear();
	}


	
	
}
