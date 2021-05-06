package com.requests.sesatha_mad_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;


public class RegisterActivity extends AppCompatActivity {

    //declaring variables
    TextInputLayout userName, email, phone, address1, address2,address3,password;
    Button registerSubmit;



    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Bundle extras = getIntent().getExtras();
        String userType = extras.getSerializable("userType").toString().trim();

        //getting values from user
        userName =  findViewById(R.id.regUsername);
        email =  findViewById(R.id.regEmail);
        phone = findViewById(R.id.regPhone);
        address1 = findViewById(R.id.regAddress1);
        address2 = findViewById(R.id.regAddress2);
        address3 = findViewById(R.id.regAddress3);
        password = findViewById(R.id.regPassword);
        registerSubmit = (Button) findViewById(R.id.btnPost);

        registerSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userId = "U" + String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
                String dUsername = userName.getEditText().getText().toString().trim();
                String dEmail = email.getEditText().getText().toString().trim();
                int dPhone = Integer.parseInt(phone.getEditText().getText().toString().trim());
                String dAddress1 = address1.getEditText().getText().toString().trim();
                String dAddress2 = address2.getEditText().getText().toString().trim();
                String dAddress3= address3.getEditText().getText().toString().trim();
                String dPassword = password.getEditText().getText().toString().trim();

                User user = new User(userId, userType, dUsername, dEmail, dPhone, dAddress1, dAddress2,dAddress3,dPassword);


                FirebaseDatabase database = FirebaseDatabase.getInstance("https://sesathaandroid-default-rtdb.asia-southeast1.firebasedatabase.app/");
                DatabaseReference myRef = database.getReference("Users");


                myRef.child(userId).setValue(user);
                Log.e("login", "User registered !");
                Toast.makeText(RegisterActivity.this,
                        "Registration Successful", Toast.LENGTH_SHORT).show();
            }
        });
    }
}