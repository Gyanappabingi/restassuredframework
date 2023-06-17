package app.petstore.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import app.petstore.endpoints.UserEndpoints;
import app.petstore.payloads.User;
import app.petstore.utilities.Utility;
import io.restassured.response.Response;

public class UserTests  {

	 Faker faker;
	 User userPayload;
	 Utility util;
	 public static String sheetname="Sheet1";
	 public static String sheetname1="Sheet2";
	@BeforeClass
	public void setupdata() {
		 faker=new Faker();
		 userPayload=new User();
		 
		 userPayload.setId(faker.idNumber().hashCode());
		 userPayload.setUsername(faker.name().username());
		 userPayload.setFirstName(faker.name().firstName());
		 userPayload.setLastName(faker.name().lastName());
		 userPayload.setEmail(faker.internet().safeEmailAddress());
		 userPayload.setPassword(faker.internet().password(5,10));
		 userPayload.setPhone(faker.phoneNumber().cellPhone());
		 
		 
	}
	
	
	//Using Faker class
	@Test(priority = 1)
	public void testPostUser() {
		Response response= UserEndpoints.createuser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("USER IS CREATED : "+this.userPayload.getUsername());
	}
	
	@Test(priority = 2 )
	public void testgetUser() {
		
		Response getresponse=UserEndpoints.readUser(this.userPayload.getUsername());
		getresponse.then().log().all();
		Assert.assertEquals(getresponse.getStatusCode(), 200);
		
	}
	
	@Test(priority = 3)
	public void updateUser() {
		
		 userPayload.setFirstName(faker.name().firstName());
		 userPayload.setLastName(faker.name().lastName());
		 userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndpoints.updateuser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("USER UPDATED : "+this.userPayload.getUsername());
	}
	
	
	@Test(priority = 4)
	public void deleteUser() {
		Response response=UserEndpoints.deleteuser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("USER DELETED : "+this.userPayload.getUsername());
	}
}
