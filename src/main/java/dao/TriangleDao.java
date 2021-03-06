package dao;

import dessin.Point;
import dessin.Triangle;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class TriangleDao extends Dao<Triangle> {

  @Override
  public Triangle create(Triangle obj) {
    this.connect();
    PreparedStatement insert = null;
    int i = -1;
    try {
      insert = this.connect.prepareStatement("Insert into Triangle"
          + "(nom, ax, ay, bx, \"by\", cx, cy, groupeid)"
          + " values (?,?,?,?,?,?,?,?)");
      insert.setString(1, obj.getNom());
      insert.setInt(2, obj.getA().getX());
      insert.setInt(3, obj.getA().getY());
      insert.setInt(4, obj.getB().getX());
      insert.setInt(5, obj.getB().getY());
      insert.setInt(6, obj.getC().getX());
      insert.setInt(7, obj.getC().getY());
      insert.setString(8, obj.getGroupeid());
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
  public Triangle find(String id) {
    Triangle t = null;
    this.connect();
    PreparedStatement select = null;
    try {
      select = this.connect.prepareStatement("select * from Triangle" 
          + " where nom = (?)");
      select.setString(1, id);
      select.execute();
      ResultSet result = select.getResultSet();
      if (result.next()) {
        String nom = result.getString("nom");
        Point a = new Point(result.getInt("ax"), result.getInt("ay"));
        Point b = new Point(result.getInt("bx"), result.getInt("by"));
        Point c = new Point(result.getInt("cx"), result.getInt("cy"));
        
        String groupeid = result.getString("groupeid");
        t = new Triangle(nom, groupeid, a, b, c);
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
    return t;

  }

  @Override
  public Triangle update(Triangle obj) {
    this.connect();
    PreparedStatement update = null;
    try {
      update = this.connect.prepareStatement("update Triangle"
          + " set ax = (?), ay = (?), bx = (?), \"by\" = (?), cx = (?), cy = (?)" 
          + " where nom = (?) ");
      
      update.setInt(1, obj.getA().getX());
      update.setInt(2, obj.getA().getY());
      update.setInt(3, obj.getB().getX());
      update.setInt(4, obj.getB().getY());
      update.setInt(5, obj.getC().getX());
      update.setInt(6, obj.getC().getY());
      update.setString(7, obj.getNom());
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
  public void delete(Triangle obj) {
    this.connect();
    PreparedStatement delete = null;
    try {
      delete = this.connect.prepareStatement("delete from Triangle " 
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

  @Override
  public ArrayList<Triangle> getAllGroupeObject(String id) {
    ArrayList<Triangle> triangles = new ArrayList<Triangle>();
    this.connect();
    PreparedStatement select = null;
    try {
      select = this.connect.prepareStatement("Select * from triangle "
          + "where groupeid = (?)");
      select.setString(1, id);
      select.execute();
      ResultSet result = select.getResultSet();

      while (result.next()) {
        String nom = result.getString("nom");
        Point a = new Point(result.getInt("ax"), result.getInt("ay"));
        Point b = new Point(result.getInt("bx"), result.getInt("by"));
        Point c = new Point(result.getInt("cx"), result.getInt("cy"));
        Triangle t = new Triangle(nom, id, a, b, c);
        triangles.add(t);
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
    return triangles;
  }
  
}