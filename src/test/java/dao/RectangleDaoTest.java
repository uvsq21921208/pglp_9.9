package dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import dessin.Rectangle;
import dessin.FormeGroupe;
import dessin.Point;


public class RectangleDaoTest {
	public Connection connect = null;
	Statement statement;
	
	public void setUp() {
	
		 
		 try {
		      String sql = "CREATE TABLE Groupe(groupeid varchar(30) primary key not null)";
		      statement = connect.createStatement();
		      statement.execute(sql);
		      sql = "CREATE TABLE Rectangle(nom varchar(20) PRIMARY KEY NOT NULL, " 
				         + " h int, w int, x int, y int, "   
				         + "groupeid varchar(30) references groupe(groupeid))";
			  statement.execute(sql);
			  statement.close();
			  
			
		      
		     
		    } catch (SQLException e) {

		      e.printStackTrace();
		    }
		 
	}
	
	@Test
	public void createCarre(){
		DaoFactory daoFac = new DaoFactory();
		dao.Dao<FormeGroupe> daoG = daoFac.createGroupeDao();
		daoG.connect();
		connect = daoG.connect;
		setUp();
	    Dao<Rectangle> dao = daoFac.createRectangleDao();
	    dao.connect();
	    FormeGroupe g = new FormeGroupe("groupe1");
	    daoG.create(g);
	    Rectangle c = new Rectangle("c",new Point(1,2),1,1,"groupe1");
	    assertNotNull(dao.create(c));
	    
	    
	    try {
	    	String sql = "drop table Rectangle";
			statement = connect.createStatement();
			statement.execute(sql);
			sql = "drop table groupe";
			statement = connect.createStatement();
			statement.execute(sql);
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	

	@Test
	public void findCarre(){
		DaoFactory daoFac = new DaoFactory();
		dao.Dao<FormeGroupe> daoG = daoFac.createGroupeDao();
		daoG.connect();
		connect = daoG.connect;
		setUp();
	    Dao<Rectangle> dao = daoFac.createRectangleDao();
	    dao.connect();
	    FormeGroupe g = new FormeGroupe("groupe2");
	    daoG.create(g);
	    Rectangle c = new Rectangle("c2",new Point(1,2),1,1,"groupe2");
	    dao.create(c);
	    Rectangle found = dao.find(c.getNom());
	    
	    assertEquals(found.getPoint().getX(), c.getPoint().getX());
	    
	    
	    try {
	    	String sql = "drop table Rectangle";
			statement = connect.createStatement();
			statement.execute(sql);
			sql = "drop table groupe";
			statement = connect.createStatement();
			statement.execute(sql);
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	@Test 
	public void deleteCarre() {
		DaoFactory daoFac = new DaoFactory();
		dao.Dao<FormeGroupe> daoG = daoFac.createGroupeDao();
		daoG.connect();
		connect = daoG.connect;
		setUp();
	    Dao<Rectangle> dao = daoFac.createRectangleDao();
	    dao.connect();
	    FormeGroupe g = new FormeGroupe("groupe3");
	    daoG.create(g);
	    Rectangle c = new Rectangle("c3",new Point(1,2),1,1,"groupe3");
	    dao.create(c);
	    dao.delete(c);
	    Rectangle found = dao.find(c.getNom());
	    
	    assertNull(found);
	    
	    
	    try {
	    	String sql = "drop table Rectangle";
			statement = connect.createStatement();
			statement.execute(sql);
			sql = "drop table groupe";
			statement = connect.createStatement();
			statement.execute(sql);
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	
	@Test 
	public void updateCarre() {
		DaoFactory daoFac = new DaoFactory();
		dao.Dao<FormeGroupe> daoG = daoFac.createGroupeDao();
		daoG.connect();
		connect = daoG.connect;
		setUp();
	    Dao<Rectangle> dao = daoFac.createRectangleDao();
	    dao.connect();
	    FormeGroupe g = new FormeGroupe("groupe4");
	    daoG.create(g);
	    Rectangle c = new Rectangle("c4",new Point(1,2),1,1,"groupe4");
	    dao.create(c);
	    Rectangle c1 = new Rectangle("c4",new Point(0,0),1,1,"groupe4");
	    dao.update(c1);
	    
	    Rectangle found = dao.find(c.getNom());
	    
	    
	    assertEquals(found.getPoint().getX(), 0);
	    assertEquals(found.getPoint().getY(), 0);
	    
	    
	    try {
	    	String sql = "drop table Rectangle";
			statement = connect.createStatement();
			statement.execute(sql);
			sql = "drop table groupe";
			statement = connect.createStatement();
			statement.execute(sql);
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	
}
