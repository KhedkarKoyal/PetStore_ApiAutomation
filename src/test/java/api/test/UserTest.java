package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;


import api.endpoint.UserEndPonts;
import api.endpoint.UserEndPonts2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {

	Faker fake;
	User user;
	public Logger log;   //for log
	
	@BeforeClass
	public void setUpData()
	{
		fake=new Faker();
		user=new User();
		user.setId(fake.idNumber().hashCode());
		user.setUsername(fake.name().username());
		user.setFirstName(fake.name().firstName());
		user.setLastName(fake.name().lastName());
		user.setEmail(fake.internet().safeEmailAddress());
		user.setPassword(fake.internet().password(5, 10));
		user.setPhone(fake.phoneNumber().cellPhone());
		
		log=LogManager.getLogger(this.getClass());
	}
	
	
	@Test(priority=1)
	public void TestPostUser()
	{
		log.info("****************creating user**********************");
		Response rsp = UserEndPonts2.createUSer(user);
		rsp.then().log().all();
		
		log.info("**************** user is create **********************");
	}
	
	//@Test(priority=2,dependsOnMethods="TestPostUser")
	public void TestGetUserByName()
	{
		log.info("****************reading user**********************");
		Response rsp = UserEndPonts.getUSeByNamer(this.user.getUsername());
		//Response rsp = UserEndPonts.getUSeByNamer("codi.nitzsche");
		rsp.then().log().all();
		Assert.assertEquals(rsp.getStatusCode(), 200);
	}
	
	//@Test(priority=3,dependsOnMethods="TestPostUser")
	public void TestPutUserByName()
	
	{
		log.info("****************updatiing user**********************");
		user.setFirstName(fake.name().firstName());
		user.setLastName(fake.name().lastName());
		Response rsp = UserEndPonts.UpdateUSeByNamer(this.user.getUsername(),user);
		rsp.then().log().body();
		Assert.assertEquals(rsp.getStatusCode(), 200);
		
		System.out.println("After update");
		Response rspafter = UserEndPonts.getUSeByNamer(this.user.getUsername());
		rspafter.then().log().all();
	//	Assert.assertEquals(rspafter.getStatusCode(), 200);
	}
	
	
	
	
	//@Test(priority=4,dependsOnMethods="TestPostUser")
	public void TestDeleteUserByName()
	{
		log.info("****************delete user**********************");
		Response rsp = UserEndPonts.DeleteUSeByNamer(this.user.getUsername());
		rsp.then().log().body();
		Assert.assertEquals(rsp.getStatusCode(), 200);
		
		
	}
	
	
	
	
	
	
	
	
}
