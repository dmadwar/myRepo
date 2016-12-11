package com.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.model.ShopDetails;
import com.util.DistanceCalculator;
/**
 * Service layer for shop details to
 * fetch and to save.
 * @author dipthimadwar
 * @version 1.0
 */
@Service
public class ShopService {
	HashMap<String, ShopDetails> shopDetailsCol;
	private int keyIndex=0;
	
	@Value("${nearest.radius.distance}")
	private int minDistance;
	
	public ShopService(){
		shopDetailsCol = new HashMap<String, ShopDetails>();
	}
	
	//Create the shop details
	public ShopDetails createShopDetails(ShopDetails shopDetails){
		if(shopDetailsCol==null){
			shopDetailsCol=new HashMap<String, ShopDetails>();
		}
		keyIndex=keyIndex+1;
		String keyIndexStr=Integer.toString(keyIndex);
		shopDetails.setShopId(keyIndex); 
		shopDetailsCol.put(keyIndexStr, shopDetails);
		return shopDetails;
	}
	
	//Retrieve the nearest shop details
	public ArrayList<ShopDetails> getNearestShops(Double longitude, Double latitude){
		ArrayList<ShopDetails> nearestShops = new ArrayList<ShopDetails>();
		if(shopDetailsCol.isEmpty()){
			return null;
		}else{
			Iterator<String> iter= shopDetailsCol.keySet().iterator();
			while(iter.hasNext()){
				ShopDetails shopDetails=shopDetailsCol.get(iter.next());
				double distance = DistanceCalculator.distanceBetween(latitude,
						longitude,
						Double.valueOf(shopDetails.getShopLatitude()),
						Double.valueOf(shopDetails.getShopLongitude()));
				if(distance<minDistance){
					nearestShops.add(shopDetails);
				}
			}
		}
		return nearestShops;
	}

}
