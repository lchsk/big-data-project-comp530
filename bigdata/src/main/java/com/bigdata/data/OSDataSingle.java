package com.bigdata.data;

public class OSDataSingle
{
    private double[][] data;
    private int iterator;
    
    public OSDataSingle()
    {
        iterator = 0;
        data = new double[200][200];
        
        for (int i = 0; i < 200; i++)
        {
            for (int j = 0; j < 200; j++)
            {
                data[i][j] = -100;
            }
        }
        
//        System.out.println(data[0][0]);
    }
    
    public void addLine(String[] line)
    {
//        System.out.println(line.length + ": " + iterator);
//        System.arraycopy(line, 0, data[iterator], 0, line.length);
        
        for (int i = 0; i < 200; i++)
        {
            data[iterator][i] = Double.parseDouble(line[i]);
        }
        
        iterator++;
    }
    
    public boolean hasData()
    {
        for (int i = 0; i < 200; i++)
        {
            for (int j = 0; j < 200; j++)
            {
                if (data[i][j] <= -99)
                    return false;
            }
        }
        
        return true;
    }
}
