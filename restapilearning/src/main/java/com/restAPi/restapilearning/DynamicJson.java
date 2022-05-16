package com.restAPi.restapilearning;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {

	@Test(dataProvider="BooksData")
	public void Addbook(String isbn,String aisle) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		RestAssured.useRelaxedHTTPSValidation();
		
		String response = given().log().all().header("Content-Type", "application/json")
				.body(payload.Addbook(isbn,aisle)).when().post("/Library/Addbook.php")
				.then().assertThat()
				.statusCode(200)
				.header("Server", "Apache/2.4.18 (Ubuntu)")
				.extract().response().asString();
		JsonPath js = Reusablemethods.rawtoJson(response);
		System.out.println(js.getString("ID"));
	}
	
	@DataProvider(name="BooksData")

	public String[][] getData()

	{
		String[][] arr = {{"ojhry","9575"},{"cwwtte","4325"},{"okhret","515"}};
		return arr;
	 

	}
}
