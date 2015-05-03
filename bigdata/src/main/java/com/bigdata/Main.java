package com.bigdata;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.apache.commons.cli.ParseException;

import com.bigdata.analysis.Dataset;
import com.bigdata.data.OSData;
import com.bigdata.data.UniversalLoader;
import com.bigdata.data.YorkData;
import com.bigdata.utility.Config;
import com.bigdata.utility.Parameters;

public class Main 
{
    private static final Logger log = Logger.getLogger(Main.class.getName());
    
    public static void main(String[] args) throws SQLException, IOException, ParseException 
    {
        Config.getInstance().registerLogger(log);
        
//        int[][] a = new int[][]{{1,2},{3,4}};
        
//        System.out.println(a[0][0] + " " + a[0][1] + " " + a[1][0] + " " + a[1][1]);
        
//        System.exit(0);
        
        Parameters params = new Parameters(args);
        YorkData yd = new YorkData();
        yd.load();
        UniversalLoader loader = new UniversalLoader();
//        loader.load(DataSetType.BATTLEFIELDS);
//        loader.load(DataSetType.IMMUNITY);
//        loader.load(DataSetType.PARKS);
//        loader.load(DataSetType.MONUMENTS);
//        loader.load(DataSetType.HERITAGE_SITES);
//        loader.load(DataSetType.BUILDINGS);
        
        OSData os = new OSData();
        os.load("SU");
        
        Dataset d = new Dataset();
        d.setArea("SU");
        d.setOSData(os);
        d.setLoader(loader);
        d.setYorkData(yd);
        d.createTrainingSet();
        
        
        
    }
}