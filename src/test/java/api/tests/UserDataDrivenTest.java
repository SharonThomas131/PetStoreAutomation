 package api.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class UserDataDrivenTest {

	/*
	 @BeforeTest
	public void setup()
	{
		//here we will get the payload, this is not needed, because the @Test method will use data provider
	}
	*/
	
	public Logger logger = LogManager.getLogger(this.getClass());
	
	@Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class)
	public void testPostUser(String userID, String username, String fname, String lname, String email, String pswd, String phone )
	{
		logger.info("*********** Creating User ***********");
		User payload = new User(); 
		payload.setId(Integer.parseInt(userID));
		payload.setUsername(username);
		payload.setFirstName(fname);
		payload.setLastName(lname);
		payload.setEmail(email);
		payload.setPassword(pswd);
		payload.setPhone(phone);
		
		Response response = UserEndPoints.createUser(payload);
		
		/*response.then()
			.log().all();*///--->not mandatory, it will only print the data for each row, too much data so you can ignore - not needed
		
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
		logger.info("*********** User created ***********");
	}
	
	
	@Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String username)
	{
		logger.info("*********** Deleting User ***********");
		
		Response response = UserEndPoints.deleteUser(username);
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
		logger.info("*********** User Deleted ***********");
	}
	
}
