package com.restAPi.restapilearning;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class ComplexJsonparse {

	public static void main(String[] args) {
		JsonPath js = new JsonPath(payload.Courseprice());
		int count = js.getInt("courses.size");
		System.out.println("No of Courses :" + count);

		System.out.println("First course title :" + js.getString("courses[0].title"));

		System.out.println("Purchase amount :" + js.getInt("dashboard.purchaseAmount"));

		for (int i = 0; i < count; i++) {
			System.out.println("Title is :" + js.getString("courses[" + i + "].title") + " and the Price is :"
					+ js.getInt("courses[" + i + "].price"));
		}

		System.out.println("Print no of copies for Cypress");

		for (int i = 0; i < count; i++) {

			if (js.getString("courses[" + i + "].title").equalsIgnoreCase("Cypress")) {
				System.out.println("No of copies are " + js.getInt("courses[" + i + "].copies"));
				break;
			}
		}

		System.out.println("Validate purchase Amount");
		
		int purchaseamout=js.getInt("dashboard.purchaseAmount");
		
		int sum=0;

		for (int i = 0; i < count; i++) {

			sum=sum+(js.getInt("courses[" + i + "].copies")*js.getInt("courses[" + i + "].price"));
			
		}
		System.out.println("calculated amount :"+sum);
		Assert.assertEquals(purchaseamout, sum);
		System.out.println("Validation success");
	}

}
