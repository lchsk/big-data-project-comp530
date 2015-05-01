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

public class UniversalLoader
{
    private final Logger log = Logger.getLogger(UniversalLoader.class.getName());
    
    private DataSetType type;
    
    private String path;
    
    private String delimiter = "\t";
    
    // number of fields in this dataset
    private int fields;
    
    private ArrayList<UniversalItem> dBattlefields = null;
    private ArrayList<UniversalItem> dImmunity = null;
    private ArrayList<UniversalItem> dParks = null;
    private ArrayList<UniversalItem> dMonuments = null;
    private ArrayList<UniversalItem> dHeritageSites = null;
    private ArrayList<UniversalItem> dBuildings = null;
    
    // Dataset paths
    private final String pathBattlefields = Config.getInstance().getSetting("battlefields_path");
    private final String pathImmunity = Config.getInstance().getSetting("immunity_path");
    private final String pathParks = Config.getInstance().getSetting("parks_path");
    private final String pathMonuments = Config.getInstance().getSetting("monuments_path");
    private final String pathHeritageSites = Config.getInstance().getSetting("worldheritage_path");
    private final String pathBuildings = Config.getInstance().getSetting("buildings_path");
    
    // # Fields
    private final int fieldsBattlefields = 10;
    private final int fieldsImmunity = 9;
    private final int fieldsParks = 11;
    
    // doesnt matter
    private final int fieldsMonuments = 9;
    
    private final int fieldsHeritageSites = 11;
    
    // doesnt matter
    private final int fieldsBuildings = 9;
    
    public UniversalLoader()
    {
        dBattlefields = new ArrayList<UniversalItem>();
        dImmunity = new ArrayList<UniversalItem>();
        dBuildings = new ArrayList<UniversalItem>();
        dHeritageSites = new ArrayList<UniversalItem>();
        dMonuments = new ArrayList<UniversalItem>();
        dParks = new ArrayList<UniversalItem>();
        
    }
    
    private void init()
    {
        switch (type)
        {
            case BATTLEFIELDS:
                path = pathBattlefields;
                fields = fieldsBattlefields;
                break;
            case IMMUNITY:
                path = pathImmunity;
                fields = fieldsImmunity;
                break;
            case PARKS:
                path = pathParks;
                fields = fieldsParks;
                break;
            case MONUMENTS:
                path = pathMonuments;
                fields = fieldsMonuments;
                break;
            case BUILDINGS:
                path = pathBuildings;
                fields = fieldsBuildings;
                break;
            case HERITAGE_SITES:
                path = pathHeritageSites;
                fields = fieldsHeritageSites;
                break;
        }
    }
    
    public void load(DataSetType p_type)
    {
        type = p_type;
        
        init();
        
        BufferedReader reader = null; 
        String[] ngr;
        double [] p;
        UniversalItem b = null;
        
        String line = null;
        int counter = 0;
        
        try
        {
            reader = new BufferedReader(new FileReader(path));
            
            while (true)
            {
                counter++;
                line = reader.readLine();
                
                // skip the first one
                if (counter == 1) continue;
                
                if (line != null && (line.isEmpty() || line.equals("") || line.equals("\n")))
                    continue;
                
                if (line == null)
                    break;
                
                String[] d = Common.rm(line).split(delimiter);
                
//                if ( ! d[0].isEmpty())
                {
                    switch(type)
                    {
                        case BATTLEFIELDS:
                            
                            // Mcode, easting, northing
                            ngr = Common.parseNGR(d[5]);
                            // 7: e, 8: n, 9: area
                            
                            b = new UniversalItem();
                            
                            p = Common.enToLatLon(d[7], d[8]);
                            
                            b.setArea_m(Common.haToM2(d[9]));
                            b.setArea_ha(d[9]);
                            b.setE(d[7]);
                            b.setN(d[8]);
                            
                            b.setMcode(ngr[0]);
                            b.setEasting(ngr[1]);
                            b.setNorthing(ngr[2]);
                            
                            b.setLat(p[0]);
                            b.setLon(p[1]);
                            
                            dBattlefields.add(b);
                            
                            break;
                            
                        case IMMUNITY:
                            
                            ngr = Common.parseNGR(d[5]);
                            p = Common.enToLatLon(d[7], d[8]);
                            
                            b = new UniversalItem();
                            
                            b.setE(d[7]);
                            b.setN(d[8]);
                            
                            b.setMcode(ngr[0]);
                            b.setEasting(ngr[1]);
                            b.setNorthing(ngr[2]);
                            
                            b.setLat(p[0]);
                            b.setLon(p[1]);
                            
                            dImmunity.add(b);
                            
                            break;
                            
                        case PARKS:
                            
                            ngr = Common.parseNGR(d[6]);
                            b = new UniversalItem();
                            p = Common.enToLatLon(d[8], d[9]);
                            
                            b.setArea_m(Common.haToM2(d[10]));
                            b.setArea_ha(d[10]);
                            
                            b.setE(d[8]);
                            b.setN(d[9]);
                            
                            b.setMcode(ngr[0]);
                            b.setEasting(ngr[1]);
                            b.setNorthing(ngr[2]);
                            
                            b.setLat(p[0]);
                            b.setLon(p[1]);
                            
                            dParks.add(b);
                            
                            break;
                            
                        case MONUMENTS:
                            if (d.length == 9)
                            {
                                ngr = Common.parseNGR(d[4]);
                                b = new UniversalItem();
                                p = Common.enToLatLon(d[6], d[7]);
                                
                                b.setArea_m(Common.haToM2(d[8]));
                                b.setArea_ha(d[8]);
                                
                                b.setE(d[6]);
                                b.setN(d[7]);
                                
                                b.setMcode(ngr[0]);
                                b.setEasting(ngr[1]);
                                b.setNorthing(ngr[2]);
                                
                                b.setLat(p[0]);
                                b.setLon(p[1]);
                                
                                dMonuments.add(b);
                            }
                            else if (d.length == 10)
                            {
                                ngr = Common.parseNGR(d[5]);
                                
                                if (ngr != null)
                                {
                                    b = new UniversalItem();
                                    
                                    p = Common.enToLatLon(d[7], d[8]);
                                    
                                    b.setArea_m(Common.haToM2(d[9]));
                                    b.setArea_ha(d[9]);
                                    
                                    b.setE(d[7]);
                                    b.setN(d[8]);
                                    
                                    b.setMcode(ngr[0]);
                                    b.setEasting(ngr[1]);
                                    b.setNorthing(ngr[2]);
                                    
                                    b.setLat(p[0]);
                                    b.setLon(p[1]);
                                    
                                    dMonuments.add(b);
                                }
                            }                
                            
                            
                            break;
                            
                        case HERITAGE_SITES:
                            ngr = Common.parseNGR(d[6]);
                            b = new UniversalItem();
                            p = Common.enToLatLon(d[8], d[9]);
                            
                            b.setArea_m(Common.haToM2(d[10]));
                            b.setArea_ha(d[10]);
                            b.setE(d[8]);
                            b.setN(d[9]);
//                            
                            b.setMcode(ngr[0]);
                            b.setEasting(ngr[1]);
                            b.setNorthing(ngr[2]);
//                            
                            b.setLat(p[0]);
                            b.setLon(p[1]);
//                            
                            dHeritageSites.add(b);
                            
                            break;
                            
                        case BUILDINGS:
                            
                            if (d.length == 9)
                            {
                                ngr = Common.parseNGR(d[5]);
                                p = Common.enToLatLon(d[7], d[8]);
                                b = new UniversalItem();
                                b.setE(d[7]);
                                b.setN(d[8]);
                                  
                                b.setMcode(ngr[0]);
                                b.setEasting(ngr[1]);
                                b.setNorthing(ngr[2]);
                                  
                                b.setLat(p[0]);
                                b.setLon(p[1]);
  
                                dBuildings.add(b);
                            }
                            
                            else if (d.length == 10)
                            {
                                ngr = Common.parseNGR(d[6]);
                                p = Common.enToLatLon(d[8], d[9]);
                                b = new UniversalItem();
                                b.setE(d[8]);
                                b.setN(d[9]);
//                                  
                                b.setMcode(ngr[0]);
                                b.setEasting(ngr[1]);
                                b.setNorthing(ngr[2]);
//                                  
                                b.setLat(p[0]);
                                b.setLon(p[1]);
//  
                                dBuildings.add(b);
                            }
                            
                            break;
                    }
                    
//                    EastingNorthing e = new EastingNorthing(new BigDecimal(d[6]).intValue(), new BigDecimal(d[7]).intValue());
//                    LatitudeLongitude l = e.toLatitudeLongitude();
//                    
//                    data.add(
//                            new YorkDataItem(
//                                  new BigDecimal(d[0]).intValue(), 
//                                  d[1], 
//                                  new BigDecimal(d[2]).intValue(), 
//                                  new BigDecimal(d[3]).intValue(), 
//                                  new BigDecimal(d[4]).intValue(), 
//                                  new BigDecimal(d[5]).intValue(), 
//                                  new BigDecimal(d[6]).intValue(), 
//                                  new BigDecimal(d[7]).intValue(), 
//                                  l.getLat(), 
//                                  l.getLon()
//                                  )
//                            );
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
            log.severe(line);
            e.printStackTrace();
        }
    }

    public ArrayList<UniversalItem> getdBattlefields()
    {
        return dBattlefields;
    }

    public ArrayList<UniversalItem> getdImmunity()
    {
        return dImmunity;
    }

    public ArrayList<UniversalItem> getdParks()
    {
        return dParks;
    }

    public ArrayList<UniversalItem> getdMonuments()
    {
        return dMonuments;
    }

    public ArrayList<UniversalItem> getdHeritageSites()
    {
        return dHeritageSites;
    }

    public ArrayList<UniversalItem> getdBuildings()
    {
        return dBuildings;
    }
    
    
}
