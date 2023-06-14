package Day1;

import java.io.File;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class DiffWaysToCreatePostReqBody {

//	@Test
//	void testPostUsingHashmap() {
//		HashMap map = new HashMap();
//		map.put("Name", "Snehal");
//		map.put("Location", "Pune");
//		map.put("Phone", "2454567");
//
//		String csrArr[] = { "c", "c++" };
//
//		map.put("Courses", csrArr);
//	}
//
//	@Test
//	void testUsingPOJOClass() {
//
//		POJOClass obj1 = new POJOClass();
//		obj1.setName("Snehal");
//		obj1.setJob("Automation");
//		given().contentType("application/json").body(obj1)
//
//				.when().post("https://reqres.in/api/users")
//
//				.then().log().all();
//
//	}

	// student test

	@Test(priority=0)
	void createNewStudentData() {

		String jsonFilePath = "F:/API Testing/Student_GetSingle.json";

		given().contentType("application/json").body(new File(jsonFilePath))

				.when().post("http://localhost:3000/students")

				.then().log().all().statusCode(201);
	}

	@Test(priority=1)
	void getSingleUser() {
		given()

				.when().get("http://localhost:3000/students/1")

				.then().log().all().statusCode(200);
	}
	
	@Test(priority=2)
	void viewAllStudents() {
		
		when().get("http://localhost:3000/students")

		.then().log().all().statusCode(200);
		
	}
	
	@Test(priority=3)
	void updateStudent(){
		
		POJOClass obj2=new POJOClass();
		String [] course= {"C#","C++","java"};
		obj2.setCourses(course);
//		obj2.setName("Snehal");
//		obj2.setLocation("Pune");
//		obj2.setPhone("14534534");
		
		given()
		.contentType("application/json")
		.body(obj2)
		
		.when()
		.put("http://localhost:3000/students/6")
		
		.then()
		.log().all()
		.statusCode(200);
		
	}
	
	@Test(priority=4)
	void viewAllStudents2() {
		
		when().get("http://localhost:3000/students")

		.then().log().all().statusCode(200);
		
	}
	
	@Test 
	void deleteStudent() {
		when().delete("http://localhost:3000/students/4")
		.then().log().all().statusCode(200);
	}

}
