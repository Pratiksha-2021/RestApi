package com.dynamicJson;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.Payload;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class ParameterizationInAddBook {
	
	
	@Test(dataProvider="Bookdata")
	public void addBook(String isbn,String aisle) {    //two parameter
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().header("Content-Type", "application/json").body(Payload.AddBookDataProvider(isbn, aisle)).when()
				.post("Library/Addbook.php ").then().log().all().assertThat().statusCode(200).extract().response()
				.asString();

		JsonPath js = ReusableMethods.rawToJson(response);

		String id = js.get("ID");
		System.out.println(id);

	}
	@DataProvider(name="Bookdata")
	public Object[][] getData(){
		return new Object[][]{{"ojxczv","23"},{"vdgs","34"},{"gds","674"}};    //test run three times,two parameter
		
	}
	
	

}
