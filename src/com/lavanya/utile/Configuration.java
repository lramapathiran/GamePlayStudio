package com.lavanya.utile;
import java.util.*;
import java.io.*;

public class Configuration {
	private static Configuration _instance = null;

    private Properties props = null;

    private Configuration() throws IOException {
         props = new Properties();
    	
    		
    		InputStream first = new FileInputStream("src/gameConfig.properties");
    		props.load(first);   
    	
    }

    public synchronized static Configuration getInstance() throws IOException {
        if (_instance == null)
            _instance = new Configuration();
        
        return _instance;
    }

    // get property value by name
    public int getIntProperty(String key) {
        int combiDigit = 0;
        if (props.containsKey(key))
        	combiDigit = Integer.parseInt(props.getProperty(key));

        return combiDigit;
    }
    
    public boolean getBooleanProperty(String key) {
    	boolean booleanProperty = true;
    	if (props.containsKey(key))
    		booleanProperty = Boolean.valueOf(props.getProperty(key));
    	return booleanProperty;
    }
}
