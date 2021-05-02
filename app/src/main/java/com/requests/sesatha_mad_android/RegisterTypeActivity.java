package com.requests.sesatha_mad_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class RegisterTypeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_type);

        RelativeLayout buyer = (RelativeLayout) findViewById(R.id.buyerlayout);
        RelativeLayout seller = (RelativeLayout) findViewById(R.id.sellerlayout);

        buyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterTypeActivity.this, RegisterActivity.class);
                intent.putExtra("userType", "buyer");
                startActivity(intent);
            }
        });

        seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterTypeActivity.this, RegisterActivity.class);
                intent.putExtra("userType", "seller");
                startActivity(intent);
            }
        });

    }

}