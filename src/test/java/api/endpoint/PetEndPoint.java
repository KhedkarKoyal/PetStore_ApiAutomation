package api.endpoint;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.List;

import api.payload.Pet;
import api.payload.User;
import io.restassured.response.Response;
public class PetEndPoint {

	public static Response createPet(Pet pet)
	{
		Response response=given()
			.body(pet)
			.contentType("application/json")
			.accept("application/json")
		.when()
			.post(Routes.petPost_url);
		return response;
		
	}

	public static Response createPetImage(int id, File file) {
		Response response=given()
				.contentType("multipart/form-data")
				.accept("application/json")
				.pathParam("petId", id)
				.multiPart("file", file)
				.multiPart("additionalMetadata", "test data")
			.when()
				.post(Routes.petImagePost_url);
			return response;
	}

	public static Response updatePet(Pet pet) {
		
		Response response=given()
				.contentType("application/json")
				.body(pet)
				
			.when()
				.put(Routes.petUpdate_url);
			return response;
	}
	
public static Response getPetByStatus(String status) {
		
		Response response=given()
				.contentType("application/json")
				.queryParam("status", status)
				
			.when()
				.get(Routes.petGetByStatus_url);
			return response;
	}
public static Response getPetById(int id) {
	
	Response response=given()
			.contentType("application/json")
			.pathParam("id", id)
			
		.when()
			.get(Routes.PetGetById_url);
		return response;
}
public static Response deletePetById(int id) {
	
	Response response=given()
			.contentType("application/json")
			.pathParam("id", id)
			
		.when()
			.delete(Routes.PetGetById_url);
		return response;
}
}
