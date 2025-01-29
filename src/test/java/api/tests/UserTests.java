package api.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker; 
	User user_payload; 
	public Logger logger;
	
	@BeforeTest
	public void setup()
	{
		//this is the first step, here we will set up the data for the POJO class
		faker = new Faker();
		user_payload = new User(); 
		
		user_payload.setId(faker.idNumber().hashCode());  //hascode converts to int
		user_payload.setFirstName(faker.name().firstName());
		user_payload.setLastName(faker.name().lastName());
		user_payload.setUsername(faker.name().username());
		user_payload.setEmail(faker.internet().safeEmailAddress());
		user_payload.setPassword(faker.internet().password(5, 10));
		user_payload.setPhone(faker.phoneNumber().cellPhone());
		
		logger = LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority = 1)
	public void testPostUser()
	{
		logger.debug("this is a debug message");
		logger.info("*********** Creating User ***********");
		Response response = UserEndPoints.createUser(user_payload); 
		
		response.then()
			.log().all(); 
		
		AssertJUnit.assertEquals(response.statusCode(),200);
		
		logger.info("*********** User created ***********");
		
	}
	
	@Test(enabled = true, priority=2)
	public void testGetUserByName()
	{ 
		logger.info("*********** Fetching User Details ***********");
		Response response = UserEndPoints.getUser(this.user_payload.getUsername());
		
		response.then()
			.log().all();
		
		AssertJUnit.assertEquals(response.statusCode(),200);
		
		logger.info("*********** User Info is displayed ***********");
	}
	
	@Test(enabled = true, priority=3)
	public void testUpdateUserByName()
	{ 
		logger.info("*********** Updating User Details ***********");
		//you have to send the fields to be updated also right? , we are not changing username, using the same username from postmethod above
		user_payload.setFirstName(faker.name().firstName());
		user_payload.setPhone(faker.phoneNumber().cellPhone());
		user_payload.setEmail(faker.internet().emailAddress());
		
		Response response = UserEndPoints.updateUser(this.user_payload.getUsername(), user_payload);
		
		response.then()
			.assertThat()
			.statusCode(200)
			.log().all();
		
		//check if updates were made or not: 
		
		testGetUserByName();
		
	}
	
	@Test(enabled = true, priority=3)
	public void testDeleteUserByName()
	{ 
		logger.info("*********** Deleting User ***********");
		Response response = UserEndPoints.deleteUser(this.user_payload.getUsername());
		
		response.then()
			.log().body().statusCode(200);
		

		
	}

}
