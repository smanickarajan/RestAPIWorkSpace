package com.restAPi.restapilearning;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import com.restAPi.pojo.Addplace;
import com.restAPi.pojo.Location;

public class Serialization {

	public static void main(String[] args) throws IOException {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		RestAssured.useRelaxedHTTPSValidation();
		Addplace p = new Addplace();
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		Location l=new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		p.setName("SMS house2");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		List<String> myList=new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		//Create API
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(p).when().post("maps/api/place/add/json")
				.then().assertThat()
				.statusCode(200).body("scope", equalTo("APP"))
				.header("Server", "Apache/2.4.41 (Ubuntu)")
				.extract().response().asString();
		
		System.out.println(response);
		
	}


}