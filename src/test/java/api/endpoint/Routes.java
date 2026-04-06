package api.endpoint;

/*
 *crate user --->https://petstore.swagger.io/v2/user/createWithList
 *get user------>https://petstore.swagger.io/v2/user/{username}
 *update user--->https://petstore.swagger.io/v2/user/{username}
 *delete user--->https://petstore.swagger.io/v2/user/{username}
 */

public class Routes {

	public static String base_url="https://petstore.swagger.io/v2/";
	
	
	public static String userPost_url=base_url+"user";
	public static String userGet_url=base_url+"user/{username}";
	public static String userPut_url=base_url+"user/{username}";
	public static String userDelete_url=base_url+"user/{username}";
	
	
}
