package com.dynamicJson;

import org.testng.annotations.Test;

import files.Payload;
import files.ReusableMethods;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.RestAssured;

public class AddBook {
	@Test
	public void addBook() {
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().header("Content-Type", "application/json").body(Payload.AddBook()).when()
				.post("Library/Addbook.php ").then().log().all().assertThat().statusCode(200).extract().response()
				.asString();

		JsonPath js = ReusableMethods.rawToJson(response);

		String id = js.get("ID");
		System.out.println(id);

	}

}
