package command;

import dao.Dao;
import dao.DaoFactory;
import dessin.Carre;
import dessin.Cercle;
import dessin.Forme;
import dessin.FormeGroupe;
import dessin.GenericForm;
import dessin.Point;
import dessin.Rectangle;
import dessin.Triangle;
import io.Display;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


public class DrawingTui {
  private static int groupeNumber = 0;
  private List<Forme> formes;
  private Map<String, Command> commands;
  private List<Forme> movedFormes;
  private List<Forme> deletedFormes;
  private Connection connect = null;
  private Statement statement;
 
  /**
   * public constructor.
  */
  public DrawingTui() {
  
    try {
      DaoFactory daoFac = new DaoFactory();
      dao.Dao<FormeGroupe> daoG = daoFac.createGroupeDao();
      daoG.connect();
      connect = daoG.connect; 
      DatabaseMetaData dbmd = connect.getMetaData();
      ResultSet rs = dbmd.getTables(null, "APP", "GROUPE", null);
      if (!rs.next()) {
        String sql = "CREATE TABLE groupe(groupeid varchar(30) primary key not null)";
         
        statement = connect.createStatement();
        
        statement.execute(sql);
         
        sql = "CREATE TABLE Carre(nom varchar(20) PRIMARY KEY NOT NULL, " 
              + " cote int, x int, y int, "   
              + "groupeid varchar(30) references groupe(groupeid))";
        statement.execute(sql);
      
        sql = "CREATE TABLE Cercle(nom varchar(20) PRIMARY KEY NOT NULL, " 
              + " rayon int, x int, y int, "   
              + "groupeid varchar(30) references groupe(groupeid))";
        statement.execute(sql);
      
        sql = "CREATE TABLE Rectangle(nom varchar(20) PRIMARY KEY NOT NULL, " 
              + " h int, w int, x int, y int, "   
              + "groupeid varchar(30) references groupe(groupeid))";
        statement.execute(sql);
      
        sql = "CREATE TABLE Triangle(nom varchar(20) PRIMARY KEY NOT NULL, " 
              + " cote int, ax int, ay int, bx int, \"by\" int, cx int,  cy int,"   
              + "groupeid varchar(30) references groupe(groupeid))";
        statement.execute(sql);
      
      
        statement.close();
      
    
      }
        
        
      connect.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    commands = new HashMap<String, Command>();
    formes = new ArrayList<Forme>();
    movedFormes = new ArrayList<Forme>();
    //groupes = new ArrayList<FormeGroupe>();
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
    commands.put("save", new SaveCommand());
    commands.put("load", new LoadCommand());
  }
 
 
  private CreationCommand getCreationCmd(String[] result) {
    CreationCommand command = null;
    Forme f = null;
    try {
      switch (result[1].toLowerCase()) {
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
          Point pa = new Point(Integer.parseInt(result[2]), Integer.parseInt(result[3]));
          Point pb = new Point(Integer.parseInt(result[4]), Integer.parseInt(result[5]));
          Point pc = new Point(Integer.parseInt(result[6]), Integer.parseInt(result[7]));
          f = new Triangle(name, groupeid, pa, pb, pc);
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
    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
      return null;
    }
 
  }
 
  private FormeGroupeDeplacement getGroupeMovementCmd(String[] result) {
    FormeGroupe groupeForme = new FormeGroupe("gr" + groupeNumber);
    FormeGroupeDeplacement command = (FormeGroupeDeplacement) this.commands.get("moveall");
    int i = 1;
    try {
      while (i != result.length - 2) {
        Forme forme = getFormeByName(this.formes, result[i]);
        movedFormes.add(forme);
        forme.setGroupeid("gr" + groupeNumber);
        groupeForme.addForme(forme);
        i += 1;
      }
 
      int x = Integer.parseInt(result[i]);
      int y = Integer.parseInt(result[i + 1]);
      command.setParameters(x, y);
      command.setForm(groupeForme);
      groupeNumber += 1;
      return command;
    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
      return null;
    }
  }

  private class SaveCommand implements Command {
    private FormeGroupe groupe;
    private DaoFactory dao;
    private Dao<FormeGroupe> daoG;
  
    public void setGroupe(FormeGroupe groupe) {
      this.groupe = groupe;
    }

    public SaveCommand() {
      dao = new DaoFactory();
      daoG = dao.createGroupeDao();
    }

    @Override
    public void execute() {
      daoG.delete(groupe);
      daoG.create(groupe);
    }
  }

  private LoadCommand getLoadCommand(String[] result) {
    LoadCommand command = (LoadCommand) this.commands.get("load");
    try {
      command.setGroupe(result[1]);
    } catch (ArrayIndexOutOfBoundsException e) {
      return null;
    }
    return command;
  }

  private class LoadCommand implements Command {
    private String groupe;
    private DaoFactory dao;
    private Dao<FormeGroupe> daoG;
    private FormeGroupe formeGroupe;
  
    public void setGroupe(String groupe) {
      this.groupe = groupe;
    }

    public LoadCommand() {
      dao = new DaoFactory();
      daoG = dao.createGroupeDao();
    }

    @Override
  public void execute() {
      formeGroupe = daoG.find(groupe);
      Iterator<Forme> iterator = formeGroupe.iterator();
      while (iterator.hasNext()) {
        DrawingTui.this.formes.add(iterator.next());
      }
    }
  }

  private Forme getFormeByName(final List<Forme> list, final String name) {
    try {
      return list.stream().filter(o -> o.getNom().equals(name)).findFirst().get();
    } catch (NoSuchElementException e) {
      String message = "Forme " + name + " doesn't exist";
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

    for (int i = 1; i < result.length; i++) {
      Forme element = getFormeByName(this.formes, result[i]);
      if (element == null) {
        return null;
      } else {
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
    for (int i = 1; i < result.length;i++) {
      listOfFormes.add(getFormeByName(this.formes, result[i]));
      deletedFormes = new ArrayList<Forme>(listOfFormes);
    }
    command.setForme(listOfFormes);
    return command;
  }
 
  private Command getSaveCommand(String[] result) {
    SaveCommand command = (SaveCommand) this.commands.get("save");
    try {
      FormeGroupe groupe = new FormeGroupe(result[1]);
      for (Forme f: this.formes) {
        f.setGroupeid(result[1]);
        groupe.addForme(f);
      }
      command.setGroupe(groupe);
    } catch (ArrayIndexOutOfBoundsException e) {
      return null;
    }
    return command;
  }
  /**
   * gets the next command based on user input.
   * @param userText user text.
   */

  public Command nextCommand(String userText) {
    userText = userText.replaceAll("[=)(,]", " ");
    String[] result = userText.split("\\s+");
    Command command = null;

  
    switch (result[0].toLowerCase()) {
      case "move":
        command = getMovementCmd(result);
        break;
      case "moveall":
        command = getGroupeMovementCmd(result);
   
        break;
      case "save":
        command = getSaveCommand(result);
        break;
      case "load":
        this.formes.clear();
        command = getLoadCommand(result);
        break;
      case "delete":
        command = getDeleteCommand(result);
        break;
      case "deleteall":
        command = this.commands.get("deleteall");
        break;
      case "show":
        if (result.length == 1) {
          return null;
        }
        command = getShowCommand(result);
        break;
      case "showall":
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
 

  private class CarreCreation extends CreationCommand implements Command {
 
 
    @Override
    public void execute() {

      DrawingTui.this.formes.add(new Carre(((Carre)forme).getNom(),
          ((Carre)forme).getCote(),
          ((Carre)forme).getPoint(),
          ((Carre)forme).getGroupeid()));
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
 
  private class RectangleCreation extends CreationCommand implements Command {
 
    @Override
    public void execute() {

      DrawingTui.this.formes.add(new Rectangle((
          (Rectangle)forme).getNom(),
          ((Rectangle)forme).getPoint(),
          ((Rectangle)forme).getHeight(),
          ((Rectangle)forme).getWidth(),
          ((Rectangle)forme).getGroupeid()));
    }
  
  }
 
  private class CercleCreation extends CreationCommand implements Command {
  
    @Override

    public void execute() {
      DrawingTui.this.formes.add(new Cercle((
          (Cercle)forme).getNom(),
          ((Cercle)forme).getRayon(),
          ((Cercle)forme).getCentre(),
          ((Cercle)forme).getGroupeid()));
    }
  
  }

  private class TriangleCreation extends CreationCommand implements Command {

  
    @Override
    public void execute() {
      DrawingTui.this.formes.add(new Triangle((
           (Triangle)forme).getNom(),
           ((Triangle)forme).getGroupeid(),
           ((Triangle)forme).getA(),
           ((Triangle)forme).getB(),
           ((Triangle)forme).getC()));
    }
  
  }
 
  private class FormeDeplacement extends MovementCommand implements Command {
 
    public void setParameters(int x, int y) {
      this.px = x;
      this.py = y;
    }

    @Override
    public void execute() {
      forme.move(px, py);
    }
  
  }

  private class FormeGroupeDeplacement  extends MovementCommand implements Command {

 
    public void setParameters(int x, int y) {
      this.px = x;
      this.py = y;
    }

    @Override
    public void execute() {
      forme.move(px, py);
   
    }
  
  }

  private class FormeDeletion implements Command {
    private List<Forme> listOfFormes;
  
    public void setForme(List<Forme> formes) {
      listOfFormes = formes;
    }
  
    public void execute() {
      for (Forme f : listOfFormes) {
        for (Iterator<Forme> iterator = DrawingTui.this.formes.iterator(); iterator.hasNext(); ) {
          if (iterator.next().getNom().equals(f.getNom())) {
            iterator.remove();
          }
        }
      }
    }
  
  
  }

  private class FormeDeleteionAll implements Command {
  
    @Override
    public void execute() {
      DrawingTui.this.formes.clear();
    }
  }
 
  private class ShowCommand implements Command {
    private FormeGroupe groupe;
  
    public void setForme(FormeGroupe g) {
      this.groupe = g;
    }

    @Override
    public void execute() {
      this.groupe.show();
   
    }
  
  }

  private class ShowAllCommand implements Command {
    @Override
    public void execute() {
      for (Forme f : DrawingTui.this.formes) {
        f.show();
      }
   
    }
  
  }

  public List<Forme> getFormes() {
    return this.formes;
  }


  public List<Forme> getMovedFormes() {
    return this.movedFormes;
  }


  /**
  * Get all deleted forms.
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
