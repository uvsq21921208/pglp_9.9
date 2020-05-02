package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class JDBCTest {
	public Connection connect = null;
	Statement statement;
	
	
	public void setUp() {
		 try {
		      Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		      connect = DriverManager.getConnection("jdbc:derby:wzaeaeaze;create=true");
		    } catch (ClassNotFoundException | SQLException e) {
		      e.printStackTrace();
		      try {
		        connect.close();
		      } catch (SQLException ex) {
		        ex.printStackTrace();
		      }
		    }
		 
		 
		 try {
		      String sql = "CREATE TABLE groupe(groupeid varchar(30) primary key not null)";
		      statement = connect.createStatement();
		      statement.execute(sql);
		 
		      
		      sql = "CREATE TABLE Cercle(nom varchar(20) PRIMARY KEY NOT NULL, " 
		         + " rayon int, x int, y int, "   
		         + "groupeid varchar(30) references groupe(groupeid))";
		      statement.execute(sql);
		   
		      sql = "CREATE TABLE Carre(nom varchar(20) PRIMARY KEY NOT NULL, " 
				         + " cote int, x int, y int, "   
				         + "groupeid varchar(30) references groupe(groupeid))";
			  statement.execute(sql);
			
			  
			  sql = "CREATE TABLE Rectangle(nom varchar(20) PRIMARY KEY NOT NULL, " 
				         + " cote int, x int, y int, "   
				         + "groupeid varchar(30) references groupe(groupeid))";
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
	public void works() {
		setUp();
		
		
	}
}
