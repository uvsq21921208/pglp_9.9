package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dessin.Cercle;
import dessin.Point;

public class CercleDao extends Dao<Cercle> {

  @Override
  public Cercle create(Cercle obj) {
    this.connect();
    PreparedStatement insert = null;
    int i = -1;
    try {
      insert = this.connect.prepareStatement("Insert into Cercle"
          + "(nom, rayon, x, y, groupeid)"
          + " values (?,?,?,?,?)");
      insert.setString(1, obj.getNom());
      insert.setInt(2, obj.getRayon());
      insert.setInt(3, obj.getCentre().getX());
      insert.setInt(4, obj.getCentre().getY());
      insert.setString(5, obj.getGroupeid());
      i = insert.executeUpdate();
     

    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      if (insert != null) {
        insert.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    if (i > 0) {
      return obj;
    } else {
      return null;
    }
  }

  @Override
  public Cercle find(String id) {
    Cercle c = null;
    this.connect();
    PreparedStatement select = null;
    try {
      select = this.connect.prepareStatement("select * from Cercle" 
          + " where nom = (?)");
      select.setString(1, id);
      select.execute();
      ResultSet result = select.getResultSet();
      if (result.next()) {
    	String nom = result.getString("nom");
        int rayon = result.getInt("rayon");
        int x = result.getInt("x");
        int y = result.getInt("y");
        String groupeid = result.getString("groupeid");
        c = new Cercle(nom, rayon, new Point(x,y), groupeid);
        select.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      if (select != null) {
        select.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    //this.disconnect();
    return c;

  }

  @Override
  public Cercle update(Cercle obj) {
    this.connect();
    PreparedStatement update = null;
    try {
      update = this.connect.prepareStatement("update Cercle"
          + " set cote = (?), x = (?), y = (?)" 
          + " where nom = (?) ");
      update.setInt(1, obj.getRayon());
      update.setInt(3, obj.getCentre().getX());
      update.setInt(3, obj.getCentre().getY());
      update.setString(4, obj.getNom());
      update.executeUpdate();
      update.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      if (update != null) {
        update.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return obj;
  }

  @Override
  public void delete(Cercle obj) {
    this.connect();
    PreparedStatement delete = null;
    try {
      delete = this.connect.prepareStatement("delete from Cercle " 
          + "where nom = (?)");
      delete.setString(1, obj.getNom());
      delete.execute();
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      if (delete != null) {
        delete.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }
  
}