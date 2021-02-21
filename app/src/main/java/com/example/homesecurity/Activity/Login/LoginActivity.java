package com.example.homesecurity.Activity.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.homesecurity.R;

public class LoginActivity extends AppCompatActivity {
    Button btnCitizen,btnGuard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();

        btnCitizen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,LoginCitizenFlatActivity.class));
            }
        });

        btnGuard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,GuardLoginActivity.class));
            }
        });
    }

    private void initialize() {
        btnCitizen = findViewById(R.id.btn_CitizenLogin);
        btnGuard = findViewById(R.id.btn_GuardLogin);
    }
}