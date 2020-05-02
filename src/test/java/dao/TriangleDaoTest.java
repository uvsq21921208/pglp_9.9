package dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import dessin.Triangle;
import dessin.FormeGroupe;
import dessin.Point;


public class TriangleDaoTest {
	public Connection connect = null;
	Statement statement;
	
	public void setUp() {
	
		 
		 try {
		      String sql = "CREATE TABLE Groupe(groupeid varchar(30) primary key not null)";
		      statement = connect.createStatement();
		      statement.execute(sql);
		      sql = "CREATE TABLE Triangle(nom varchar(20) PRIMARY KEY NOT NULL, " 
				         + " cote int, ax int, ay int, bx int, \"by\" int, cx int,  cy int,"   
				         + "groupeid varchar(30) references groupe(groupeid))";
			  statement.execute(sql);
			  statement.close();
			
			  
			
		      
		     
		    } catch (SQLException e) {

		      e.printStackTrace();
		    }
		 
	}
	
	@Test
	public void createTriangle(){
		DaoFactory daoFac = new DaoFactory();
		dao.Dao<FormeGroupe> daoG = daoFac.createGroupeDao();
		daoG.connect();
		connect = daoG.connect;
		setUp();
	    Dao<Triangle> dao = daoFac.createTriangleDao();
	    dao.connect();
	    FormeGroupe g = new FormeGroupe("groupe1");
	    daoG.create(g);
	    Triangle c = new Triangle("c1","groupe1" , new Point(1,2), new Point(0,0), new Point(3,3));;
	    assertNotNull(dao.create(c));
	    
	    
	    try {
	    	String sql = "drop table Triangle";
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
	public void findTriangle(){
		DaoFactory daoFac = new DaoFactory();
		dao.Dao<FormeGroupe> daoG = daoFac.createGroupeDao();
		daoG.connect();
		connect = daoG.connect;
		setUp();
	    Dao<Triangle> dao = daoFac.createTriangleDao();
	    dao.connect();
	    FormeGroupe g = new FormeGroupe("groupe2");
	    daoG.create(g);
	    Triangle c = new Triangle("c2","groupe2" , new Point(1,2), new Point(0,0), new Point(3,3));
	    dao.create(c);
	    Triangle found = dao.find(c.getNom());
	    
	    assertEquals(found.getA().getX(), c.getA().getX());
	    
	    
	    try {
	    	String sql = "drop table Triangle";
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
	public void deleteTriangle() {
		DaoFactory daoFac = new DaoFactory();
		dao.Dao<FormeGroupe> daoG = daoFac.createGroupeDao();
		daoG.connect();
		connect = daoG.connect;
		setUp();
	    Dao<Triangle> dao = daoFac.createTriangleDao();
	    dao.connect();
	    FormeGroupe g = new FormeGroupe("groupe3");
	    daoG.create(g);
	    Triangle c = new Triangle("c3","groupe3" , new Point(1,2), new Point(0,0), new Point(3,3));;
	    dao.create(c);
	    dao.delete(c);
	    Triangle found = dao.find(c.getNom());
	    
	    assertNull(found);
	    
	    
	    try {
	    	String sql = "drop table Triangle";
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
	public void updateTriangle() {
		DaoFactory daoFac = new DaoFactory();
		dao.Dao<FormeGroupe> daoG = daoFac.createGroupeDao();
		daoG.connect();
		connect = daoG.connect;
		setUp();
	    Dao<Triangle> dao = daoFac.createTriangleDao();
	    dao.connect();
	    FormeGroupe g = new FormeGroupe("groupe4");
	    daoG.create(g);
	    Triangle c = new Triangle("c4","groupe4" , new Point(1,2), new Point(0,0), new Point(3,3));
	    dao.create(c);
	    Triangle c1 = new Triangle("c4","groupe4" , new Point(4,4), new Point(0,0), new Point(3,3));
	    dao.update(c1);
	    
	    Triangle found = dao.find(c.getNom());
	    assertEquals(found.getA().getX(), 4);
	    assertEquals(found.getA().getY(), 4);
	    
	    
	    try {
	    	String sql = "drop table Triangle";
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
