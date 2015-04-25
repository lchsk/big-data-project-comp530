package com.bigdata.utility;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import com.bigdata.Main;

public class Conn
{
    private static final Logger log = Logger.getLogger(Conn.class.getName());
    
    private static Conn instance = null;
    
    private String driverName = "org.apache.hive.jdbc.HiveDriver";
    private String url = "jdbc:hive2://";
    private Connection conn;
    private Statement s;
    
    public static Conn getInstance() throws UnknownHostException
    {
        if (instance == null)
            instance = new Conn();

        return instance;
    }
    
    private Conn()
    {
        Config.getInstance().registerLogger(log);
        
        try 
        {
            Class.forName(driverName);
        } 
        catch (ClassNotFoundException e) 
        {
            log.severe("Driver not found");
            e.printStackTrace();
            System.exit(1);
        }
        
        try
        {
            conn = DriverManager.getConnection(url, "", "");
            s = conn.createStatement();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public void testConnection() throws SQLException
    {
        boolean success = true;
        log.info("Testing connection...");
        
        try
        {
            ResultSet res = s.executeQuery("show tables");
        }
        catch (Exception e)
        {
            success = false;
            e.printStackTrace();
        }
        
        if (success)
        {
            log.info("Connection test success.");
        }
        else
        {
            log.severe("Connection failure.");
        }
    }
}
