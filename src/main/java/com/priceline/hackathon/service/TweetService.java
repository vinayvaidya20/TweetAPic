package com.priceline.hackathon.service;

import com.priceline.hackathon.Email.dto.TweetRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import java.io.File;

@Component
public class TweetService {
	static String consumerKeyStr = "awQaZ9GLSMZ3ZzQmaF2g0BnP3";
	static String consumerSecretStr = "aRFNtXF49BivTCdlE0bd0cIgdwgG5EEkWkqhySWbN2HkwMgpA7";
	Logger logger = LoggerFactory.getLogger(TweetService.class);

	public boolean uploadToTwitter(TweetRequest tweetRequest) throws TwitterException {

		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(consumerKeyStr, consumerSecretStr);

		AccessToken accessToken = new AccessToken(tweetRequest.getOauthToken(),
				tweetRequest.getOauthSecret());

		twitter.setOAuthAccessToken(accessToken);

		logger.info("user auth key {} \n filepath {} \n access token {}\n user message {} ", tweetRequest.getOauthToken(), tweetRequest.getFilePath(),
				accessToken, tweetRequest.getUserTweet());



		StatusUpdate status = new StatusUpdate(tweetRequest.getUserTweet());

		if(null != tweetRequest.getFilePath()) {
			File file = new File(tweetRequest.getFilePath().toString());
			status.setMedia(file);
		}

		Status updatedStatus = twitter.updateStatus(status);

		if (updatedStatus != null) {
			return true;
		} else {
			return false;
		}
	}
}
