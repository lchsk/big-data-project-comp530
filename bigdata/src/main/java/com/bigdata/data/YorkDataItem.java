package com.bigdata.data;

public class YorkDataItem
{
    private int id;
    private String mcode;
    private int easting;
    private int northing;
    private int easting_offset;
    private int northing_offset;
    private int e;
    private int n;
    
    private double lat;
    private double lon;
    
    public YorkDataItem(
            int p_id, 
            String p_mcode, 
            int p_easting, 
            int p_northing, 
            int p_easting_offset, 
            int p_northing_offset, 
            int p_e, 
            int p_n, 
            double p_lat, 
            double p_lon
            )
    {
        id = p_id;
        mcode = p_mcode;
        easting = p_easting;
        northing = p_northing;
        easting_offset = p_easting_offset;
        northing_offset = p_northing_offset;
        e = p_e;
        n = p_n;
        lat = p_lat;
        lon = p_lon;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getMcode()
    {
        return mcode;
    }

    public void setMcode(String mcode)
    {
        this.mcode = mcode;
    }

    public int getEasting()
    {
        return easting;
    }

    public void setEasting(int easting)
    {
        this.easting = easting;
    }

    public int getNorthing()
    {
        return northing;
    }

    public void setNorthing(int northing)
    {
        this.northing = northing;
    }

    public int getEasting_offset()
    {
        return easting_offset;
    }

    public void setEasting_offset(int easting_offset)
    {
        this.easting_offset = easting_offset;
    }

    public int getNorthing_offset()
    {
        return northing_offset;
    }

    public void setNorthing_offset(int northing_offset)
    {
        this.northing_offset = northing_offset;
    }

    public int getE()
    {
        return e;
    }

    public void setE(int e)
    {
        this.e = e;
    }

    public int getN()
    {
        return n;
    }

    public void setN(int n)
    {
        this.n = n;
    }

    public double getLat()
    {
        return lat;
    }

    public void setLat(double lat)
    {
        this.lat = lat;
    }

    public double getLon()
    {
        return lon;
    }

    public void setLon(double lon)
    {
        this.lon = lon;
    }
    
    
    
    
}
