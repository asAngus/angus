package com.demo.HttpRequestor;

import static com.jayway.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

/**
 * Wrapper for RestAssured. Perform an HTTP requests.
 * 
 * @author wadexu
 *
 */
public class HTTPRequestor {

	protected static final Logger logger = LoggerFactory.getLogger(HTTPRequestor.class);
	private RequestSpecification reqSpec;

	/**
	 * Constructor. Initializes the RequestSpecification (relaxedHTTPSValidation
	 * avoids certificate errors).
	 * 
	 */
	public HTTPRequestor() {
		reqSpec = given().relaxedHTTPSValidation();
	}

	public HTTPRequestor(String proxy) {
		reqSpec = given().relaxedHTTPSValidation().proxy(proxy);
	}

	/**
	 * Performs the request using the stored request data and then returns the
	 * sponse
	 * 
	 * @param url
	 * @param method
	 * @param headers
	 * @param body
	 * @return response Response, will contain entire response (response string
	 *         d status code).
	 * @throws Exception
	 */
	public Response perform_request(String url, String method, HashMap<String, String> headers, String body)
			throws Exception {

		Response response = null;

		try {

			for (Map.Entry<String, String> entry : headers.entrySet()) {
				reqSpec.header(entry.getKey(), entry.getValue());
			}

			switch (method) {

			case "GET": {
				response = reqSpec.get(url);
				break;
			}
			case "POST": {
				response = reqSpec.body(body).post(url);
				break;
			}
			case "PUT": {
				response = reqSpec.body(body).put(url);
				break;
			}
			case "DELETE": {
				response = reqSpec.delete(url);
				break;
			}

			default: {
				logger.error("Unknown call type: [" + method + "]");
			}
			}

		} catch (Exception e) {
			logger.error("Problem performing request: ", e);
		}

		return response;
	}
}