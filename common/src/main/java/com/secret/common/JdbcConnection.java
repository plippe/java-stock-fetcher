package com.secret.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.lang.ClassNotFoundException;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class JdbcConnection {
  private static Connection cache = null;
  public static Connection get(Config conf) {
    if(cache == null) {
      try {      
        Class.forName("org.postgresql.Driver");        
        String connectionString = conf.getString("database.secret.connection");

        cache = DriverManager.getConnection(connectionString);
      } catch (Exception e) {
        e.printStackTrace();
        System.err.println(e.getClass().getName()+": "+e.getMessage());
      }
    }
    
    return cache;
  }
}
