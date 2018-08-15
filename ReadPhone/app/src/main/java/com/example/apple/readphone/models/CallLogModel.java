package com.example.apple.readphone.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class CallLogModel {

    String phoneNumber;
    String dir;
    String callDuration;
    Date callDayTime;

    public CallLogModel(String phoneNumber, String dir, String callDuration, Date callDayTime) {
        this.phoneNumber = phoneNumber;
        this.callDuration = callDuration;
        this.dir = dir;
        this.callDayTime = callDayTime;
    }

    public String getPhoneNumber() {

        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(String callDuration) {
        this.callDuration = callDuration;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public Date getCallDayTime() {
        return callDayTime;
    }

    public void setCallDayTime(Date callDayTime) {
        this.callDayTime = callDayTime;
    }
}
