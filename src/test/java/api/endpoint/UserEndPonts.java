package api.endpoint;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import api.payload.User;
import io.restassured.response.Response;

public class UserEndPonts {

	public static Response createUSer(User payload)
	{
		Response response=	given()
				.contentType("application/json")
				.accept("application/json")
				.body(payload)
		   .when()
		   		.post(Routes.userPost_url);
		System.out.println("response---->"+response.asPrettyString());
		return response;
		
	}
	
	public static Response getUSeByNamer(String username)
	{
		Response response=	given()
				.pathParams("username", username)
		   .when()
		   		.get(Routes.userGet_url);
		return response;
		
	}
	
	public static Response UpdateUSeByNamer(String username,User payload)
	{
		Response response=	given()
				.contentType("application/json")
				.accept("application/json")
				.body(payload)
				.pathParams("username", username)
		   .when()
		   		.put(Routes.userPut_url);
		return response;
		
	}
	
	public static Response DeleteUSeByNamer(String username)
	{
		Response response=	given()
				.pathParams("username", username)
		   .when()
		   		.delete(Routes.userDelete_url);
		return response;
		
	}
	
	public static Response userLogin(String username,String password)
	{
		Response response=	given()
				.queryParam("username", username)
				.queryParam("password", password)
		   .when()
		   		.get(Routes.userGetLogin_url);
		return response;
		
	}
	
	public static Response userLogout()
	{
		Response response=	given()
				.accept("application/json")
		   .when()
		   		.get(Routes.userGetLogout_url);
		return response;
		
	}

	public static Response crateUserWithArray(List user) {
		Response response=	given()
				.contentType("application/json")
				.accept("application/json")
				.body(user)
		   .when()
		   		.post(Routes.userPostByArray_url);
		
		return response;
	}
}
