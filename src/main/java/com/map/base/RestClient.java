package com.map.base;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

import java.util.Map;

import org.codehaus.groovy.util.ListHashMap;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.map.config.PropertiesReader;
import com.map.exception.InvalidEndpointException;
 
public class RestClient {
	
	RequestSpecification requestSpec;
	
	public RestClient(RequestSpecification requestSpec)
	{
	this.requestSpec=requestSpec;
	}
	
	//Build the Endpoint URL
	public  String buildEndpointURL(String endpoint)
	{
		if (endpoint==null || endpoint.isEmpty())
		{
			throw new InvalidEndpointException("The endpoint cannot be blank or null "+endpoint);	
		}	 
		 PropertiesReader propreader=new PropertiesReader(BaseAPIConfig.env);
		 String baseURL=propreader.get("base_url"); 	
		 return baseURL+endpoint;
	}
	
	//Add the Quey Params
	public RequestSpecification addQueryParams(RequestSpecification request, Map<String,Object> queryParams)
	{
		if (queryParams==null || queryParams.isEmpty())
		{
			queryParams=new ListHashMap<String, Object>();	
		}
		request.queryParams(queryParams);
		return request;
	}
	
	//Add Header Params
	public RequestSpecification addHeaderParams(RequestSpecification request, Map<String,Object> headerParams)
	{
		if (headerParams==null || headerParams.isEmpty())
		{
			headerParams=new ListHashMap<String, Object>();	
		}
		headerParams.put("Content-Type","application/json");
		request.headers(headerParams);
		return request;
	}
	
	// GET Rest Action
	public Response getRestAction(String endpoint, 
								  Map<String, Object> queryParams, 
								  Map<String, Object> headerParams)
	{

		  RequestSpecification request = given().spec(requestSpec);
		  request=addQueryParams(request, queryParams);
		  request=addHeaderParams(request, headerParams);
		  Response response = request.when()
		            				 .get(buildEndpointURL(endpoint))
		            				 .then()
		            				 .extract()
		            				 .response();
		  return response;
	}
	
	//POST Rest Action
	public Response postRestAction(String endpoint, 
								   Map<String,Object> queryParams, 
								   Map<String,Object> headerParams, 
								   JSONPObject body)
	{
		RequestSpecification request = given().spec(requestSpec);
	    request=addQueryParams(request, queryParams);
	    request=addHeaderParams(request, headerParams);	    
	    Response response = request.when()
	    						   .body(body)
	            				   .post(endpoint)
	            				   .then()
	            				   .extract()
	            				   .response();
	    return response;
	}
	
	
	

}
