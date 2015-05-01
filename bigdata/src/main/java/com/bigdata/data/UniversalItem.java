package com.bigdata.data;

import java.math.BigDecimal;

public class UniversalItem
{
    private String ngr;
    private double e;
    private double n;
    private double area_ha;
    private double area_m;
    private String mcode;
    private double easting;
    private double northing;
    private double lon;
    private double lat;
    
    
    
    public String getNgr()
    {
        return ngr;
    }
    public void setNgr(String ngr)
    {
        this.ngr = ngr;
    }
    public double getE()
    {
        return e;
    }
    public void setE(String e)
    {
        this.e = new BigDecimal(e).doubleValue();
    }
    public double getN()
    {
        return n;
    }
    public void setN(String n)
    {
        this.n = new BigDecimal(n).doubleValue();
    }
    public double getArea_ha()
    {
        return area_ha;
    }
    public void setArea_ha(String area_ha)
    {
        this.area_ha = new BigDecimal(area_ha).doubleValue();
    }
    public double getArea_m()
    {
        return area_m;
    }
    public void setArea_m(double area_m)
    {
        this.area_m = area_m;
    }
    public String getMcode()
    {
        return mcode;
    }
    public void setMcode(String mcode)
    {
        this.mcode = mcode;
    }
    public double getEasting()
    {
        return easting;
    }
    public void setEasting(String easting)
    {
        this.easting = new BigDecimal(easting).doubleValue();
    }
    public double getNorthing()
    {
        return northing;
    }
    public void setNorthing(String northing)
    {
        this.northing = new BigDecimal(northing).doubleValue();
    }
    public double getLon()
    {
        return lon;
    }
    public void setLon(double lon)
    {
        this.lon = lon;
    }
    public double getLat()
    {
        return lat;
    }
    public void setLat(double lat)
    {
        this.lat = lat;
    }
    
    
}
