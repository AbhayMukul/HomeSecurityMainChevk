package com.example.homesecurity.Activity.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.homesecurity.R;

public class GuestDescriptionActivity extends AppCompatActivity {
    private TextView tvUID,tvName,tvWork,tvPhone,tvFlat,tvDateInGuard,tvDateOutGuard,tvDateOutCitizen;

    SharedPreferences sharedPreferences;
    String GuestDetails = "guestDetails";

    String SP_KEY = "key";
    String SP_NAME = "name";
    String SP_PHONE = "phone";
    String SP_FLAT = "flat";
    String SP_WORK = "work";
    String SP_DateOutCitizen = "dateOutCitizen";
    String SP_TimeOutCitizen = "timeOutCitizen";
    String SP_DateInGuard = "dateInGuard";
    String SP_TimeInGuard = "timeInGuard";
    String SP_TimeOutGuard = "timeOutGuard";
    String SP_DateOutGuard = "dateOutGuard";

    String key,name,phone,flat,dateOutCitizen,dateOutGuard,dateInGuard,work;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_description);

        initialize();

        getDate();

        setDate();
    }

    private void setDate() {
        tvWork.setText(work);
        tvUID.setText(key);
        tvPhone.setText(phone);
        tvName.setText(name);
        tvDateOutGuard.setText(dateOutGuard);
        tvDateOutCitizen.setText(dateOutCitizen);
        tvDateInGuard.setText(dateInGuard);
    }

    private void getDate() {
        sharedPreferences = getSharedPreferences(GuestDetails, Context.MODE_PRIVATE);

        key = sharedPreferences.getString(SP_KEY,"");
        name = sharedPreferences.getString(SP_NAME,"");
        phone = sharedPreferences.getString(SP_PHONE,"");
        flat = sharedPreferences.getString(SP_FLAT,"");
        dateOutCitizen = sharedPreferences.getString(SP_DateOutCitizen,"");
        dateOutGuard = sharedPreferences.getString(SP_DateOutGuard,"");
        dateInGuard = sharedPreferences.getString(SP_DateInGuard,"");
        work = sharedPreferences.getString(SP_WORK,"");
    }

    private void initialize() {
        tvDateInGuard = findViewById(R.id.Tv_GuestDescription_Guard_DateIn);
        tvDateOutCitizen = findViewById(R.id.Tv_GuestDescription_Citizen_DateIn);
        tvDateOutGuard = findViewById(R.id.Tv_GuestDescription_Guard_DateExit);
        tvName = findViewById(R.id.Tv_GuestDescription_Name);
        tvPhone = findViewById(R.id.Tv_GuestDescription_Phone);
        tvUID = findViewById(R.id.Tv_GuestDescription_UIDNumber);
        tvWork = findViewById(R.id.Tv_GuestDescription_Description);
    }
}