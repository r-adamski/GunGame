package me.panda.minecraft.gungame.methods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import me.panda.minecraft.gungame.Main;

	public class MySQLDatabase
	{
		
	  private String hostname = "localhost";
	  private String username = "minecraft";
	  private String portnum = "3306";
	  private String dbname = "minecraft";
	  private String password = "";
	  protected  Connection conn;

	  public MySQLDatabase(Main is, String host, String user, String port, String password, String db)
	  {
	    this.hostname = host;
	    this.username = user;
	    this.portnum = port;
	    this.dbname = db;
	    this.password = password;
	  }

	  protected boolean initialize() {
	    try {
	      Class.forName("com.mysql.jdbc.Driver");
	      return true;
	    } catch (ClassNotFoundException e) {
	    	Main.addLog("[MySQL] Could not load JDBC driver " + e.getMessage());
	    }return false;
	  }

	  public Connection open()
	  {
	    if (initialize()) {
	      String url = "";
	      try {
	        url = "jdbc:mysql://" + this.hostname + ":" + this.portnum + "/" + this.dbname;

	        this.conn = DriverManager.getConnection(url, this.username, this.password);
	      } catch (SQLException e) {
	    	  Main.addLog("[MySQL] Could not connect to database" + e.getMessage());
	      }
	    }
	    return null;
	  }

	  public void close()
	  {
	    try {
	      if (this.conn != null)
	        this.conn.close();
	    } catch (Exception e) {
	    	Main.addLog("[MySQL] Could not close connection to database" + e.getMessage());
	    }
	  }

	  public Connection getConnection() {
	    if (this.conn == null) {
	      return open();
	    }
	    return this.conn;
	  }

	  public boolean checkConnection()
	  {
	    return this.conn != null;
	  }

	  public ResultSet query(String query)
	  {
	    Statement statement = null;
	    ResultSet result = null;
	    try
	    {
	      if (!checkConnection()) {
	        open();
	      }

	      statement = this.conn.createStatement();
	      result = statement.executeQuery("SELECT CURTIME()");

	      if (query.contains("SELECT"))
	        result = statement.executeQuery(query);
	      else {
	        statement.executeUpdate(query);
	      }

	      return result;
	    } catch (SQLException e) {
	    	Main.addLog("[MySQL] Error in SQL query: " + e.getMessage());
	    }
	    return result;
	  }

	  public boolean createTable(String query) {
	    Statement statement = null;
	    try
	    {
	      if ((query.equals("")) || (query == null)) {
	    	  Main.addLog(Main.pref + "MySQL-SQL query puste: createTable(" + query + ")");
	        return false;
	      }

	      statement = this.conn.createStatement();
	      statement.execute(query);
	      return true;
	    } catch (SQLException e) {
	    	Main.addLog(e.getMessage());
	      return false;
	    } catch (Exception e) {
	    	Main.addLog(e.getMessage());
	    }return false;
	  }

	  public boolean checkTable(String table)
	  {
	    try
	    {
	      Statement statement = this.conn.createStatement();

	      ResultSet result = statement.executeQuery("SELECT * FROM " + table);

	      if (result == null)
	        return false;
	      if (result != null)
	        return true;
	    } catch (SQLException e) {
	      if (e.getMessage().contains("exist")) {
	        return false;
	      }
	      Main.addLog("[MySQL] Error in SQL query: " + e.getMessage());

	      if (query("SELECT * FROM " + table) == null) return true;  } return false;
	  }
	}
