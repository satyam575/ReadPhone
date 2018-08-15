package com.example.apple.readphone.models;

public class PhoneContactModel {
    String contactName ;
    String phoneNumber ;

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PhoneContactModel(String contactName, String phoneNumber) {

        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
    }
}
