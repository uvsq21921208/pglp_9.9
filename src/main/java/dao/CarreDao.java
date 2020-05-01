package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dessin.Carre;
import dessin.Point;

public class CarreDao extends Dao<Carre> {

  @Override
  public Carre create(Carre obj) {
    this.connect();
    PreparedStatement insert = null;
    int i = -1;
    try {
      insert = this.connect.prepareStatement("Insert into Carre"
          + "(nom, cote, x, y, groupeid)"
          + " values (?,?,?,?,?)");
      insert.setString(1, obj.getNom());
      insert.setInt(2, obj.getCote());
      insert.setInt(3, obj.getPoint().getX());
      insert.setInt(4, obj.getPoint().getY());
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
  public Carre find(String id) {
    Carre c = null;
    this.connect();
    PreparedStatement select = null;
    try {
      select = this.connect.prepareStatement("select * from Carre" 
          + " where nom = (?)");
      select.setString(1, id);
      select.execute();
      ResultSet result = select.getResultSet();
      if (result.next()) {
    	String nom = result.getString("nom");
        int cote = result.getInt("cote");
        int x = result.getInt("x");
        int y = result.getInt("y");
        String groupeid = result.getString("groupeid");
        c = new Carre(nom, cote, new Point(x,y), groupeid);
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
  public Carre update(Carre obj) {
    this.connect();
    PreparedStatement update = null;
    try {
      update = this.connect.prepareStatement("update Cercle"
          + " set cote = (?), x = (?), y = (?)" 
          + " where nom = (?) ");
      update.setInt(1, obj.getCote());
      update.setInt(3, obj.getPoint().getX());
      update.setInt(3, obj.getPoint().getY());
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
  public void delete(Carre obj) {
    this.connect();
    PreparedStatement delete = null;
    try {
      delete = this.connect.prepareStatement("delete from Carre " 
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