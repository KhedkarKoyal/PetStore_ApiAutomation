package api.endpoint;

import api.payload.Store;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;

public class StoreEndPoints {

	public static Response createStore(Store store) {
		Response response=given()
				.contentType("application/json")
	            .accept("application/json")
				.body(store)
		.when()
			.post(Routes.storePost_url);
		return response;
	}

	public static Response getStore(int id) {
		Response response=given()
				.pathParam("id", id)
		.when()
			.get(Routes.storeGet_url);
		return response;
	}

	public static Response deleteStore(int id) {
		Response response=given()
				.pathParam("id", id)
		.when()
			.delete(Routes.storeDelete_url);
		return response;
	}

}
