package api.endpoints;

import io.restassured.RestAssured.*;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

import api.payload.User;

//Create to store all the CRUD implementation tests of the user module 

public class UserEndPoints {
	
	public static Response createUser(User payload)
	{
		Response response = given()
			.contentType("application/json")
			.accept("application/json")
			.body(payload)
		
		.when()
			.post(Routes.post_url);
		
		return response; 
	}
	
	
	public static Response getUser(String userName)
	{
		Response response = given() 
			.pathParam("username", userName)
			.accept("application/json")
		
		.when()
			.get(Routes.get_url);
		
		return response;
	}
	
	public static Response updateUser(String userName, User payload)
	{
		Response response = given() 
			.pathParam("username", userName)
			.contentType("application/json")
			.accept("application/json")
			.body(payload)
		
		.when()
			.put(Routes.put_url);
		
		return response;
	}
	
	public static Response deleteUser(String userName)
	{
		Response response = given() 
			.pathParam("username", userName)
			.accept("application/json")
		
		.when()
			.delete(Routes.delete_url);
		
		return response;
	}
	
	

}
