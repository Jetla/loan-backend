package com.sample.loanTests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class CustomerTests {

	private static String SPECIFIC_QUESTION_URL="/customer/1";
	private static String ADD_CUSTOMER_URL="/customer/addCustomer";
	private static String UPDATE_CUSTOMER_URL="/customer";
	String str="""
			{
    "id": 1,
    "fname": "venkey",
    "lname": "ravi",
    "gender": "male",
    "phone": 9705703318,
    "email": "venkey@gmail.com",
    "password": "venkey@034",
    "confirmPassword": "venkey@034",
    "salary": 100000.0,
    "adhaar": 2537522773,
    "pan": "WRYR7383H",
    "loans": []
}
			""";
	 
	@Autowired
	private TestRestTemplate template;
	@Test
	public void RetriveCustomerData() throws JSONException
	{
		String expectedResponse="""
				{
    "id": 1,
    "fname": "venkey",
    "lname": "ravi",
    "gender": "male",
    "phone": 9705703318,
    "email": "venkey@gmail.com",
    "password": "venkey@034",
    "confirmPassword": "venkey@034",
    "salary": 100000.0,
    "adhaar": 2537522773,
    "pan": "WRYR7383H",
    "loans": []""";
		HttpHeaders headers=new HttpHeaders();
		headers.add("Content-Type","application/json");
		HttpEntity<String> httpEntity=new HttpEntity<String>(expectedResponse,headers);
		ResponseEntity<String> responseEntity=template.exchange(SPECIFIC_QUESTION_URL,HttpMethod.GET,httpEntity,String.class);
		assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
//		ResponseEntity<String> responseEntity=template.getForEntity(SPECIFIC_QUESTION_URL,String.class);
//		String expectedResponse="""
//				{
//    "id": 1,
//    "fname": "venkey",
//    "lname": "ravi",
//    "gender": "male",
//    "phone": 9705703318,
//    "email": "venkey@gmail.com",
//    "password": "venkey@034",
//    "confirmPassword": "venkey@034",
//    "salary": 100000.0,
//    "adhaar": 2537522773,
//    "pan": "WRYR7383H",
//    "loans": []
//}
//				""";
//		assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
//		assertEquals("application/json",responseEntity.getHeaders().get("Content-Type").get(0));
//		JSONAssert.assertEquals(expectedResponse,responseEntity.getBody(),false);
//		//assertEquals(expectedResponse.trim(),responseEntity.getBody());
//		
//		System.out.println(responseEntity.getBody());
//		//System.out.println(responseEntity.getHeaders());
	}
//	String body="""
//			{
//"fname":"venkey",
//"lname" : "ravi",
//"gender" : "male",
//"phone" : 9705703318,
//"email" : "venkey@gmail.com",
//"password" :"venkey@034",
//"confirmPassword" : "venkey@034",
//"salary" : 100000,
//"adhaar" : 2537522773,
//"pan": "WRYR7383H"
//}
//			""";
	@Test
	void addNewAddCustomer()
	{
		String requestBody="""
				{
"fname":"venkey",
"lname" : "ravi",
"gender" : "male",
"phone" : 9705703318,
"email" : "venkey@gmail.com",
"password" :"venkey@034",
"confirmPassword" : "venkey@034",
"salary" : 100000,
"adhaar" : 2537522773,
"pan": "WRYR7383H"
}
				""";
		HttpHeaders headers=new HttpHeaders();
		headers.add("Content-Type","application/json");
		HttpEntity<String> httpEntity=new HttpEntity<String>(requestBody,headers);
		ResponseEntity<String> responseEntity=template.exchange(ADD_CUSTOMER_URL,HttpMethod.POST,httpEntity,String.class);
		assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
	
	}
	@Test
	void addUpdateCustomer()
	{
		String requestBody="""
"id":1,			{
"fname":"venkey",
"lname" : "ravi",
"gender" : "male",
"phone" : 9182761987,
"email" : "venkey08@gmail.com",
"password" :"venkey@0341",
"confirmPassword" : "venkey@0341",
"salary" : 1000000,
"adhaar" : 146206338855,
"pan": "WPYR7383Y"
}
				""";
		HttpHeaders headers=new HttpHeaders();
		headers.add("Content-Type","application/json");
		HttpEntity<String> httpEntity=new HttpEntity<String>(requestBody,headers);
		ResponseEntity<String> responseEntity=template.exchange(UPDATE_CUSTOMER_URL,HttpMethod.PUT,httpEntity,String.class);
		assertTrue(responseEntity.getStatusCode().is4xxClientError());
	}
	
}
