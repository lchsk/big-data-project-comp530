package com.bigdata;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.apache.commons.cli.ParseException;

import com.bigdata.data.DataSetType;
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
        loader.load(DataSetType.BUILDINGS);
       
//        String hive = Config.getInstance().getSetting("hive_home") + "bin/hive";
        
//        System.out.println(hive);
//        try
        {
//            Runtime.getRuntime().exec(new String[]{"/home/lchsk/projects/uni_1415_2/comp530/bigdata/run.sh"});
//            Runtime.getRuntime().exec(new String[]{hive, "-f", 
//                    "/home/lchsk/projects/uni_1415_2/comp530/bigdata/sql/createTablesAll_DDL.sql"});
        }
//        catch (IOException e1)
        {
//            e1.printStackTrace();
        }
        
//        Conn.getInstance().run();
        
       
        
//        ProcessBuilder hiveProcessBuilder = new ProcessBuilder(hive, "-f",
//                "/home/lchsk/projects/uni_1415_2/comp530/bigdata/sql/createSchema.sql");
//        String path = processEnv.get("PATH");
//        Process hiveProcess = hiveProcessBuilder.start();

//        OutputRedirector outRedirect = new OutputRedirector(
//                hiveProcess.getInputStream(), "HIVE_OUTPUT");
//        OutputRedirector outToConsole = new OutputRedirector(
//                hiveProcess.getErrorStream(), "HIVE_LOG");

//        outRedirect.start();
//        outToConsole.start();
        
        
        
        System.exit(0);
        
//        try
//        {
//            Conn.getInstance().testConnection();
//        }
//        catch (UnknownHostException e)
//        {
//            e.printStackTrace();
//        }
     
    }
}