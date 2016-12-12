package com.login;

import javax.jws.WebService;

//import org.json.simple.*;
//import org.json.*;

@WebService
public class Calculate {
	// public boolean validate(String username, String password){

	public Result calculate(int num1, int num2, String operation) {
		System.out.println("calculating..");
		System.out.println("Task is: " + operation + " " + num1 + ", " + num2);

//		JSONObject result = new JSONObject();
		Result result = new Result();
		int value;

	//	System.out.println("Before Trying..");
		try {
		//	System.out.println("Trying..");
			if (operation != null) {
				System.out.println("Operation is not null, valid numbers and valid operation");
				switch (operation) {
				case "add":
					value = (num1) + (num2);
					System.out.println("Value is " + value);
					result.setStatusCode(200);
					result.setValue(value);
//					result.put("statusCode", 200);
//					result.put("value", value);
					break;
				case "subtract":
					value = num1 - num2;
					System.out.println("Value is " + value);
					result.setStatusCode(200);
					result.setValue(value);
//					result.put("statusCode", 200);
//					result.put("value", value);
					break;
				case "multiply":
					value = num1 * num2;
					System.out.println("Value is " + value);
					result.setStatusCode(200);
					result.setValue(value);
//					result.put("statusCode", 200);
//					result.put("value", value);
					break;
				case "divide":
					if (num2 != 0) {
						value = num1 / num2;
						System.out.println("Value is " + value);
						result.setStatusCode(200);
						result.setValue(value);
//						result.put("statusCode", 200);
//						result.put("value", value);
					} else {
						result.setStatusCode(401);
//						result.put("statusCode", 401);
					}
					break;
				default:
    				result.setStatusCode(401);
//					result.put("statusCode", 401);
					break;
				}

			} else {
				result.setStatusCode(401);
//				result.put("statusCode", 401);
			}
			//System.out.println("Result being sent is " + result);
			System.out.println("Result being sent is " + result.getStatusCode());
			System.out.println("Result being sent is " + result.getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
//		return result.toString();
		return result;

	}

}
