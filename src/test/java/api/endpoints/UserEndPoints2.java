/*
 * only to show how to read URL's from properties file 
 */

package api.endpoints;

import io.restassured.RestAssured.*;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

import java.util.ResourceBundle;

import api.payload.User;



public class UserEndPoints2 {
	
	//we need to load the properties file and this is how we do it: 
	
	static ResourceBundle getURL()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes"); //loads the properties file
		return routes;
	}
	
	public static Response createUser(User payload)
	{
		String post_url = getURL().getString("post_url");
		Response response = given()
			.contentType("application/json")
			.accept("application/json")
			.body(payload)
		
		.when()
			.post(post_url);
		
		return response; 
	}
	
	
	public static Response getUser(String userName)
	{
		String get_url = getURL().getString("get_url");
		Response response = given() 
			.pathParam("username", userName)
			.accept("application/json")
		
		.when()
			.get(get_url);
		
		return response;
	}
	
	public static Response updateUser(String userName, User payload)
	{
		String put_url = getURL().getString("put_url");
		Response response = given() 
			.pathParam("username", userName)
			.contentType("application/json")
			.accept("application/json")
			.body(payload)
		
		.when()
			.put(put_url);
		
		return response;
	}
	
	public static Response deleteUser(String userName)
	{
		String delete_url = getURL().getString("delete_url");
		Response response = given() 
			.pathParam("username", userName)
			.accept("application/json")
		
		.when()
			.delete(delete_url);
		
		return response;
	}
	
	

}
