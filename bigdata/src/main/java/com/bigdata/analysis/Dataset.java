package com.bigdata.analysis;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

import com.bigdata.data.DataSetType;
import com.bigdata.data.OSData;
import com.bigdata.data.UniversalItem;
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
    
    private Random r;
    
    public Dataset()
    {
        r = new Random();
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
            
//            System.out.println(easting + " " + easting % 10000 + " " + eID);
//            System.out.println(northing + " " + northing % 10000 + " " + nID);
//            System.out.println(area + " : " + h);
            
            return h;
        }
        
        return -1;
    }
    
    public ArrayList<UniversalItem> getDataset(DataSetType t)
    {
        switch (t)
        {
            case BATTLEFIELDS:
                return loader.getdBattlefields();
            case IMMUNITY:
                return loader.getdImmunity();
            case PARKS:
                return loader.getdParks();
            case MONUMENTS:
                return loader.getdMonuments();
            case BUILDINGS:
                return loader.getdBuildings();
            case HERITAGE_SITES:
                return loader.getdHeritageSites();
        }
        
        return null;
    }
    
    public double countObjectsWithinRadius(DataSetType t, String area, int easting, int northing, int radius)
    {
        int count = 0;
        ArrayList<UniversalItem> d = getDataset(t);
        
        if (d != null)
        {
            for (UniversalItem i : d)
            {
                if (area.equals(i.getMcode()))
                {
                    double e = i.getEasting();
                    double n = i.getNorthing();
                    
                    double dist = Common.distance(e, n, easting, northing);
                    
                    if (dist < radius)
                        count++;
                }
            }
        }
        
        return count;
    }
    
    public double getMinDistanceFromPlace(DataSetType t, String area, int easting, int northing)
    {
        double min = 9999999;
        ArrayList<UniversalItem> d = getDataset(t);
        
//        switch (t)
//        {
//            case BATTLEFIELDS:
//                d = loader.getdBattlefields();
//                break;
//            case IMMUNITY:
//                d = loader.getdImmunity();
//                break;
//            case PARKS:
//                d = loader.getdParks();
//                break;
//            case MONUMENTS:
//                d = loader.getdMonuments();
//                break;
//            case BUILDINGS:
//                d = loader.getdBuildings();
//                break;
//            case HERITAGE_SITES:
//                d = loader.getdHeritageSites();
//                break;
//        }
        
        for (UniversalItem i : d)
        {
            if (area.equals(i.getMcode()))
            {
                double e = i.getEasting();
                double n = i.getNorthing();
                
                double dist = Common.distance(e, n, easting, northing);
                
                if (dist < min)
                    min = dist;
            }
        }
        
        return min;
    }
    
    public void createTrainingSet()
    {
        BufferedWriter writer = null;
      //create a temporary file
        File output = new File("out.csv");
        
        int counter = 1;
        
        try 
        {
            writer = new BufferedWriter(new FileWriter(output));
            writer.write("arch,height,distM,distP,distB,countM,countP,countB\n");
//            writer.write("arch,x,y\n");
            
            for (YorkDataItem s : yd.getData())
            {
                System.out.println("Item: " + counter + " / " + yd.getData().size() + " (" + Math.round((float)counter / yd.getData().size()) + "%)");
                
                if (s.getMcode().equals(area))
                {
                    double height = getHeight(area, s.getEasting(), s.getNorthing());
                    
                    double distM = getMinDistanceFromPlace(DataSetType.MONUMENTS, area, s.getEasting(), s.getNorthing());
                    double distP = getMinDistanceFromPlace(DataSetType.PARKS, area, s.getEasting(), s.getNorthing());
                    double distB = getMinDistanceFromPlace(DataSetType.BUILDINGS, area, s.getEasting(), s.getNorthing());
                    
                    double countM = countObjectsWithinRadius(DataSetType.MONUMENTS, area, s.getEasting(), s.getNorthing(), 5000);
                    double countP = countObjectsWithinRadius(DataSetType.PARKS, area, s.getEasting(), s.getNorthing(), 5000);
                    double countB = countObjectsWithinRadius(DataSetType.BUILDINGS, area, s.getEasting(), s.getNorthing(), 5000);
                    
                    writer.write(s.getMark() + 
                            "," + height + 
                            "," + distM + 
                            "," + distP + 
                            "," + distB + 
                            "," + countM + 
                            "," + countP + 
                            "," + countB + 
                            "\n");
//                    System.out.println(distM + " " + distP + " " + distB);
//                    writer.write(s.getMark() + "," + s.getEasting() / 100000.0 + "," + s.getNorthing() / 100000.0 + "\n");
                }
                counter++;
            }
            
            System.out.println("Output saved to " + output.getCanonicalPath());
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        } 
        finally 
        {
            try 
            {
                writer.close();
            } 
            catch (Exception e) 
            {
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
