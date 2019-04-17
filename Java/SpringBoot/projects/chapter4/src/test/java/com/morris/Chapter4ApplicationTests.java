package com.morris;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@RunWith(SpringRunner.class)
@WebMvcTest
public class Chapter4ApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void testAddUser() throws Exception{

		RequestBuilder request = MockMvcRequestBuilders.get("/addUser").param("id","111");
		MvcResult mvcResult = mvc.perform(request).andReturn();
		String contentAsString = mvcResult.getResponse().getContentAsString();
		System.out.println(contentAsString);
	}

}
