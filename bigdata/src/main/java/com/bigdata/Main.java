package com.bigdata;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.apache.commons.cli.ParseException;

import com.bigdata.data.DataSetType;
import com.bigdata.data.OSData;
import com.bigdata.data.UniversalLoader;
import com.bigdata.utility.Config;
import com.bigdata.utility.Parameters;

public class Main 
{
    private static final Logger log = Logger.getLogger(Main.class.getName());
    
    public static void main(String[] args) throws SQLException, IOException, ParseException 
    {
        Config.getInstance().registerLogger(log);
        
        Parameters params = new Parameters(args);
//        YorkData d = new YorkData();
//        d.load();
        UniversalLoader loader = new UniversalLoader();
//        loader.load(DataSetType.BATTLEFIELDS);
//        loader.load(DataSetType.IMMUNITY);
//        loader.load(DataSetType.PARKS);
//        loader.load(DataSetType.MONUMENTS);
//        loader.load(DataSetType.HERITAGE_SITES);
//        loader.load(DataSetType.BUILDINGS);
        
        OSData os = new OSData();
        os.load("NO");
        
        
    }
}