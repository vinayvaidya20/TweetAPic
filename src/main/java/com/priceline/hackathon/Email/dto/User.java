package com.priceline.hackathon.Email.dto;

public class User {
	private String firstName;
	private String lastName;
	private String emailAddress;
	private Trip trip;
	private Deal deal;


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastNName) {
		this.lastName = lastNName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public Deal getDeal() {
		return deal;
	}

	public void setDeal(Deal deal) {
		this.deal = deal;
	}
}
