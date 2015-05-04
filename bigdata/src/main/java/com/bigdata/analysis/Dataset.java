package com.bigdata.analysis;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

import com.bigdata.data.DataSetType;
import com.bigdata.data.OSData;
import com.bigdata.data.OSDataSingle;
import com.bigdata.data.UniversalItem;
import com.bigdata.data.UniversalLoader;
import com.bigdata.data.YorkData;
import com.bigdata.data.YorkDataItem;
import com.bigdata.utility.Common;

public class Dataset
{
    private final Logger log = Logger.getLogger(UniversalLoader.class.getName());
    
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
        
        File output = new File("train_" + area + ".csv");
        
        int counter = 1;
        
        try 
        {
            writer = new BufferedWriter(new FileWriter(output));
            writer.write("arch,height,distM,distP,distB,countM,countP,countB\n");
            
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
                }
                counter++;
            }
            
            log.info("Output saved to " + output.getCanonicalPath());
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
    
    public ArrayList<EastingNorthingPoint> getAllPoints(int squareID, double baseX, double baseY)
    {
        ArrayList<EastingNorthingPoint> points = new ArrayList<EastingNorthingPoint>();
        
//        int easting = os.getEastingModifier(s.getIterator()) + 
        
        
        double distance = 1;
        double jump = 50. / distance;
        
        for (int i = 0; i < 200; i += jump)
        {
            for (int j = 0; j < 200; j += jump)
            {
//                System.out.println("SQUAREID: " + squareID);
                int e = (int) Common.shiftFromCorner(baseX, i, 50);
                int n = (int) Common.shiftFromCorner(baseY, j, 50);
                String eastingMod = String.valueOf(os.getEastingModifier(squareID));
                String northingMod = String.valueOf(os.getNorthingModifier(squareID));
                String easting = eastingMod + String.valueOf(j * 50);
                String northing = northingMod + String.valueOf(i * 50);
                double[] latlon = Common.enToLatLon(String.valueOf(e), String.valueOf(n));
                
//                System.out.println(eastingMod + " " + northingMod + " " + easting + " " + northing);
                
                EastingNorthingPoint p = new EastingNorthingPoint(
                        Integer.valueOf(easting), Integer.valueOf(northing),
                        e, n,
                        latlon[0], latlon[1]
                        );
                points.add(p);
            }
        }
        
        return points;
    }
    
    public void createTestSet()
    {
        BufferedWriter writer = null;
        
        File output = new File("test_" + area + ".csv");
        
        int counter = 1;
        
        try 
        {
            writer = new BufferedWriter(new FileWriter(output));
            writer.write("e,n,lat,lon,height,distM,distP,distB,countP\n");
            
//            for (YorkDataItem s : yd.getData())
//            int it = 0;
            for (OSDataSingle s : os.getData())
            {
                if (s != null)
                {
                    
                    
                    System.out.println("Item: " + counter + " / " + os.getData().length + " (" + Math.round((float)counter / os.getData().length) + "%)");
                    
                    ArrayList<EastingNorthingPoint> points = getAllPoints(counter - 1, s.getXcorner(), s.getYcorner());

                    //for (EastingNorthingPoint p : points)
                    {
                        //System.out.println(p.getEasting() + " " + p.getNorthing() + " " + p.getE() + " " + p.getN() + " " + p.getLat() + " " + p.getLon());
                    }
                    
//                    System.exit(0);
                    
                    for (EastingNorthingPoint p : points)
                    {
//                        double[] latlon = Common.enToLatLon(String.valueOf(p.getEasting()), String.valueOf(p.getNorthing()));
                        
                        double height = getHeight(area, p.getEasting(), p.getNorthing());
                        
                        double distM = getMinDistanceFromPlace(DataSetType.MONUMENTS, area, p.getEasting(), p.getNorthing());
                        double distP = getMinDistanceFromPlace(DataSetType.PARKS, area, p.getEasting(), p.getNorthing());
                        double distB = getMinDistanceFromPlace(DataSetType.BUILDINGS, area, p.getEasting(), p.getNorthing());
                        
                       // double countM = countObjectsWithinRadius(DataSetType.MONUMENTS, area, p.getEasting(), p.getNorthing(), 5000);
                        double countP = countObjectsWithinRadius(DataSetType.PARKS, area, p.getEasting(), p.getNorthing(), 5000);
                       // double countB = countObjectsWithinRadius(DataSetType.BUILDINGS, area, p.getEasting(), p.getNorthing(), 5000);
//                        System.out.println(countM + " " + countP + " " + countB);
                        writer.write(
                                p.getE() +
                                "," + p.getN() +
                                "," + p.getLat() +
                                "," + p.getLon() +
                                "," + height + 
                                "," + distM + 
                                "," + distP + 
                                "," + distB + 
                                "," + countP + 
                                "\n");
                    }
                }
                counter++;
                
            }
            
            log.info("Test output saved to " + output.getCanonicalPath());
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
