package com.map.tests;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;


public class MapServices {
	
	public static void main(String [] args) {
		
		RestAssured.baseURI="http://rahulshettyacademy.com/";
		
		String response=given().log().all().when().get("maps/api/place/get/json?place_id=44657b2e56863c8996a4bedf353410b4&key=qaclick123").then().extract().response().asString();
		System.out.println(response.toString());
		JsonPath jsonpath =new JsonPath(response);
		System.out.println(jsonpath.getString("language"));
		
	}
	

}
