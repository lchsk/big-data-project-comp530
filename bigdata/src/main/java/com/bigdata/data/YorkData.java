package com.bigdata.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.bigdata.coords.EastingNorthing;
import com.bigdata.coords.LatitudeLongitude;
import com.bigdata.utility.Common;
import com.bigdata.utility.Config;

public class YorkData
{
    private final Logger log = Logger.getLogger(YorkData.class.getName());
    
    private String path;
    
//    private HashMap<String, ArrayList<YorkDataItem>> data;
    private ArrayList<YorkDataItem> data;
    
    public YorkData()
    {
        path = Config.getInstance().getSetting("york_path");
//        data = new HashMap<String, ArrayList<YorkDataItem>>();
        data = new ArrayList<YorkDataItem>();
    }
    
    public void load()
    {
        BufferedReader reader = null; 
        try
        {
            reader = new BufferedReader(new FileReader(path));
            
            String line;
            while (true)
            {
                line = reader.readLine();
                
                if (line != null && (line.isEmpty() || line.equals("") || line.equals("\n")))
                    continue;
                
                if (line == null)
                    break;
                
                String[] d = Common.rm(line).split(",");
                
                // 0 Positive/Negative
                // 1 ID
                // 2 mcode
                // 3 easting
                // 4 northing
                // 5 easting_offset
                // 6 northing_offset
                // 7 e
                // 8 n
                
                if ( ! d[0].isEmpty() && d.length == 9)
                {
                    EastingNorthing e = new EastingNorthing(new BigDecimal(d[7]).intValue(), new BigDecimal(d[8]).intValue());
                    LatitudeLongitude l = e.toLatitudeLongitude();
                    
//                    if (new BigDecimal(d[2]).intValue() > 10000 || new BigDecimal(d[3]).intValue() > 10000)
                    {
                        data.add(
                                new YorkDataItem(
                                      new BigDecimal(d[0]).intValue(),
                                      new BigDecimal(d[1]).intValue(), 
                                      d[2], 
                                      new BigDecimal(d[3]).intValue(), 
                                      new BigDecimal(d[4]).intValue(), 
                                      new BigDecimal(d[5]).intValue(), 
                                      new BigDecimal(d[6]).intValue(), 
                                      new BigDecimal(d[7]).intValue(), 
                                      new BigDecimal(d[8]).intValue(), 
                                      l.getLat(), 
                                      l.getLon()
                                      )
                                );
                    }
//                    else
                    {
                    }
                }
            }
            reader.close();
        } 
        catch (FileNotFoundException e)
        {
            log.severe("File " + path + " not found.");
        }
        catch (Exception e)
        {
            log.severe("Error reading file " + path);
            e.printStackTrace();
        }
    }

    public ArrayList<YorkDataItem> getData()
    {
        return data;
    }
    
    
}
