package com.bigdata.utility;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Parameters
{
    Options options;
    String[] args;
    
    CommandLineParser parser;
    CommandLine cmd;
    
    public Parameters(String[] p_args) throws ParseException
    {
        args = p_args;
        options = new Options();
        options.addOption("t", false, "display current time");
        
        parser = new BasicParser();
        cmd = parser.parse(options, args);
    }
    
//    if(cmd.hasOption("t")) {
//        System.out.println("hahahaha");
//    }
}
