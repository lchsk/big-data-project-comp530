package com.bigdata;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import com.bigdata.analysis.Dataset;
import com.bigdata.data.DataSetType;
import com.bigdata.data.OSData;
import com.bigdata.data.OSDataSingle;
import com.bigdata.data.UniversalLoader;
import com.bigdata.data.YorkData;
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
        
        String[] s5_ = Common.parseNGR("testHMMM1234");
        
        assertEquals(s1[0], s1_[0]);
        assertEquals(s1[1], s1_[1]);
        assertEquals(s1[2], s1_[2]);
        
        assertEquals(s2[0], s2_[0]);
        assertEquals(s2[1], s2_[1]);
        assertEquals(s2[2], s2_[2]);
        
        assertEquals(null, s3_);
        assertEquals(null, s4_);
        assertEquals(null, s5_);
        
    }
    
    /**
     * Tests whether correct number of files were loaded.
     */
    public void testOS()
    {
        OSData os = new OSData();
        os.load("NN");
        assertEquals(100, os.count());
        os.load("NO");
        assertEquals(80, os.count());
    }
    
    /**
     * Tests whether all loaded data files have all fields.
     */
    public void testOSData()
    {
        OSData os = new OSData();
        os.load("NN");
        assertEquals(true, os.isDataLoaded());
        
        os.load("NO");
        assertEquals(true, os.isDataLoaded());
        
        os.load("SU");
        assertEquals(true, os.isDataLoaded());
        
        os.load("HZ");
        assertEquals(true, os.isDataLoaded());
        
        os.load("TM");
        assertEquals(true, os.isDataLoaded());
    }
    
    public void testOSMetadata()
    {
        OSData os = new OSData();
        os.load("NN");
        
        OSDataSingle[] s = os.getData();
        
        for (int i = 0; i < s.length; i++)
        {
            if (s[i] != null)
            {
                assertEquals(200, s[i].getRows());
                assertEquals(200, s[i].getCols());
                assertEquals(50, s[i].getCellsize());
                assertNotEquals(-1., s[i].getXcorner(), 0.01);
                assertNotEquals(-1., s[i].getYcorner(), 0.01);
            }
        }
    }
    
    public void testFillZeroes()
    {
        assertEquals("00001", Common.fillZeroes(1));
        assertEquals("00021", Common.fillZeroes(21));
        assertEquals("00411", Common.fillZeroes(411));
        assertEquals("04521", Common.fillZeroes(4521));
        assertEquals("41021", Common.fillZeroes(41021));
    }
    
    public void testSquareSymbols()
    {
        assertEquals("01", Common.getSquareSymbol(241, 10942));
        assertEquals("90", Common.getSquareSymbol(98105, 00047));
        assertEquals("90", Common.getSquareSymbol(98105, 47));
        assertEquals("49", Common.getSquareSymbol(41299, 91251));
        assertEquals("00", Common.getSquareSymbol(44, 99));
        assertEquals("99", Common.getSquareSymbol(99999, 99999));
    }
    
    public void testHeightEastingNorthing()
    {
        YorkData yd = new YorkData();
        yd.load();
        
        OSData os = new OSData();
        os.load("SU");
        
        Dataset d = new Dataset();
        d.setArea("SU");
        d.setOSData(os);
        d.setYorkData(yd);
        
        assertEquals(18.1, d.getHeight("SU", 0, 0));
        assertEquals(12.4, d.getHeight("SU", 9999, 0));
        assertEquals(74.0, d.getHeight("SU", 0, 9999));
        assertEquals(67.8, d.getHeight("SU", 9999, 9999));
        
        assertEquals(6.4, d.getHeight("SU", 90000, 0));
        assertEquals(-1.7, d.getHeight("SU", 99999, 0));
        assertEquals(119.5, d.getHeight("SU", 90000, 9999));
        assertEquals(133.8, d.getHeight("SU", 99999, 9999));
        
        assertEquals(101.8, d.getHeight("SU", 90000, 90000));
        assertEquals(76.2, d.getHeight("SU", 99999, 90000));
        assertEquals(122.9, d.getHeight("SU", 90000, 99999));
        assertEquals(138.2, d.getHeight("SU", 99999, 99999));
    }
}
