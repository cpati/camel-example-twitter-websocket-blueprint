package com.backBenchers.twitter;

import java.util.List;

import org.apache.commons.io.IOUtils;

import twitter4j.*;
import twitter4j.auth.AccessToken;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TwitterAPIBean extends TwitterAPIConfig {
	
	private static final Logger LOG = LoggerFactory.getLogger(TwitterAPIBean.class);
	
	public TwitterAPIBean() {
		super();
	}
	 
	public String getTweet(String param) {
		
		try {
			
				StringBuffer sb = new StringBuffer();
			
			 	Twitter twitter = new TwitterFactory().getInstance();

	            AccessToken at = new AccessToken(accessToken, accessTokenSecret);
	            twitter.setOAuthConsumer(consumerKey, consumerSecret);
	            twitter.setOAuthAccessToken(at);

	            try {
	                Query query = new Query(param);
	                QueryResult result;
	                result = twitter.search(query);
	                List<Status> tweets = result.getTweets();
	                for (Status tweet : tweets) {
	                	sb.append("@" + tweet.getUser().getScreenName() + " - " + tweet.getText()+"\n");
//	                    System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
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
	
//		public static void main(String args[]) {
//			TwitterAPIBean o = new TwitterAPIBean();
//			System.out.println(o.getTweet("Trump"));
//		}
//	
}
