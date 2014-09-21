package com.secret.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.lang.ClassNotFoundException;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class JDBCConnector
{    
    public Connection connect() {      
    
        Connection conn = null;
        try {
          Class.forName("org.postgresql.Driver");
        
          final Config conf = ConfigFactory.load();
          final String connectionString = conf.getString("database.secret.connection"); 
          conn = DriverManager.getConnection(connectionString);
        } catch (java.lang.ClassNotFoundException e) {
          e.printStackTrace();
          System.err.println(e.getClass().getName()+": "+e.getMessage());        
        } catch (Exception e) {
          e.printStackTrace();
          System.err.println(e.getClass().getName()+": "+e.getMessage());
        }

        if (conn != null) {
          System.out.println("Successfully connected!");
        } else {
          System.out.println("Failed to make connection!");
        }
        
        return conn;
    }

}
