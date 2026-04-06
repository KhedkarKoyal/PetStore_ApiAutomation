package api.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoint.PetEndPoint;
import api.endpoint.UserEndPonts;
import api.payload.Pet;
import api.payload.Pet.Tag;
import api.payload.User;
import io.restassured.response.Response;

public class PetTest {
	Faker fake;
	Pet pet;
	public Logger log; 
	
	@BeforeClass
	public void setUpData()
	{
		fake=new Faker();
	pet	=new Pet();
		
	  Pet.Category category = new Pet.Category();
	    category.setId(fake.number().numberBetween(1, 100));
	    category.setName(fake.animal().name());
	    
	    // 🔹 Tags1
	    Pet.Tag tag1 = new Pet.Tag();
	    tag1.setId(fake.number().numberBetween(1000, 2000));
	    tag1.setName(fake.lorem().word());
	    
	    // 🔹 Tags1
	    Pet.Tag tag2 = new Pet.Tag();
	    tag2.setId(fake.number().numberBetween(2000, 3000));
	    tag2.setName(fake.lorem().word());
	    
		pet.setId(fake.nation().hashCode());
		pet.setName(fake.name().name());
		pet.setCategory(category);
		
	    // Photo URLs (Array of Strings)
	    pet.setPhotoUrls(Arrays.asList(
	            fake.internet().url(),
	            fake.internet().url()
	    ));
	    
	 // Tags (Array of Objects)
	    pet.setTags(Arrays.asList(tag1, tag2));
	    String Status[]= {"available","pending","sold"};
		pet.setStatus(Status[1]);
		
		log=LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	public void TestPostUser()
	{
		log.info("****************creating pet**********************");
		Response rsp = PetEndPoint.createPet(pet);
		rsp.then().log().all();
		System.out.println(rsp.getHeaders());
		
		log.info("**************** pet is create **********************");
	}
	
//	@Test(priority=2)
	public void TestPostPetImage()
	{
		File file=new File("src/test/resources/sample3.pdf");
		log.info("****************test post image**********************");
		Response rsp = PetEndPoint.createPetImage(this.pet.getId(),file);
		rsp.then().log().all();
		Assert.assertEquals(rsp.contentType(), "application/json");
		
		log.info("**************** test post image done **********************");
	}
	
	//@Test(priority=2)
	public void TestUpdatePet()
	{
		
		log.info("****************test pet update**********************");
		pet.setName(fake.name().firstName());
		Pet.Tag existingTag = pet.getTags().get(0);  // get first tag
		existingTag.setName(fake.lorem().word()); 
		Response rsp = PetEndPoint.updatePet(pet);
		rsp.then().log().all();
		Assert.assertEquals(rsp.contentType(), "application/json");
		
		    
		log.info("****************test pet updated**********************");
		
	}
	//@Test(priority=1)
	public void TestGetPetByStatus()
	{
		log.info("****************creating user**********************");
		Response rsp = PetEndPoint.getPetByStatus(pet.getStatus());
		rsp.then().log().all();
		Assert.assertEquals(rsp.contentType(), "application/json");
		log.info("**************** user is create **********************");
	}
	@Test(priority=2)
	public void TestGetPetById()
	{
		log.info("****************pet get by Id**********************");
		Response rsp = PetEndPoint.getPetById(this.pet.getId());
		rsp.then().log().all();
		Assert.assertEquals(rsp.contentType(), "application/json");
		log.info("**************** pet get by Id found **********************");
	}
	@Test(priority=3)
		public void TestdeletePetById()
		{
			log.info("****************deleting pet**********************");
			Response rsp = PetEndPoint.deletePetById(this.pet.getId());
			rsp.then().log().all();
			Assert.assertEquals(rsp.contentType(), "application/json");
			log.info("**************** pet deleted **********************");
		}
}
