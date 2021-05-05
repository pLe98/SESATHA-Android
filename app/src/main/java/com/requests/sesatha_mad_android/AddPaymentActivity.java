package com.requests.sesatha_mad_android;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPaymentActivity extends AppCompatActivity {

    private TextInputLayout enCardholder, enCardno, enMonth, enYear, enCcv;
    Button addCard;

    DrawerLayout mdrawerLayout;
    ActionBarDrawerToggle mToggle;
    Toolbar mytoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);

        //navigation bar

        mytoolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(mytoolbar);
        mdrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mdrawerLayout, R.string.open, R.string.close);
        mdrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //navigation bar

        //getting input values
        enCardholder = findViewById(R.id.et_cardholder);
        enCardno = findViewById(R.id.et_cardno);
        enMonth = findViewById(R.id.et_month);
        enYear = findViewById(R.id.et_year);
        enCcv = findViewById(R.id.et_ccv);
        addCard = findViewById(R.id.add_card);

        addCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cardHolder = enCardholder.getEditText().getText().toString().trim();
                String cardNo = enCardno.getEditText().getText().toString().trim();
                String month = enMonth.getEditText().getText().toString().trim();
                String cYear = enYear.getEditText().getText().toString().trim();
                String ccv = enCcv.getEditText().getText().toString().trim();

                Card card = new Card(cardHolder, cardNo, month, cYear, ccv);

                FirebaseDatabase database = FirebaseDatabase.getInstance("https://sesathaandroid-default-rtdb.asia-southeast1.firebasedatabase.app/");
                DatabaseReference myRef = database.getReference("Card");

                myRef.child(cardNo).setValue(card);

                Toast.makeText(AddPaymentActivity.this,
                        "Card Details Added Successfully", Toast.LENGTH_SHORT).show();
            }
        });


    }
}