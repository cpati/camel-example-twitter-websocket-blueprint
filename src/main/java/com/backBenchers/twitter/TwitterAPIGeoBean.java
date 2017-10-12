package com.backBenchers.twitter;

import twitter4j.*;
import twitter4j.api.PlacesGeoResources;
import twitter4j.auth.AccessToken;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TwitterAPIGeoBean extends TwitterAPIConfig{
	private static final Logger LOG = LoggerFactory.getLogger(TwitterAPIGeoBean.class);
	
	public TwitterAPIGeoBean() {
		super();
	}
	 
	public String getGeo(String param) {
		
		try {
			
				StringBuffer sb = new StringBuffer();
			
			 	Twitter twitter = new TwitterFactory().getInstance();

	            AccessToken at = new AccessToken(accessToken, accessTokenSecret);
	            twitter.setOAuthConsumer(consumerKey, consumerSecret);
	            twitter.setOAuthAccessToken(at);

	            try {
	            	String cordinates[] = param.split(":");
	            	GeoQuery query = new GeoQuery(new GeoLocation(Double.parseDouble(cordinates[0]), Double.parseDouble(cordinates[1])));
	                ResponseList<Place> places;
	                places = twitter.searchPlaces(query);
	                
	                for (Place place : places) {
	                	sb.append(place.getName() + ":"+ place.getCountry() + ":" + place.getCountryCode() + "\n");
	                }
	              
	            }
	            catch (TwitterException te) {
	                te.printStackTrace();
//	                System.out.println("Failed to search tweets: " + te.getMessage());
	                System.exit(-1);
	            }	
	        LOG.info(sb.toString());
			return sb.toString();
		}catch(Exception ex) {
			LOG.error(ex.getMessage());
//			ex.printStackTrace();
			return "Error !!!Pl. try again";
		}
	}
	
		public static void main(String args[]) {
			TwitterAPIGeoBean o = new TwitterAPIGeoBean();
			System.out.println(o.getGeo("37.279518: -121.867905"));
		}
//	

}
