package com.demo.mocktest;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.HashMap;

import org.testng.ITest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.demo.HttpRequestor.HTTPRequestor;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.jayway.restassured.response.Response;

public class HTTPRequestorMockTest implements ITest {

	private WireMockServer wireMockServer;

	@Override
	public String getTestName() {
		return "Mock Test";
	}

	public HTTPRequestorMockTest() {
		wireMockServer = new WireMockServer(wireMockConfig().port(8090));
		WireMock.configureFor("localhost", 8090);
		wireMockServer.start();
	}

	@BeforeTest
	public void stubRequests() {
		stubFor(get(urlEqualTo("/cars/Chevy")).withHeader("Accept", equalTo("application/json"))
				.withHeader("User-Agent", equalTo("Jakarta Commons-HttpClient/3.1"))
				.willReturn(aResponse().withHeader("content-type", "application/json").withStatus(200)
						.withBody("{\"message\":\"Chevy car response body\"}")));

		stubFor(post(urlEqualTo("/cars/Mini")).withHeader("Authorization", equalTo("Basic d8d74jf82o929d"))
				.withHeader("Accept", equalTo("application/json"))
				.withHeader("User-Agent", equalTo("Jakarta Commons-HttpClient/3.1"))
				.withRequestBody(equalTo("Mini Cooper"))
				.willReturn(aResponse().withHeader("content-type", "application/json").withStatus(200)
						.withBody("{\"message\":\"Mini Cooper car response body\", \"success\":true}")));
	}

	@Test
	public void test_Get_Method() {

		String url = "http://localhost:8090/cars/Chevy";
		String method = "GET";
		String body = "";

		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/json");
		headers.put("User-Agent", "Jakarta Commons-HttpClient/3.1");

		HTTPRequestor httpRequestor = new HTTPRequestor();
		Response response = null;

		try {
			response = httpRequestor.perform_request(url, method, headers, body);
		} catch (Exception e) {
			fail("Problem using HTTPRequestor to generate response: " + e.getMessage());
		}

		assertEquals(200, response.getStatusCode());
		assertEquals("Chevy car response body", response.jsonPath().get("message"));

	}

	@Test
	public void test_Post_Method() {

		String url = "http://localhost:8090/cars/Mini";
		String method = "POST";
		String body = "Mini Cooper";

		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "Basic d8d74jf82o929d");
		headers.put("Accept", "application/json");
		headers.put("User-Agent", "Jakarta Commons-HttpClient/3.1");

		HTTPRequestor httpRequestor = new HTTPRequestor();
		Response response = null;

		try {
			response = httpRequestor.perform_request(url, method, headers, body);
		} catch (Exception e) {
			fail("Problem using HTTPRequestor to generate response: " + e.getMessage());
		}

		assertEquals(200, response.getStatusCode());
		assertEquals("Mini Cooper car response body", response.jsonPath().get("message"));
		// assertEquals(true, response.jsonPath().get("success"));

	}

	@AfterTest(alwaysRun = true)
	public void tearDown() {
		wireMockServer.stop();
		wireMockServer.shutdown();
	}

}
