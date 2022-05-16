package com.restAPi.restapilearning;

import io.restassured.path.json.JsonPath;

public class Reusablemethods {

	public static JsonPath rawtoJson(String response) {
		JsonPath js=new JsonPath(response);
		return js;
	}
}
