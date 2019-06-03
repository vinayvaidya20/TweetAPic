package com.priceline.hackathon.tweetAPic;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import java.awt.*;
import java.net.URI;

public class JavaTweet {

	static String consumerKeyStr = "awQaZ9GLSMZ3ZzQmaF2g0BnP3";
	static String consumerSecretStr = "aRFNtXF49BivTCdlE0bd0cIgdwgG5EEkWkqhySWbN2HkwMgpA7";
	static String accessTokenStr = "1134106524295606272-o7ifp6CnVw7d2xgYVpuFVsO2pRicX4";
	static String accessTokenSecretStr = "bTLfXyNMCkeaaL3NSADVfqsrczKMf8bD2IOTqQZPeFamr";

	public static void main(String[] args) {

		try {
		Twitter twitter = new TwitterFactory().getInstance();

		twitter.setOAuthConsumer(consumerKeyStr, consumerSecretStr);

		RequestToken requestToken = twitter.getOAuthRequestToken("http://localhost:8080/getVerifier");
		requestToken.getAuthenticationURL();
		System.out.println(requestToken.getAuthenticationURL());

		String url =  requestToken.getAuthenticationURL();

		Desktop desktop = java.awt.Desktop.getDesktop();
		URI oURL = new URI(url);
		desktop.browse(oURL);


		//twitter.updateStatus("Post using Twitter4J Again");

		System.out.println("Successfully updated the status in Twitter.");
	} catch (Exception e) {
		e.printStackTrace();
	}
}

}

