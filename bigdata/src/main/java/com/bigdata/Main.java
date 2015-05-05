package com.bigdata;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.apache.commons.cli.ParseException;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import com.bigdata.analysis.Dataset;
import com.bigdata.data.DataSetType;
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
        
        String[] areas = new String[]{
                // 7
                "HP", "HT", "HU", "HW", "HX", "HY", "HZ",
                
                //21
                "NA", "NB", "NC", "ND", "NF", "NG", "NH", "NJ", "NK", "NL", "NM", "NN", "NO", "NR", "NS", "NT", "NU", "NW", "NX", "NY", "NZ",
                
                // 1
                "OV",
                
                // 19
                "SC", "SD", "SE", "SH", "SJ", "SK", "SM", "SN", "SO", "SP", "SR", "SS", "ST", "SU", "SV", "SW", "SX", "SY", "SZ",
                
                // 8
                "TA", "TF", "TG", "TL", "TM", "TQ", "TR", "TV"
        };
        
        Parameters params = new Parameters(args);
        
        if (params.help())
        {
            
            System.exit(0);
        }
        
        if (params.generateTrainingData() != null || params.generateTestData() != null)
        {
            String area = "";
            
            if (params.generateTrainingData() != null)
                area = params.generateTrainingData();
            else if (params.generateTestData() != null)
                area = params.generateTestData();
            
            log.info("Loading data in progress...");
            
            YorkData yd = new YorkData();
            yd.load();
            UniversalLoader loader = new UniversalLoader();
            loader.load(DataSetType.PARKS);
            loader.load(DataSetType.MONUMENTS);
            loader.load(DataSetType.BUILDINGS);
            
            OSData os = new OSData();
            
            Dataset d = new Dataset();
            
            d.setOSData(os);
            d.setLoader(loader);
            d.setYorkData(yd);
            
            log.info("Data loaded.");
            
            if (area.equals("ALL"))
            {
                for (String a : areas)
                {
                    d.setArea(a);
                    os.load(a);
                    
                    if (params.generateTrainingData() != null)
                    {
                        log.info("Creating training data set for " + a);
                        d.createTrainingSet();
                    }
                    else if (params.generateTestData() != null)
                    {
                        log.info("Creating test data set for: " + a);
                        d.createTestSet();
                    }
                }
            }
            else
            {
                d.setArea(area);
                os.load(area);
                
                if (params.generateTrainingData() != null)
                {
                    log.info("Creating training data set for " + area);
                    d.createTrainingSet();
                }
                else if (params.generateTestData() != null)
                {
                    log.info("Creating test data set for: " + area);
                    d.createTestSet();
                }
            }
        }
    }
}