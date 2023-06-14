package Day2;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

public class JsonSchemaValidation {

	@Test()
	void validateJsonSchema() {
//		given().contentType(ContentType.JSON)
//
//				.when().get("http://localhost:3000/store")
//
//				.then().statusCode(200).header("Content-Type", "application/json; charset=utf-8")
//				.body("book[3].title", equalTo("The Lord of the Rings")).log().all();

		// Aproch 2nd

		Response res = given().contentType(ContentType.JSON)

				.when().get("http://localhost:3000/store");

		Assert.assertEquals(res.getStatusCode(), 200, "Response code is NOT matched!!");

		Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8",
				"Header value is matched with given!!");

		String bookName = res.jsonPath().get("book[3].title").toString();
		Assert.assertEquals(bookName, "The Lord of the Rings");
		
	}
	
	@Test 
	void parseJsonDataUsingJsonObject() {
		Response resa=given().contentType(ContentType.JSON)
				.when()
				.get("http://localhost:3000/store");
		
		JSONObject jo= new JSONObject(resa.asString());
		
		//Print All title of the book
		
		for(int i=0;i<jo.getJSONArray("book").length();i++) {
			String titlee=jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			System.out.println("Title of object array"+i+" - "+titlee);
		}
		//find total price of book
		
		double priceTotal=0;
		
		for(int i=0;i<jo.getJSONArray("book").length();i++) {
			String price=jo.getJSONArray("book").getJSONObject(i).get("price").toString() ;
			priceTotal=priceTotal+Double.parseDouble(price);
		}
		
		System.out.println("Total price - "+priceTotal);	
	}
}
