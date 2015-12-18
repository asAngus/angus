/**
 * 
 */
package com.angus.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.angus.AngusApplication;
import com.angus.model.CmmTPro;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp") // @WebAppConfiguration：测试环境使用，用来表示测试环境使用的ApplicationContext将是WebApplicationContext类型的；value指定web应用的根；
@SpringApplicationConfiguration(classes = AngusApplication.class)
/**
 * 1、@WebAppConfiguration：测试环境使用，
 * 用来表示测试环境使用的ApplicationContext将是WebApplicationContext类型的；value指定web应用的根；
 * 2、@ContextHierarchy：指定容器层次，即spring-config.xml是父容器，而spring-mvc.xml是子容器，请参考《第三章
 * DispatcherServlet详解 ——跟开涛学SpringMVC》 3、通过@Autowired WebApplicationContext
 * wac：注入web环境的ApplicationContext容器；
 * 4、然后通过MockMvcBuilders.webAppContextSetup(wac).build()创建一个MockMvc进行测试；
 * 
 * @author weipeng
 *
 * @RunWith(SpringJUnit4ClassRunner.class) @WebAppConfiguration(value =
 *                                         "src/main/webapp")
 * @ContextHierarchy({ @ContextConfiguration(name = "parent", locations =
 *                     "classpath:spring-config.xml"),
 * @ContextConfiguration(name = "child", locations = "classpath:spring-mvc.xml")
 *                            })
 * 
 *                            //注解风格 //@RunWith(SpringJUnit4ClassRunner.class)
 *                            //@WebAppConfiguration(value = "src/main/webapp")
 *                            //@ContextHierarchy({
 *                            // @ContextConfiguration(name = "parent", classes
 *                            = AppConfig.class), // @ContextConfiguration(name
 *                            = "child", classes = MvcConfig.class) //})
 */
public class HisunCmmTProCotrollerTest {
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	/**
	 * Test method for
	 * {@link com.angus.controller.HisunCmmTProCotroller#addPro(com.angus.model.CmmTPro)}
	 * .
	 */
	@Test
	public void testAddPro() {
		try {
			CmmTPro pro = new CmmTPro();
			pro.setFundCorg("test");
			pro.setPrdId("YBR");
			pro.setPrdTyp("0100");

			MvcResult result = this.mockMvc
					.perform(post("/cmmtpro/addPro").contentType(MediaType.APPLICATION_JSON).content(pro.toString()))
					.andDo(print()).andExpect(status().isOk()).andReturn();
			// Object asyncResult = result.getAsyncResult();
			System.out.println();
			// MockHttpServletResponse response = result.getResponse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Test method for
	 * {@link com.angus.controller.HisunCmmTProCotroller#findProByPrdType(java.lang.String)}
	 * .
	 */
	@Test
	public void testFindProByPrdType() {
		fail("Not yet implemented");
	}

	@Test
	public void testFind() {
		try {
			MvcResult result = this.mockMvc.perform(get("/cmmtpro/find").contentType(MediaType.APPLICATION_JSON))
					.andDo(print()).andExpect(status().isOk()).andReturn();
			String contentAsString = result.getResponse().getContentAsString();

			System.out.println(contentAsString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
