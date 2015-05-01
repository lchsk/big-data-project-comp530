package com.bigdata;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.bigdata.data.DataSetType;
import com.bigdata.data.UniversalLoader;
import com.bigdata.utility.Common;

public class DataLoadingTest extends TestCase
{
    public DataLoadingTest(String testName)
    {
        super(testName);
    }

    public static Test suite()
    {
        return new TestSuite(DataLoadingTest.class);
    }

    public void testLoading()
    {
        UniversalLoader loader = new UniversalLoader();
        loader.load(DataSetType.BATTLEFIELDS);
        loader.load(DataSetType.IMMUNITY);
        loader.load(DataSetType.PARKS);
        loader.load(DataSetType.MONUMENTS);
        loader.load(DataSetType.HERITAGE_SITES);
        loader.load(DataSetType.BUILDINGS);
        
        assertEquals(46, loader.getdBattlefields().size());
        assertEquals(158, loader.getdImmunity().size());
        assertEquals(1634, loader.getdParks().size());
        assertEquals(19854, loader.getdMonuments().size());
        assertEquals(283, loader.getdHeritageSites().size());
        assertEquals(376123, loader.getdBuildings().size());
    }
    
    public void testLanLonConverter()
    {
        double delta = 0.0001;
        double[] a1 = Common.enToLatLon("513794", "164777");
        
        assertEquals(51.370660, a1[0], delta);
        assertEquals(-0.36662780, a1[1], delta);
    }
    
    public void testHectaresToMeters2()
    {
        double delta = 0.0001;
        double ha1 = Common.haToM2("51.24");
        double ha2 = Common.haToM2("0.156151");
        
        assertEquals(512400, ha1, delta);
        assertEquals(1561.51, ha2, delta);
    }
    
    public void testNGR()
    {
        String s1[] = new String[]{"SP", "58699", "34779"};
        String[] s1_ = Common.parseNGR("SP5869934779");
        
        String s2[] = new String[]{"SP", "58699", "34779"};
        String[] s2_ = Common.parseNGR("SP 58699 34779");
        
        String[] s3_ = Common.parseNGR("Only text here");
        
        String[] s4_ = Common.parseNGR("Unknown");
        
        assertEquals(s1[0], s1_[0]);
        assertEquals(s1[1], s1_[1]);
        assertEquals(s1[2], s1_[2]);
        
        assertEquals(s2[0], s2_[0]);
        assertEquals(s2[1], s2_[1]);
        assertEquals(s2[2], s2_[2]);
        
        assertEquals(null, s3_);
        
        assertEquals(null, s4_);
        
    }
}
