package com.example.apple.readphone.models;

public class SmsModel {

    String senderNumber ;
    String body ;
    String date ;
    String type ;

    public SmsModel(String senderNumber, String body, String date, String type) {
        this.senderNumber = senderNumber;
        this.body = body;
        this.date = date;
        this.type = type;
    }

    public String getSenderNumber() {
        return senderNumber;
    }

    public void setSenderNumber(String senderNumber) {
        this.senderNumber = senderNumber;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
