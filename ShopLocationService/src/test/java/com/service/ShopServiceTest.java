package com.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.model.ShopAddress;
import com.model.ShopDetails;

public class ShopServiceTest {
	
	private ShopService shopService;
	
	@Before
    public void setUp() {
		shopService = new ShopService();
    }
	
	@Test
    public void createShopDetailsSuccess() throws Exception{
		ShopDetails shopDetails=new ShopDetails();
		shopDetails.setShopName("Dmart");
		ShopAddress shopAddress=new ShopAddress();
		shopAddress.setNumber(1);
		shopAddress.setPostCode("TW136AY"); 
		shopDetails.setShopAddress(shopAddress);
		ShopDetails shopDetails2=shopService.createShopDetails(shopDetails);	
		assertNotNull(shopDetails2.getShopId());
	}

}
