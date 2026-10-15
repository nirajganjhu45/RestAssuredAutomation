package com.map.base;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.map.config.PropertiesReader;
import com.map.endpoints.EndpointURLs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class BaseAPIConfig {
	
    public static RequestSpecification requestSpec;
    private String baseURL;
    public static String env="demo";
    
    @BeforeSuite
    public void setup()
    {
    PropertiesReader propreader=new PropertiesReader(env);
    baseURL=propreader.get("url"); 	
    }
    
    @BeforeClass
    public void buildULR() {    	
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(baseURL)
                .build();
    }
    
    
    
    

}
