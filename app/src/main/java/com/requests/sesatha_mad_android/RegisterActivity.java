package com.requests.sesatha_mad_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;


public class RegisterActivity extends AppCompatActivity {

    //declaring variables
    EditText userName, email, phone, address, password;
    Button registerSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //getting values from user
        userName = (EditText) findViewById(R.id.regUsername);
        email = (EditText) findViewById(R.id.regEmail);
        phone = (EditText) findViewById(R.id.regPhone);
        address = (EditText) findViewById(R.id.regAddress);
        password = (EditText) findViewById(R.id.regPassword);
        registerSubmit = (Button) findViewById(R.id.regSubmit);
    }
}