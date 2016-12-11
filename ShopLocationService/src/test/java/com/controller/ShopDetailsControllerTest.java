package com.controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.ShopAddress;
import com.model.ShopDetails;
import com.shop.ShopLocationServiceApplicationTests;

public class ShopDetailsControllerTest extends ShopLocationServiceApplicationTests{
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	
	private MockMvc mockMvc;
	
	@Before
	public void setup(){
		mockMvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void createShopDetails() throws Exception{
		ShopDetails shopDetails=new ShopDetails();
		shopDetails.setShopName("Dmart");
		ShopAddress shopAddress=new ShopAddress();
		shopAddress.setNumber(1);
		shopAddress.setPostCode("TW136AY"); 
		shopDetails.setShopAddress(shopAddress);
		mockMvc.perform(post("/shops/").content(asJsonString(shopDetails)).contentType(MediaType.APPLICATION_JSON)
				  .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.shopName").value("Dmart"));

	}
	
	private static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}  
	
	@Test
	public void searchNearestShopDetails() throws Exception{
		mockMvc.perform(get("/shops?longitude= -0.3862915&latitude=51.4394099").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

	}

}
