package Day1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

/*
  
 given()
     Content type,set cookies,add auth,add param
 
 when() 
    get,post,put, 
    
 then()
   All the validation status code,extract response,extract header cookies& response body

*/

public class HTTPRequest {
	int id;

	@Test
	void getUsersList() {
		given()

				.when().get("https://reqres.in/api/users?page=2")

				.then().statusCode(200).log().all();
	}

	@Test(priority = 1)
	void createUser() {
		HashMap map = new HashMap();
		map.put("Name", "Snehal");
		map.put("Job", "Automation tester");

		id = given().contentType("application/json").body(map)

				.when().post("https://reqres.in/api/users").jsonPath().getInt("id");

//				.then().statusCode(201).log().all();
	}

	@Test(priority = 2, dependsOnMethods = "createUser")
	void updateUser() {
		HashMap map = new HashMap();
		map.put("Name", "Snehal");
		map.put("Job", "Automation QA");

		given().contentType("application/json").body(map)

				.when().put("https://reqres.in/api/users/" + id)

				.then().statusCode(200).log().all();
	}

}
