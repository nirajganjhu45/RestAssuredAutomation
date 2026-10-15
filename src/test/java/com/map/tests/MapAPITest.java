package com.map.tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.map.base.BaseAPIConfig;
import com.map.base.RestClient;
import com.map.endpoints.EndpointURLs;
import io.restassured.response.Response;

public class MapAPITest extends BaseAPIConfig {
	
	@Test
	public static void MapPlaceID_GET() throws Exception
	{
		RestClient restclient=new RestClient(requestSpec);
		Response response=restclient.getRestAction(EndpointURLs.getPlace,null,null);
		Assert.assertEquals(response.getStatusCode(),200,"Expected status code is 200");
	}

}
