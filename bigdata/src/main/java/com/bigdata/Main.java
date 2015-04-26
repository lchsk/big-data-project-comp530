package com.bigdata;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.apache.commons.cli.ParseException;

import com.bigdata.data.YorkData;
import com.bigdata.utility.Config;
import com.bigdata.utility.Conn;
import com.bigdata.utility.Parameters;

public class Main 
{
    private static final Logger log = Logger.getLogger(Main.class.getName());
    
    public static void main(String[] args) throws SQLException, ParseException 
    {
        Parameters params = new Parameters(args);
        YorkData d = new YorkData();
        d.load();
        
        Config.getInstance().registerLogger(log);
        
        System.exit(0);
        
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