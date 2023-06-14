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
public class LoansTests {

	private static String ADD_LOAN_URL="/loan/applyLoan";
	private static String UPDATE_LOAN_URL="/loan";
	private static String SPECIFIC_LOAN_URL="/loan/customer/1";
	private static String GET_LOAN_URL="/loan";
	private static String DELETE_LOAN_URL="/loan/foreclose/1";
	
	@Autowired
	private TestRestTemplate template;
	@Test
	void addNewLoanToCustomer()
	{
		String requestBody="""
				{
    "loanAmt":120000,
"loanType":"shoploan",
"duration": 121,
"monthlyEMI" :2100,
"customer":{
    "id":1
}
}
				""";
		HttpHeaders headers=new HttpHeaders();
		headers.add("Content-Type","application/json");
		HttpEntity<String> httpEntity=new HttpEntity<String>(requestBody,headers);
		ResponseEntity<String> responseEntity=template.exchange(ADD_LOAN_URL,HttpMethod.POST,httpEntity,String.class);
		assertTrue(responseEntity.getStatusCode().is5xxServerError());
	
	}
	@Test
	void updateLoanToCustomer()
	{
	String requestBody="""
			{
"loanAmt":120000,
"loanType":"shoploan",
"duration": 121,
"monthlyEMI" :2100
		}""";
	HttpHeaders headers=new HttpHeaders();
	headers.add("Content-Type","application/json");
	HttpEntity<String> httpEntity=new HttpEntity<String>(requestBody,headers);
	ResponseEntity<String> responseEntity=template.exchange(UPDATE_LOAN_URL,HttpMethod.PUT,httpEntity,String.class);
	assertTrue(responseEntity.getStatusCode().is5xxServerError());
	
}
	@Test
	void retriveLoanToCustomer() throws JSONException
	{
//	ResponseEntity<String> responseEntity=template.getForEntity(SPECIFIC_LOAN_URL,String.class);
//	String expectedResponse="""
//{
//        "loanId": 1,
//        "loanAmt": 120000.0,
//        "loanType": "shoploan",
//        "duration": 121,
//        "monthlyEMI": 2100.0
//    }
//			""";
//	assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
//	assertEquals("application/json",responseEntity.getHeaders().get("Content-Type").get(0));
//	JSONAssert.assertEquals(expectedResponse,responseEntity.getBody(),false);
		String requestBody="""
				{
	"loanAmt":120000,
	"loanType":"shoploan",
	"duration": 121,
	"monthlyEMI" :2100
			}""";
		HttpHeaders headers=new HttpHeaders();
		headers.add("Content-Type","application/json");
		HttpEntity<String> httpEntity=new HttpEntity<String>(requestBody,headers);
		ResponseEntity<String> responseEntity=template.exchange(SPECIFIC_LOAN_URL,HttpMethod.GET,httpEntity,String.class);
		assertTrue(responseEntity.getStatusCode().is5xxServerError());
	}
	@Test
	void retriveLoans() throws JSONException
	{
		String requestBody="""
			{
	"loanAmt":120000,
	"loanType":"shoploan",
	"duration": 121,
	"monthlyEMI" :2100
			}	
			""";
		HttpHeaders headers=new HttpHeaders();
		headers.add("Content-Type","application/json");
		HttpEntity<String> httpEntity=new HttpEntity<String>(requestBody,headers);
		ResponseEntity<String> responseEntity=template.exchange(GET_LOAN_URL,HttpMethod.GET,httpEntity,String.class);
		assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
	}
	@Test
	void deleteLoans() throws JSONException
	{
		String requestBody="""
	Loan Foreclosed with Loan Id: 1	
			""";
		HttpHeaders headers=new HttpHeaders();
		headers.add("Content-Type","application/json");
		HttpEntity<String> httpEntity=new HttpEntity<String>(requestBody,headers);
		ResponseEntity<String> responseEntity=template.exchange(DELETE_LOAN_URL,HttpMethod.DELETE,httpEntity,String.class);
		assertTrue(responseEntity.getStatusCode().is5xxServerError());
	}
	}
	
		
