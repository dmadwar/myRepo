package com.controller;

import java.util.ArrayList;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.CustomException;
import com.model.ShopDetails;
import com.model.geoApi.GoogleGeoCode;
import com.model.geoApi.GoogleGeoResult;
import com.service.GeoService;
import com.service.ShopService;
/**
 * Shop Details rest service supports 
 * two operations. Create shop and one is to
 * retrieve the nearest shop details.
 * @author dipthimadwar
 * @version 1.0
 */
@RestController
@RequestMapping("/shops")
public class ShopDetailsController {
	
	@Autowired
	ShopService shopService;
	
	@Autowired
	GeoService geoService;
	
	/**
	 * Will create the shop details.
	 * @param shopDetails
	 * @return Shopdetails json
	 */
	@RequestMapping(path="/",
			consumes=MediaType.APPLICATION_JSON,
			produces=MediaType.APPLICATION_JSON,
			method=RequestMethod.POST)
	public ShopDetails createShopDetails(@RequestBody ShopDetails shopDetails) throws CustomException{
		try{
			GoogleGeoCode geoCode=geoService.getGeoCode(shopDetails.getShopAddress().getPostCode(), Boolean.FALSE);
			if(geoCode!=null && geoCode.getResults()!=null){
				GoogleGeoResult[] geoResult=geoCode.getResults();
				shopDetails.setShopLatitude(geoResult[0].getGeometry().getLocation().getLat());
				shopDetails.setShopLongitude(geoResult[0].getGeometry().getLocation().getLng()); 
			}
		}catch(Exception ex){
			throw new CustomException("Not able to locate shop longitude and latitude");
		}
		return shopService.createShopDetails(shopDetails);
		
	}
	/**
	 * Searches nearest shop details to given
	 * longitude and latitude.
	 * @param longitude
	 * @param latitude
	 * @return all the nearest shop details
	 */
	@RequestMapping(path="",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON)
	public ArrayList<ShopDetails> searchNearestShopDetails(@QueryParam("longitude") Double longitude,
			@QueryParam("latitude") Double latitude){
		ArrayList<ShopDetails> nearestShopDetails=shopService.getNearestShops(longitude,latitude);
		return nearestShopDetails;
	}
}
