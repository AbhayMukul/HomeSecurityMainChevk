package com.example.homesecurity.Activity.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.homesecurity.Activity.Citizen.CitizenMainActivity;
import com.example.homesecurity.Activity.Guard.MainActivity;
import com.example.homesecurity.Activity.Login.LoginActivity;
import com.example.homesecurity.R;

public class SplashActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    String LoginDetails = "loginDetails";
    String accountType = "accountType";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (getAccountType().equals("Guard")) {
                    //set login for Guard
                    Intent n = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(n);
                    finish();
                }
                else if (getAccountType().equals("Citizen")) {
                    //set login for Citizen
                    Intent n = new Intent(SplashActivity.this, CitizenMainActivity.class);
                    startActivity(n);
                    finish();
                } else {
                    //Login
                    Intent n = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(n);
                    finish();
                }
            }
        }, 2000);
    }

    private String getAccountType() {
        sharedPreferences = getSharedPreferences(LoginDetails, Context.MODE_PRIVATE);

        String account = sharedPreferences.getString(accountType, "");

        return account;
    }
}