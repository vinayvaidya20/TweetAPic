package com.hackathon.tweetAPic;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
public class tweet {

	static String consumerKeyStr = "awQaZ9GLSMZ3ZzQmaF2g0BnP3";
	static String consumerSecretStr = "aRFNtXF49BivTCdlE0bd0cIgdwgG5EEkWkqhySWbN2HkwMgpA7";
	static String accessTokenStr = "1134106524295606272-AZGKi8EFlSR3UkkJygwv6Yt9MxFOOl";
	static String accessTokenSecretStr = "bd9ultai7VrzyQDbtAZTnfemvUuPnwAOfkUreB4Xmit5i";

	public static void main(String[] args) {

		try {
			Twitter twitter = new TwitterFactory().getInstance();

			twitter.setOAuthConsumer(consumerKeyStr, consumerSecretStr);
			AccessToken accessToken = new AccessToken(accessTokenStr,
					accessTokenSecretStr);

			twitter.setOAuthAccessToken(accessToken);

			twitter.updateStatus("Post using Twitter4J Again111");

			System.out.println("Successfully updated the status in Twitter.");
		} catch (TwitterException te) {
			te.printStackTrace();
		}
	}
}
