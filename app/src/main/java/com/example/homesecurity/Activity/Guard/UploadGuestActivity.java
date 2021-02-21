package com.example.homesecurity.Activity.Guard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.homesecurity.Model.ModelGuest;
import com.example.homesecurity.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UploadGuestActivity extends AppCompatActivity {
    private EditText edname,edphone,edflat,edwork;
    private String key,name, phone,flat,work,CitizenPassword;
    private Button btnNext;

    SharedPreferences sharedPreferences;
    String GuestDetails = "guestDetails";

    String SP_KEY = "key";
    String SP_NAME = "name";
    String SP_PHONE = "phone";
    String SP_FLAT = "flat";
    String SP_WORK = "work";

    DatabaseReference mUserDatabaseCitizen;
    DatabaseReference mUserDatabaseGuest;
    DatabaseReference mUserDatabaseGuard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_guest);

        mUserDatabaseCitizen = FirebaseDatabase.getInstance().getReference("citizen");
        mUserDatabaseGuard = FirebaseDatabase.getInstance().getReference("Guard");
        mUserDatabaseGuest = FirebaseDatabase.getInstance().getReference("guest");

        initialize();
        getValuesc();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getValuesc();
                if(validate()){
                    mUserDatabaseCitizen.child(flat).child("info").child("password").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String password = (String) snapshot.getValue();
                            if (password == null) {
                                //flat does not exist
                                Toast.makeText(UploadGuestActivity.this, "please check the flat", Toast.LENGTH_SHORT).show();
                            } else {
                                //flat exists
                                btnNext.setEnabled(false);
                                openNext();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }

    private boolean validate() {
        getValuesc();
        Integer check = phone.length();
        if (name.equals("") || phone.equals("") || work.equals("") || flat.equals("")) {
            Toast.makeText(this, "please check all the fields", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!check.equals(10)){
            Toast.makeText(this, "please check the phone number " , Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void openNext() {
        getValuesc();
        sendData();
        Intent i = new Intent(UploadGuestActivity.this,PhoneGuardActivity.class);
        startActivity(i);
        finish();
    }

    private void sendData() {
        getValuesc();

        key = mUserDatabaseCitizen.push().getKey();

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(SP_KEY,key);
        editor.putString(SP_NAME,name);
        editor.putString(SP_PHONE,phone);
        editor.putString(SP_WORK,work);
        editor.putString(SP_FLAT,flat);

        editor.commit();
    }

    private void getValuesc() {
        name = edname.getText().toString().trim();
        phone = edphone.getText().toString().trim();
        work = edwork.getText().toString().trim();

        flat = edflat.getText().toString().trim();
    }

    private void initialize() {
        sharedPreferences = getSharedPreferences(GuestDetails, Context.MODE_PRIVATE);

        btnNext = findViewById(R.id.Btn_UploadFirebase);

        edname = findViewById(R.id.Ed_Name);
        edphone = findViewById(R.id.Ed_Phone);
        edflat = findViewById(R.id.Ed_Flat);
        edwork = findViewById(R.id.Ed_Work);
    }

}