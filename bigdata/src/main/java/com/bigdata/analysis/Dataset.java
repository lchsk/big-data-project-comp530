package com.bigdata.analysis;

import com.bigdata.data.OSData;
import com.bigdata.data.UniversalLoader;
import com.bigdata.data.YorkData;
import com.bigdata.data.YorkDataItem;
import com.bigdata.utility.Common;

public class Dataset
{
    private YorkData yd;
    private OSData os;
    private UniversalLoader loader;
    private String area;
    
    public Dataset()
    {
        
    }
    
    public double getHeight(String area, int easting, int northing)
    {
        int sq = Common.getSquareSymbolInt(easting, northing);
        
        if (os.getData()[sq] != null)
        {
            double[][] d = os.getData()[sq].getData();
            int eID = (int) (Math.round(easting % 10000) / 50.0);
            int nID = 199 - (int) (Math.round(northing % 10000) / 50.0);
            
            double h = d[nID][eID];
            
            System.out.println(easting + " " + easting % 10000 + " " + eID);
            System.out.println(northing + " " + northing % 10000 + " " + nID);
            System.out.println(area + " : " + h);
            
            return h;
        }
        
        return -1;
    }
    
    public void createTrainingSet()
    {
        for (YorkDataItem s : yd.getData())
        {
            if (s.getMcode().equals(area))
            {
                getHeight(area, s.getEasting(), s.getNorthing());
                
            }
        }
    }
    
    public void setArea(String p_area)
    {
        area = p_area;
    }
    
    public void setOSData(OSData p_s)
    {
        os = p_s;
    }
    
    public void setYorkData(YorkData p_d)
    {
        yd = p_d;
    }
    
    public void setLoader(UniversalLoader p_l)
    {
        loader = p_l;
    }
}
