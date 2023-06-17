package app.petstore.testcases;

import java.io.IOException;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import app.petstore.endpoints.UserEndpoints;
import app.petstore.payloads.User;
import app.petstore.utilities.ExcelDataProvider;
import app.petstore.utilities.Utility;
import io.restassured.response.Response;

public class DataDrivenTests {

	public Faker faker;
	public User userPayload;
	public Utility utils;
//	
	public static String sheetname="Sheet1";
	public static String sheetname2="Sheet2";
//	
	
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
	
	@DataProvider(name = "data")
	public Object[][] getdata() throws Exception{
		Object[][] data=utils.getExceldata(sheetname);
		return data;
		
	}
	@Test(priority = 1,dataProvider = "data")
	public void testpostuser(String id,String username,String firstname,String lastname,String email,String password,String phone,String userstatus ) throws Exception{
		
		 userPayload=new User();
		userPayload.setId(Integer.parseInt(id));
		userPayload.setUsername(username);
		userPayload.setFirstName(firstname);
		userPayload.setLastName(lastname);
		userPayload.setEmail(email);
		userPayload.setPassword(password);
		userPayload.setPhone(phone);
		userPayload.setUserStatus(0);
		
		Response resoponse=UserEndpoints.createuser(userPayload);
		resoponse.then().log().all();
		
		Assert.assertEquals(resoponse.getStatusCode(), 200);
		System.out.println("USER CREATED :"+this.userPayload.getUsername());
		
	}
	
	@Test(priority = 2, dataProvider = "excelData",dataProviderClass = ExcelDataProvider.class)
	public void testgetuser(String username) {
		Response response=UserEndpoints.readUser(username);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("GET USER :"+username);
	}
	
	@DataProvider(name = "updatedata")
	public Object[][] updatedata() throws Exception{
		Object[][] data=utils.getExceldata(sheetname2);
		return data;
		
	}
	@Test(priority = 3 ,dataProvider = "updatedata")
	public void testupdateuser(String id,String username,String firstname,String lastname,String email,String password,String phone,String userstatus ) {
		
		 userPayload=new User();
			
		 userPayload.setId(Integer.parseInt(id));
			userPayload.setUsername(username);
			userPayload.setFirstName(firstname);
			userPayload.setLastName(lastname);
			userPayload.setEmail(email);
			userPayload.setPassword(password);
			userPayload.setPhone(phone);
			userPayload.setUserStatus(0);
		
		Response resoponse=UserEndpoints.updateuser(username, userPayload);
		resoponse.then().log().all();
		
		Assert.assertEquals(resoponse.getStatusCode(), 200);
		System.out.println("USER Updated :"+username);
		
	}
	
//	@Test(priority = 4, dataProvider = "excelData",dataProviderClass = ExcelDataProvider.class)
//	public void testdeleteuser(String username) {
//		
//		Response response=UserEndpoints.deleteuser(username);
//		response.then().log().all();
//		Assert.assertEquals(response.getStatusCode(), 200);
//		System.out.println("USER DELETED :"+username);
//	}
	
}
