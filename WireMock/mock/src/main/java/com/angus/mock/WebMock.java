/**
 * 
 */
package com.angus.mock;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.matching;
import static com.github.tomakehurst.wiremock.client.WireMock.notMatching;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

/**
 * @author weipeng
 *
 */
public class WebMock {
	// @ClassRule
	// public static WireMockClassRule wireMockRule = new
	// WireMockClassRule(8089);
	//
	// @Rule
	// public WireMockClassRule instanceRule = wireMockRule;
	private WireMockServer wireMockServer;

	@BeforeClass
	public void setUp() throws FileNotFoundException, IOException {
		// 配置本地端口
		WireMock.configureFor(9090);
		wireMockServer = new WireMockServer(wireMockConfig().port(9090));
		// 启动
		wireMockServer.start();
	}

	@Test
	public void exampleTest() {
		stubFor(get(urlEqualTo("/my/resource")).withHeader("Accept", equalTo("text/xml")).willReturn(aResponse()
				.withStatus(200).withHeader("Content-Type", "text/xml").withBody("<response>Some content</response>")));

		verify(postRequestedFor(urlMatching("/my/resource/[a-z0-9]+"))
				.withRequestBody(matching(".*<message>1234</message>.*"))
				.withHeader("Content-Type", notMatching("application/json")));
	}

	@Test
	public void testForMock() {

		// 配置rule
		stubFor(post(urlEqualTo("/api/add")).willReturn(aResponse().withStatus(200).withBody("hzmali")));
		// 自己写的post方法
//		String response = HttpUtil.post("http://127.0.0.1:9090/api/add", "");
//		System.out.println("GET RESP:" + response);
		// 检查
//		Assert.assertTrue(response.contains("hzmali"));

	}

	@AfterClass
	public void tearDown() throws FileNotFoundException, IOException {
		// 停掉mock
		wireMockServer.stop();
	}
}
