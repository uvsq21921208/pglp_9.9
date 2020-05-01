package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dessin.Rectangle;
import dessin.Point;

public class RectangleDao extends Dao<Rectangle> {

  @Override
  public Rectangle create(Rectangle obj) {
    this.connect();
    PreparedStatement insert = null;
    int i = -1;
    try {
      insert = this.connect.prepareStatement("Insert into Rectangle"
          + "(nom, h, w, x, y, groupeid)"
          + " values (?,?,?,?,?)");
      insert.setString(1, obj.getNom());
      insert.setInt(2, obj.getHeight());
      insert.setInt(3, obj.getWidth());
      insert.setInt(4, obj.getPoint().getX());
      insert.setInt(5, obj.getPoint().getY());
      insert.setString(6, obj.getGroupeid());
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
  public Rectangle find(String id) {
    Rectangle c = null;
    this.connect();
    PreparedStatement select = null;
    try {
      select = this.connect.prepareStatement("select * from Rectangle" 
          + " where nom = (?)");
      select.setString(1, id);
      select.execute();
      ResultSet result = select.getResultSet();
      if (result.next()) {
    	String nom = result.getString("nom");
        int h = result.getInt("h");
        int w = result.getInt("w");
        int x = result.getInt("x");
        int y = result.getInt("y");
        String groupeid = result.getString("groupeid");
        c = new Rectangle(nom, new Point(x,y), h,w,  groupeid);
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
  public Rectangle update(Rectangle obj) {
    this.connect();
    PreparedStatement update = null;
    try {
      update = this.connect.prepareStatement("update Rectangle"
          + " set h = (?), w = (?), x = (?), y = (?)" 
          + " where nom = (?) ");
      update.setInt(1, obj.getHeight());
      update.setInt(2, obj.getWidth());
      update.setInt(3, obj.getPoint().getX());
      update.setInt(4, obj.getPoint().getY());
      update.setString(5, obj.getNom());
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
  public void delete(Rectangle obj) {
    this.connect();
    PreparedStatement delete = null;
    try {
      delete = this.connect.prepareStatement("delete from Rectangle " 
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