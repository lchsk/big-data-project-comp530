package com.bigdata;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.bigdata.utility.Config;
import com.bigdata.utility.Conn;

public class Main 
{
    private static final Logger log = Logger.getLogger(Main.class.getName());
    
    public static void main(String[] args) throws SQLException 
    {
        Config.getInstance().registerLogger(log);
        
        try
        {
            Conn.getInstance().testConnection();
        }
        catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
     
    }
}