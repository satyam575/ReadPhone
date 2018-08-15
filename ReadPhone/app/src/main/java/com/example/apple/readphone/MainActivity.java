package com.example.apple.readphone;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apple.readphone.models.CallLogModel;
import com.example.apple.readphone.models.PhoneContactModel;
import com.example.apple.readphone.models.SmsModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ArrayList<CallLogModel> callLogs;
    ArrayList<PhoneContactModel> phoneContacts ;
    ArrayList<SmsModel> phoneSmsList ;
    Api api;Retrofit retrofit ;
    Button callLogsButton,contactsButton,messagesbutton;
    EditText sendername ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        callLogsButton = findViewById(R.id.callLogs);
        contactsButton = findViewById(R.id.contacts);
        messagesbutton = findViewById(R.id.messages);
        sendername = findViewById(R.id.editTextName);


        retrofit  = new Retrofit.Builder()
                .baseUrl("http://192.168.1.6:4000/") //ip on the lan
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .setLenient()
                        .create()))
                .build();

        api = retrofit.create(Api.class);

        callLogs = new ArrayList<>();
        phoneContacts = new ArrayList<>();
        phoneSmsList =new ArrayList<>();


        callLogsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                getCallDetails();

                Call<List<CallLogModel>> call = api.sendCallLog(sendername.getText().toString(),callLogs);

                call.enqueue(new Callback<List<CallLogModel>>() {
                    @Override
                    public void onResponse(Call<List<CallLogModel>> call, Response<List<CallLogModel>> response) {

                    }

                    @Override
                    public void onFailure(Call<List<CallLogModel>> call, Throwable t) {
                        Log.i("result",t.toString());
                    }
                });
            }
        });

        contactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gettingPhoneContacts();
                Call<List<PhoneContactModel>> contactCall = api.sendContacts(sendername.getText().toString(),phoneContacts);


                contactCall.enqueue(new Callback<List<PhoneContactModel>>() {
                    @Override
                    public void onResponse(Call<List<PhoneContactModel>> call, Response<List<PhoneContactModel>> response) {

                    }

                    @Override
                    public void onFailure(Call<List<PhoneContactModel>> call, Throwable t) {
                        Log.i("result",t.toString());
                    }
                });
            }
        });

        messagesbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getAllSms();

                Call<List<SmsModel>> smsCall = api.sendAllSms(sendername.getText().toString(),phoneSmsList);

                smsCall.enqueue(new Callback<List<SmsModel>>() {
                    @Override
                    public void onResponse(Call<List<SmsModel>> call, Response<List<SmsModel>> response) {

                    }

                    @Override
                    public void onFailure(Call<List<SmsModel>> call, Throwable t) {
                        Log.i("sms result",t.toString());
                    }
                });
            }
        });

    }



    private void gettingPhoneContacts() {


        ContentResolver cr = this.getContentResolver();
        Cursor c = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{ContactsContract.Contacts._ID,
                        ContactsContract.Contacts.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.NUMBER,
                        ContactsContract.RawContacts.ACCOUNT_TYPE},
                ContactsContract.RawContacts.ACCOUNT_TYPE + " <> 'google' ",
                null, null);
        if (c.getCount() <= 0) {
            Log.i("contacts","No Phone Contact Found..!");
        } else {
            while (c.moveToNext()) {
                String Phone_number = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));          //Phone number
                String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));                        //Name of contact
                phoneContacts.add(new PhoneContactModel(name,Phone_number));
            }
        }
    }

    private void getCallDetails() {
        ContentResolver cr = this.getContentResolver();
        @SuppressLint("MissingPermission")
        Cursor managedCursor = cr.query(CallLog.Calls.CONTENT_URI, null, null, null, null);
        int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);

        while (managedCursor.moveToNext()) {
            String phNumber = managedCursor.getString(number);
            String callType = managedCursor.getString(type);
            String callDate = managedCursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            String callDuration = managedCursor.getString(duration);
            String dir = null;
            int dircode = Integer.parseInt(callType);
            switch (dircode) {
                case CallLog.Calls.OUTGOING_TYPE:
                    dir = "OUTGOING";
                    break;
                case CallLog.Calls.INCOMING_TYPE:
                    dir = "INCOMING";
                    break;
                case CallLog.Calls.MISSED_TYPE:
                    dir = "MISSED";
                    break;
            }
            callLogs.add(new CallLogModel(phNumber, dir, callDuration, callDayTime));

        }


    }

    public void getAllSms() {

        ContentResolver cr = this.getContentResolver();
        Cursor c = cr.query(Telephony.Sms.CONTENT_URI, null, null, null, null);
        int totalSMS = 0;
        if (c != null) {
            totalSMS = c.getCount();
            if (c.moveToFirst()) {
                for (int j = 0; j < totalSMS; j++) {
                    String smsDate = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.DATE));
                    String number = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.ADDRESS));
                    String body = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.BODY));
                    Date dateFormat= new Date(Long.valueOf(smsDate));
                    String type = "";
                    switch (Integer.parseInt(c.getString(c.getColumnIndexOrThrow(Telephony.Sms.TYPE)))) {
                        case Telephony.Sms.MESSAGE_TYPE_INBOX:
                            type = "inbox";
                            break;
                        case Telephony.Sms.MESSAGE_TYPE_SENT:
                            type = "sent";
                            break;
                        case Telephony.Sms.MESSAGE_TYPE_OUTBOX:
                            type = "outbox";
                            break;
                        default:
                            break;
                    }

                    phoneSmsList.add(new SmsModel(number,body,dateFormat.toString(),type));
                    c.moveToNext();
                }
            }

            c.close();

        } else {
            Log.i("messages","No message to show!");
        }
    }

}
