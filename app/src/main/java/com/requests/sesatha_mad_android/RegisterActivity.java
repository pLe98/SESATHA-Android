package com.requests.sesatha_mad_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends AppCompatActivity {

    //declaring variables
    TextInputLayout userName, email, phone, address, password;
    Button registerSubmit;

    FirebaseDatabase rootNode;
    DatabaseReference reff;

    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //getting values from user
        userName =  findViewById(R.id.regUsername);
        email =  findViewById(R.id.regEmail);
        phone = findViewById(R.id.regPhone);
        address = findViewById(R.id.regAddress);
        password = findViewById(R.id.regPassword);
        registerSubmit = (Button) findViewById(R.id.regSubmit);

        registerSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //rootNode = FirebaseDatabase.getInstance();
                //reff = rootNode.getReference("Users");

                //reff.setValue("test");

                mDatabase = FirebaseDatabase.getInstance().getReference("Users");
                //User user = new User(userName, email, phone, address, password);
                mDatabase.child("Users").setValue("test");

                /*
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Users");
                myRef.setValue("aaa");
                 */

                Toast.makeText(RegisterActivity.this,
                        "Card Details Saved Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}