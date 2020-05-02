package dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import dessin.Cercle;
import dessin.FormeGroupe;
import dessin.Point;


public class CercleDaoTest {
	public Connection connect = null;
	Statement statement;
	
	public void setUp() {
	
		 
		 try {
		      String sql = "CREATE TABLE Groupe(groupeid varchar(30) primary key not null)";
		      statement = connect.createStatement();
		      statement.execute(sql);
		      sql = "CREATE TABLE Cercle(nom varchar(20) PRIMARY KEY NOT NULL, " 
				         + " rayon int, x int, y int, "   
				         + "groupeid varchar(30) references groupe(groupeid))";
			  statement.execute(sql);
			  statement.close();
			  
			
		      
		     
		    } catch (SQLException e) {

		      e.printStackTrace();
		    }
		 
	}
	
	@Test
	public void createCercle(){
		DaoFactory daoFac = new DaoFactory();
		dao.Dao<FormeGroupe> daoG = daoFac.createGroupeDao();
		daoG.connect();
		connect = daoG.connect;
		setUp();
	    Dao<Cercle> dao = daoFac.createCercleDao();
	    dao.connect();
	    FormeGroupe g = new FormeGroupe("groupe1");
	    daoG.create(g);
	    Cercle c = new Cercle("c",2 , new Point(1,2),"groupe1");
	    assertNotNull(dao.create(c));
	    
	    
	    try {
	    	String sql = "drop table Cercle";
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
	public void findCercle(){
		DaoFactory daoFac = new DaoFactory();
		dao.Dao<FormeGroupe> daoG = daoFac.createGroupeDao();
		daoG.connect();
		connect = daoG.connect;
		setUp();
	    Dao<Cercle> dao = daoFac.createCercleDao();
	    dao.connect();
	    FormeGroupe g = new FormeGroupe("groupe2");
	    daoG.create(g);
	    Cercle c = new Cercle("c2",2 , new Point(1,2),"groupe2");
	    dao.create(c);
	    Cercle found = dao.find(c.getNom());
	    
	    assertEquals(found.getRayon(), c.getRayon());
	    
	    
	    try {
	    	String sql = "drop table Cercle";
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
	public void deleteCercle() {
		DaoFactory daoFac = new DaoFactory();
		dao.Dao<FormeGroupe> daoG = daoFac.createGroupeDao();
		daoG.connect();
		connect = daoG.connect;
		setUp();
	    Dao<Cercle> dao = daoFac.createCercleDao();
	    dao.connect();
	    FormeGroupe g = new FormeGroupe("groupe3");
	    daoG.create(g);
	    Cercle c = new Cercle("c3",2 , new Point(1,2),"groupe3");
	    dao.create(c);
	    dao.delete(c);
	    Cercle found = dao.find(c.getNom());
	    
	    assertNull(found);
	    
	    
	    try {
	    	String sql = "drop table Cercle";
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
	public void updateCercle() {
		DaoFactory daoFac = new DaoFactory();
		dao.Dao<FormeGroupe> daoG = daoFac.createGroupeDao();
		daoG.connect();
		connect = daoG.connect;
		setUp();
	    Dao<Cercle> dao = daoFac.createCercleDao();
	    dao.connect();
	    FormeGroupe g = new FormeGroupe("groupe4");
	    daoG.create(g);
	    Cercle c = new Cercle("c4",2 , new Point(1,2),"groupe4");
	    dao.create(c);
	    Cercle c1 = new Cercle("c4", 1, new Point(0,0),"groupe4");
	    dao.update(c1);
	    
	    Cercle found = dao.find(c.getNom());
	    
	    assertEquals(found.getRayon(), 1);
	    assertEquals(found.getCentre().getX(), 0);
	    assertEquals(found.getCentre().getY(), 0);
	    
	    
	    try {
	    	String sql = "drop table Cercle";
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
