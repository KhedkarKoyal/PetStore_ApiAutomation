package api.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoint.UserEndPonts;
import api.payload.User;
import api.utilies.DataProviders;
import io.restassured.response.Response;

public class DDTestUser {

	@Test(priority=1,dataProvider="Data",dataProviderClass=DataProviders.class)
	public void testPostUser(String userID,String userName,String fname,String lname,String useremail,String pwd,String ph)
	{
		 System.out.println("totalRows----->"+1);
		User user=new User();
		user.setId(Integer.parseInt(userID));
		user.setUsername(userName);
		user.setFirstName(fname);
		user.setLastName(lname);
		user.setEmail(useremail);
		user.setPassword(pwd);
		user.setPhone(ph);
		
		Response rsp = UserEndPonts.createUSer(user);
		Assert.assertEquals(rsp.getStatusCode(), 200);
		//rsp.then().log().all();
		
	}
	@Test(priority=2,dataProvider="UserName",dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String userName)
	{
		Response rsp = UserEndPonts.DeleteUSeByNamer(userName);
		//rsp.then().log().body();
		Assert.assertEquals(rsp.getStatusCode(), 200);
	}
}
















