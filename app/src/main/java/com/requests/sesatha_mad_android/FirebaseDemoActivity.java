package com.requests.sesatha_mad_android;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseDemoActivity extends AppCompatActivity {

    //navigation bar variables
    DrawerLayout mdrawerLayout;
    ActionBarDrawerToggle mToggle;
    Toolbar mytoolbar;
    //navigation bar variables


    //firebase variables
    FirebaseDatabase myDatabase;
    DatabaseReference myRef;


    //button for sending data
    Button button;
    EditText valueET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_demo);

        //navigation bar

        mytoolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(mytoolbar);
        mdrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mdrawerLayout, R.string.open, R.string.close);
        mdrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //navigation bar


        //getting values from user
        button = findViewById(R.id.button);
        valueET = findViewById(R.id.editText);

        //onclick listner for buton

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //editText to string
                String value = valueET.getEditableText().toString().trim();

                //firebase send data
                myDatabase = FirebaseDatabase.getInstance("https://sesathaandroid-default-rtdb.asia-southeast1.firebasedatabase.app/");
                myRef = myDatabase.getReference("test");

                myRef.setValue(value);
            }
        });
    }
}