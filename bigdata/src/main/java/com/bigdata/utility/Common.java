package com.bigdata.utility;

import java.math.BigDecimal;

import com.bigdata.coords.EastingNorthing;
import com.bigdata.coords.LatitudeLongitude;

public class Common
{
    public static String rm(String s)
    {
        return s.replace("\"", "");
    }
    
    public static String[] parseNGR(String s)
    {
        String[] a = s.split(" ");
        
        if (a.length == 3)
            return a;
        else if (s.length() == 12)
        {
            String[] r = new String[3];
            
            r[0] = s.substring(0, 2);
            r[1] = s.substring(2, 7);
            r[2] = s.substring(7, 12);
            
            return r;
        }
        else
        {
            return null;
        }
    }
    
    public static double[] enToLatLon(String e, String n)
    {
        EastingNorthing en = new EastingNorthing(new BigDecimal(e).intValue(), new BigDecimal(n).intValue());
        LatitudeLongitude l = en.toLatitudeLongitude();
        
        double[] r = new double[2];
        r[0] = l.getLat();
        r[1] = l.getLon();
        
        return r;
    }
    
    /**
     * Hectares to Square Meters
     * 
     * @param ha
     * @return
     */
    public static double haToM2(String ha)
    {
        return new BigDecimal(ha).doubleValue() * 10000.0;
    }
}
