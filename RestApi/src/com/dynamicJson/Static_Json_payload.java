package com.dynamicJson;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import files.Payload;

public class Static_Json_payload {
	public static void main(String[] args) throws Exception {

		// how to call json file from outside insted of put in file

		// convert content of file in to string...>>convert to byte..>byte to
		// string
		// new String(Files.readAllBytes(path.get("")))

		// E:\UDEMY RestApi\staticJsonPayload.json

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123")
				.headers("Content-Type", "application/json")
				.body(new String(Files.readAllBytes(Paths.get("E:\\UDEMY RestApi\\staticJsonPayload.json")))).when()
				.post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200)
				.body("scope", equalTo("APP")) // assertion on body(see respone)
				.header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString(); // printing
		// response
		// in
		// console
		System.out.println(response);

		// jsonpath take i/p as a string and converting to json
		JsonPath js = new JsonPath(response);
		String placeid = js.getString("place_id"); // extracting place id(from
		// response)
		System.out.println(placeid);

	}

}
