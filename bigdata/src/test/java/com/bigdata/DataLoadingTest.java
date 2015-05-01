package com.bigdata;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.bigdata.data.DataSetType;
import com.bigdata.data.UniversalLoader;

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
}
