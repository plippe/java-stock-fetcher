package com.secret.app;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by User on 10/05/2014.
 */
@Component
public class JDBCConnector
{
    public static final String host = "ec2-54-217-232-235.eu-west-1.compute.amazonaws.com";
    public static final String database = "/d19jtlr4l8g99q";
    public static final String user = "hcanocdpgowsvl";
    public static final String password = "WyKQumTsTBWrHM7HRJJFhMtozn";
    public static final String port = "5432";
    
    public Connection connect()
    {
        Connection conn = null;
        try
        {
            Class.forName("org.postgresql.Driver");
        }
        catch (Exception e)
        {
            System.out.println("NO JDBC DRIVER");
            e.printStackTrace();
        }

        try
        {
            conn = DriverManager.getConnection("jdbc:postgresql://"+ host + ":"+ port +"" + database +"?user="+user +"&password=" + password + "&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory");
        }
        catch (Exception e)
        {
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
