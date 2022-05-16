package com.restAPi.restapilearning;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

public class Basics {

	public static void main(String[] args) throws IOException {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		RestAssured.useRelaxedHTTPSValidation();
		
		//Create API
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(new String (Files.readAllBytes(Paths.get("C:\\Rahulshetty\\Addplace.json")))).when().post("maps/api/place/add/json")
				.then().assertThat()
				.statusCode(200).body("scope", equalTo("APP"))
				.header("Server", "Apache/2.4.41 (Ubuntu)")
				.extract().response().asString();
		
		System.out.println(response);
		JsonPath js=new JsonPath(response);
		
		String placeid=js.getString("place_id");
		
		System.out.println("70 Summer walk, USA");
		String newaddress="70 Summer walk, USA";
		//update API
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+placeid+"\",\r\n" + 
				"\"address\":\""+newaddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}\r\n" + 
				"")
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat()
		.statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//Get API
		String getPlaceResponse=given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeid)
				.when().get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200)
				.extract().response().asString();
		JsonPath Js2 = Reusablemethods.rawtoJson(getPlaceResponse);
		String updatedaddress=Js2.getString("address");
		Assert.assertEquals(updatedaddress, newaddress);
		System.out.println("Validation success");
	}


}