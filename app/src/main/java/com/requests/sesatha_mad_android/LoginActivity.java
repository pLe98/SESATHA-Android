package com.requests.sesatha_mad_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout enteredEmail, enteredPassword;
    private Button login, forgotPw, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        enteredEmail = findViewById(R.id.loginEmail);
        enteredPassword = findViewById(R.id.loginPassword);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        forgotPw = (Button) findViewById(R.id.forgotPw);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterTypeActivity.class);
                startActivity(intent);

            }
        });
        forgotPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, PasswordRestActivity.class);
                startActivity(intent);
            }
        });
    }

    public void loginUser(){


    }
}