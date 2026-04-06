package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoint.PetEndPoint;
import api.endpoint.StoreEndPoints;
import api.payload.Pet;
import api.payload.Store;
import io.restassured.response.Response;

public class StoreTest {
	Faker fake;
	Store store;
	Pet pet;
	//public Logger log; 
	public Logger log = LogManager.getLogger(this.getClass());
	@BeforeClass
	void setData()
	{
		
		PetTest p=new PetTest();
		p.setUpData();
		pet = p.pet; 
		  Response petResponse = PetEndPoint.createPet(pet);

		    // 🔹 Extract petId from response
		    int petId = petResponse.jsonPath().getInt("id");
		    System.out.println("petId----->"+petId);
		    
		fake=new Faker();
		store=new Store();
		pet=new Pet();
		store.setId(fake.number().numberBetween(1, 10));
		store.setQuantity(fake.number().numberBetween(1, 100));
		store.setShipDate("2026-04-06T08:38:03.176Z");
		store.setComplete(true);
		store.setStatus("placed");
		store.setPetId(petId);
	}
	
	@Test(priority=1)
	public void TestPostStore()
	{
		log.info("****************creating store**********************");
		Response rsp = StoreEndPoints.createStore(store);
		rsp.then().log().all();
		Assert.assertEquals(rsp.statusCode(), 200);

		log.info("**************** store is create **********************");
	}
	
	@Test(priority=2)
	public void TestGetStore()
	{
		log.info("****************get store By id**********************");
		Response rsp = StoreEndPoints.getStore(this.store.getId());
		rsp.then().log().all();
		Assert.assertEquals(rsp.statusCode(), 200);
		log.info("**************** geted **********************");
	}
	
	@Test(priority=3)
	public void TestDleteByIDStore()
	{
		log.info("****************delete store By id**********************");
		Response rsp = StoreEndPoints.deleteStore(this.store.getId());
		rsp.then().log().all();
		Assert.assertEquals(rsp.statusCode(), 200);

		log.info("**************** deleted  **********************");
	}
}
