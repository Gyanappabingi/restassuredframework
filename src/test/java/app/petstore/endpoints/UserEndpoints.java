package app.petstore.endpoints;

import com.aventstack.extentreports.gherkin.model.Given;

import app.petstore.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class UserEndpoints {
 
	
	//contains CRUD method implementation
	
	public static Response createuser(User payload) {
		
		Response response= 
				given()
				 .contentType(ContentType.JSON)
				 .accept(ContentType.JSON)
				 .body(payload).
			  when()
			     .post(Routes.post_url);
		return response;
			
	}
	
	public static Response readUser(String username) {
		Response response= given()
				.pathParam("username", username)
				
		.when()
		 .get(Routes.get_url);
		 return response;
	}
	
   public static Response updateuser(String username,User payload) {
		
		Response response= 
				given()
				 .contentType(ContentType.JSON)
				 .accept(ContentType.JSON)
				 .pathParam("username", username)
				 .body(payload).
			  when()
			     .put(Routes.update_url);
		return response;
			
	}
   
    public static Response deleteuser(String username) {
	Response response= given()
			.pathParam("username", username)
	     .when()
	       .delete(Routes.delete_url);
	        return response;
}
}
