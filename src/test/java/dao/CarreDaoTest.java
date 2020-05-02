package dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import dessin.Carre;
import dessin.FormeGroupe;
import dessin.Point;


public class CarreDaoTest {
	public Connection connect = null;
	Statement statement;
	
	public void setUp() {
	
		 
		 try {
		      String sql = "CREATE TABLE Groupe(groupeid varchar(30) primary key not null)";
		      statement = connect.createStatement();
		      statement.execute(sql);
		      sql = "CREATE TABLE Carre(nom varchar(20) PRIMARY KEY NOT NULL, " 
				         + " cote int, x int, y int, "   
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
	    Dao<Carre> dao = daoFac.createCarreDao();
	    dao.connect();
	    FormeGroupe g = new FormeGroupe("groupe1");
	    daoG.create(g);
	    Carre c = new Carre("c",2 , new Point(1,2),"groupe1");
	    assertNotNull(dao.create(c));
	    
	    
	    try {
	    	String sql = "drop table Carre";
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
	    Dao<Carre> dao = daoFac.createCarreDao();
	    dao.connect();
	    FormeGroupe g = new FormeGroupe("groupe2");
	    daoG.create(g);
	    Carre c = new Carre("c2",2 , new Point(1,2),"groupe2");
	    dao.create(c);
	    Carre found = dao.find(c.getNom());
	    
	    assertEquals(found.getCote(), c.getCote());
	    
	    
	    try {
	    	String sql = "drop table Carre";
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
	    Dao<Carre> dao = daoFac.createCarreDao();
	    dao.connect();
	    FormeGroupe g = new FormeGroupe("groupe3");
	    daoG.create(g);
	    Carre c = new Carre("c3",2 , new Point(1,2),"groupe3");
	    dao.create(c);
	    dao.delete(c);
	    Carre found = dao.find(c.getNom());
	    
	    assertNull(found);
	    
	    
	    try {
	    	String sql = "drop table Carre";
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
	    Dao<Carre> dao = daoFac.createCarreDao();
	    dao.connect();
	    FormeGroupe g = new FormeGroupe("groupe4");
	    daoG.create(g);
	    Carre c = new Carre("c4",2 , new Point(1,2),"groupe4");
	    dao.create(c);
	    Carre c1 = new Carre("c4", 1, new Point(0,0),"groupe4");
	    dao.update(c1);
	    
	    Carre found = dao.find(c.getNom());
	    
	    assertEquals(found.getCote(), 1);
	    assertEquals(found.getPoint().getX(), 0);
	    assertEquals(found.getPoint().getY(), 0);
	    
	    
	    try {
	    	String sql = "drop table Carre";
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
