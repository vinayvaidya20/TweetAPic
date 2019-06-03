package com.priceline.hackathon.Email.dto;

public class Email {
    private String subject;
    private String templete;
    private User user;


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTemplete() {
        return templete;
    }

    public void setTemplete(String templete) {
        this.templete = templete;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
