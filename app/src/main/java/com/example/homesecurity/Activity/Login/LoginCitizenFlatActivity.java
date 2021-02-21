package com.example.homesecurity.Activity.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homesecurity.Activity.Admin.AdminMainActivity;
import com.example.homesecurity.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginCitizenFlatActivity extends AppCompatActivity {
    Button btnNextCitizenLogin;
    EditText edFlatCitizenLogin;
    TextView tvHint;

    DatabaseReference mUserDatabaseCitizen;

    public String flat, password;

    SharedPreferences sharedPreferences;
    String CitizenDetails = "citizenDetails";

    String SP_FLAT = "flat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_citizen_flat);

        initialize();

        mUserDatabaseCitizen = FirebaseDatabase.getInstance().getReference("citizen");

        btnNextCitizenLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
                putData();
                if (!flat.equals("")) {
                    if (AdminLogin()) {
                        load();
                    }
                } else
                    Toast.makeText(LoginCitizenFlatActivity.this, "please fill all the details", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void load() {
        mUserDatabaseCitizen.child(flat).child("info").child("password").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                password = (String) snapshot.getValue();
                if (password == null) {
                    //new user
                    openNewLogin();
                } else {
                    //old user
                    openOldLogin();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void openOldLogin() {
        startActivity(new Intent(LoginCitizenFlatActivity.this, CitizenLoginOldOwnerActivity.class));
    }

    private void putData() {
        getData();

        sharedPreferences = getSharedPreferences(CitizenDetails, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(SP_FLAT, flat);

        editor.commit();
    }

    private void getData() {
        flat = edFlatCitizenLogin.getText().toString().trim();
    }

    private void initialize() {
        btnNextCitizenLogin = findViewById(R.id.Btn_CitizenFlatNext);

        edFlatCitizenLogin = findViewById(R.id.Ed_FlatDesignationogin);

        tvHint = findViewById(R.id.tv_hintCitizenLogin);
    }

    public void openNewLogin() {
        startActivity(new Intent(LoginCitizenFlatActivity.this, CitizenLoginNewActivity.class));
    }

    public Boolean AdminLogin() {
        if (flat.equals("Admin")) {
            startActivity(new Intent(LoginCitizenFlatActivity.this, AdminMainActivity.class));
            return false;
        } else
            return true;
    }
}