package com.bigdata.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Singleton class that holds all configuration.
 */
public class Config
{
    private static Config instance = null;

    // logging settings
    private FileHandler logFile;
    private final Logger log = Logger.getLogger(Config.class.getName());
    private final String logFileExtension = ".log";
    private final String logDirectory = "log/";

    public static Config getInstance()
    {
        if (instance == null)
        {
            synchronized (Config.class)
            {
                if (instance == null)
                {
                    instance = new Config();
                }
            }
        }

        return instance;
    }

    private Config()
    {
        try
        {
            // set up a log file
            logFile = new FileHandler(logDirectory + getLogFilename(), false);
            logFile.setFormatter(new SimpleFormatter());
            registerLogger(log);

        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    /*
     * Adds logger from another class to the same logfile.
     */
    public void registerLogger(Logger p_logger)
    {
        p_logger.addHandler(logFile);
    }

    /*
     * Generates a new log file filename (based on current date and time).
     */
    public String getLogFilename()
    {
        String dateString = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
        return dateString.toString() + logFileExtension;
    }

    //    public String getSetting(String p_key)
    //    {
    //        return settings.get(p_key);
    //    }

}
