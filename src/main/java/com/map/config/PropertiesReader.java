package com.map.config;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

	  public static Properties   properties;
	  public PropertiesReader(String environment) {
		    try {
		    	 properties = new Properties();
		        FileInputStream file = new FileInputStream(
		                "src/main/resources/configfiles/"
		                + environment + ".properties");
		        System.out.println("properties file path--"+file);
		        properties.load(file);
		        
		    	} 
		    catch(IOException e) {
		        throw new RuntimeException(
		          "Unable to load config file");
		    	}
	  	}


		public String get(String key) {
		    return properties.getProperty(key);
		}
}