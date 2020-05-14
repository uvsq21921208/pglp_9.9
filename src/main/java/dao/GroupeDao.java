package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import dessin.Carre;
import dessin.Cercle;
import dessin.Forme;
import dessin.FormeGroupe;
import dessin.Rectangle;
import dessin.Triangle;

public class GroupeDao extends Dao<FormeGroupe> {

  @Override
  public FormeGroupe create(FormeGroupe obj) {

    int i = -1;
    this.connect();
    PreparedStatement insert = null;
    try {
      String sql = "insert into Groupe(groupeid) values (?)";
      insert = this.connect.prepareStatement(sql);
      insert.setString(1, obj.getNom());
      i = insert.executeUpdate();
      
      DaoFactory df = new DaoFactory();
      Dao<Carre> daoCar = df.createCarreDao();
      Dao<Cercle> daoCercle = df.createCercleDao();
      Dao<Triangle> daoTriangle = df.createTriangleDao();
      Dao<Rectangle> daoRectangle = df.createRectangleDao();
      
      Iterator<Forme> iter = obj.iterator();
      while (iter.hasNext()) {
    	  Forme f  = iter.next();
          if ( f instanceof Carre) {
        	  daoCar.create((Carre) f);
          }
          if ( f instanceof Cercle) {
        	  daoCercle.create((Cercle) f);
          }
          if ( f instanceof Triangle) {
        	  daoTriangle.create((Triangle) f);
          }
          if ( f instanceof Rectangle) {
        	  daoRectangle.create((Rectangle) f);
          }
          
      }

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
    //this.disconnect();
    if (i > 0) {
      return obj;
    } else {
      return null;
    }
  }

  @Override
  public FormeGroupe find(String id) {
    FormeGroupe obj = null;
    this.connect();
    PreparedStatement select = null;
    try {
      String sql = "select * from Groupe where groupeid = (?)";
      select = connect.prepareStatement(sql);
      select.setString(1, id);
      select.execute();
     
      DaoFactory df = new DaoFactory();
      Dao<Carre> daoCar = df.createCarreDao();
      Dao<Cercle> daoCercle = df.createCercleDao();
      Dao<Triangle> daoTriangle = df.createTriangleDao();
      Dao<Rectangle> daoRectangle = df.createRectangleDao();
      
      ResultSet result = select.getResultSet();
      if (result.next()) {
          obj = new FormeGroupe(result.getString(1));
          ArrayList<Cercle> cercles = daoCercle.getAllGroupeObject(obj.getNom());
          ArrayList<Triangle> triangles = daoTriangle.getAllGroupeObject(obj.getNom());
          ArrayList<Carre> carres = daoCar.getAllGroupeObject(obj.getNom());
          ArrayList<Rectangle> rectangles = daoRectangle.getAllGroupeObject(obj.getNom());
          for (Carre c: carres) {
            obj.addForme(c);
          }
          
          for (Rectangle r: rectangles) {
              obj.addForme(r);
          }
          
          for (Cercle c: cercles) {
              obj.addForme(c);
          }
          
          for (Triangle t: triangles) {
              obj.addForme(t);
          }
          
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
    return obj;
  }

  @Override
  public FormeGroupe update(FormeGroupe obj) {
    this.connect();
    PreparedStatement updateGroupe = null;
    try {
      updateGroupe =
          this.connect.prepareStatement("update Groupe set groupeid = (?)");
      updateGroupe.setString(1, obj.getNom());
      DaoFactory df = new DaoFactory();
      Dao<Carre> daoCar = df.createCarreDao();
      Dao<Cercle> daoCercle = df.createCercleDao();
      Dao<Triangle> daoTriangle = df.createTriangleDao();
      Dao<Rectangle> daoRectangle = df.createRectangleDao();
      Iterator<Forme> formeIter = obj.iterator();
      while (formeIter.hasNext()) {
    	  
    	Forme f = formeIter.next();
    	if (f instanceof Cercle) {
    		
        if (daoCercle.find(((Cercle) f).getNom()) != null) {
        	daoCercle.update((Cercle) f);
        } else {
        	daoCercle.create((Cercle) f);
        }
      }
    	
    	if (f instanceof Rectangle) {
    		
            if (daoRectangle.find(((Rectangle) f).getNom()) != null) {
            	daoRectangle.update((Rectangle) f);
            } else {
            	daoRectangle.create((Rectangle) f);
            }
          }
    	
    	if (f instanceof Triangle) {
    		
            if (daoTriangle.find(((Triangle) f).getNom()) != null) {
            	daoTriangle.update((Triangle) f);
            } else {
            	daoTriangle.create((Triangle) f);
            }
          }
    	
    	if (f instanceof Carre) {
    		
            if (daoCar.find(((Carre) f).getNom()) != null) {
            	daoCar.update((Carre) f);
            } else {
            	daoCar.create((Carre) f);
            }
          }
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } 
    try {
      if (updateGroupe != null) {
        updateGroupe.close();
      } 
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    //this.disconnect();
    return obj;
  }

  @Override
  public void delete(FormeGroupe obj) {
    this.connect();
    PreparedStatement delete = null;
    try {
      
      
      Iterator<Forme> i = obj.iterator();
      while (i.hasNext()) {
    	  i.next();
        
      }
      String sql = "Delete from Groupe where groupeid = (?)";
      String groupeid = obj.getNom();
      delete = connect.prepareStatement(sql);
      delete.setString(1, groupeid);
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
public ArrayList<FormeGroupe> getAllGroupeObject(String id) {
	return null;
	
}

}