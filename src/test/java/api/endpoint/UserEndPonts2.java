package api.endpoint;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.response.Response;

public class UserEndPonts2 {

	//using routes.propertes file
	static ResourceBundle getUrl()
	{
		ResourceBundle routes=ResourceBundle.getBundle("routes");
		return routes;
	}
	
	public static Response createUSer(User payload)
	{
		String post_url=getUrl().getString("post_url");
		Response response=	given()
				.contentType("application/json")
				.accept("application/json")
				.body(payload)
		   .when()
		   		.post(post_url);
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
}
