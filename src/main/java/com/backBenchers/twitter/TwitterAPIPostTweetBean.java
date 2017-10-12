package com.backBenchers.twitter;

import java.util.List;

import org.apache.commons.io.IOUtils;

import twitter4j.*;
import twitter4j.auth.AccessToken;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TwitterAPIPostTweetBean extends TwitterAPIConfig {
	
	private static final Logger LOG = LoggerFactory.getLogger(TwitterAPIPostTweetBean.class);
	
	public TwitterAPIPostTweetBean() {
		super();
	}
	 
	public String postTweet(String param) {
		
		try {
			
				StringBuffer sb = new StringBuffer();
			
			 	Twitter twitter = new TwitterFactory().getInstance();

	            AccessToken at = new AccessToken(accessToken, accessTokenSecret);
	            twitter.setOAuthConsumer(consumerKey, consumerSecret);
	            twitter.setOAuthAccessToken(at);

	            try {
	            	Status status = twitter.updateStatus(param);
	            	sb.append(status.getText());
	               
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
			TwitterAPIPostTweetBean o = new TwitterAPIPostTweetBean();
			System.out.println(o.postTweet("Testing "));
		}
//	
}
