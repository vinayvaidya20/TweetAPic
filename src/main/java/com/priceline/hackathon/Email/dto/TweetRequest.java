package com.priceline.hackathon.Email.dto;

import java.nio.file.Path;

public class TweetRequest {

	private Path filePath;
	private String oauthToken;
	private String oauthSecret;
	private String userTweet;

	public TweetRequest() {

	}

	public TweetRequest(Path filePath, String oauthToken, String oauthSecret, String userTweet) {
		this.filePath = filePath;
		this.oauthToken = oauthToken;
		this.oauthSecret = oauthSecret;
		this.userTweet = userTweet;
	}

	public Path getFilePath() {
		return filePath;
	}

	public void setFilePath(Path filePath) {
		this.filePath = filePath;
	}

	public String getOauthToken() {
		return oauthToken;
	}

	public void setOauthToken(String oauthToken) {
		this.oauthToken = oauthToken;
	}

	public String getOauthSecret() {
		return oauthSecret;
	}

	public void setOauthSecret(String oauthSecret) {
		this.oauthSecret = oauthSecret;
	}

	public String getUserTweet() {
		return userTweet;
	}

	public void setUserTweet(String userTweet) {
		this.userTweet = userTweet;
	}
}
