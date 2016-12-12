package com.service;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.model.geoApi.GoogleGeoCode;

public class GeoServiceTest {
	
	private GeoService geoService;
	
	@Before
    public void setUp() {
		geoService = new GeoService();
    }
	
	@Test
    public void getGeoCodeSuccessFully() throws Exception{
		GoogleGeoCode geoCode=geoService.getGeoCode("TW136AY", Boolean.FALSE);	
		assertNotNull(geoCode.getResults());
	}
	
	/** As ssl requires API Key this call should fail*/
	@Test(expected=Exception.class)
    public void getGeoCodeWithSSL() throws Exception{
		geoService.getGeoCode("TW136AY", Boolean.TRUE);	
	}
	
}
