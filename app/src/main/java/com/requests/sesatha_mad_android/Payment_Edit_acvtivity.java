package com.requests.sesatha_mad_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Payment_Edit_acvtivity extends AppCompatActivity {

    DrawerLayout mdrawerLayout;
    ActionBarDrawerToggle mToggle;
    Toolbar mytoolbar;

    //payment activity
    private EditText cardNoET;
    private EditText cardHolderET;
    private EditText cardYrET;
    private EditText cardMonthET;
    private EditText cardCCVET;
    //private RadioGroup radioGroup;
    //private RadioButton radioButton;
    private Button submitButton;
    DatabaseReference refs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment__edit_acvtivity);

        //navigation bar

        mytoolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(mytoolbar);
        mdrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mdrawerLayout, R.string.open, R.string.close);
        mdrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //navigation bar


        //payment edit assign variables
        cardNoET = (EditText) findViewById(R.id.ETno);
        cardHolderET = (EditText) findViewById(R.id.ETcardholder);
        cardYrET = (EditText) findViewById(R.id.ETyear);
        cardMonthET = (EditText) findViewById(R.id.ETmonth);
        cardCCVET = (EditText) findViewById(R.id.ETccv);
        submitButton = (Button) findViewById(R.id.payment_editbt);

        CardDetails cardDetails = new CardDetails();

        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        //refs = Firebase.database.getReference("CardDetails");


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get selected radio button from radioGroup
                //int selectedId = radioGroup.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                //radioButton = (RadioButton) findViewById(selectedId);

                int cardNo = Integer.parseInt(cardNoET.getText().toString().trim());
                int year = Integer.parseInt(cardYrET.getText().toString().trim());
                int month = Integer.parseInt(cardMonthET.getText().toString().trim());
                int ccv = Integer.parseInt(cardCCVET.getText().toString().trim());

                //calling setter
                cardDetails.setCardHolder(cardHolderET.getText().toString().trim());
                //cardDetails.setCardType(radioButton.getText().toString().trim());
                cardDetails.setCardNo(cardNo);
                cardDetails.setYear(year);
                cardDetails.setMonth(month);
                cardDetails.setCcv(ccv);

                //refs.push().setValue(cardDetails);
                mDatabase.child("cardDetails").child(String.valueOf(cardNo)).setValue(cardDetails);

                Toast.makeText(Payment_Edit_acvtivity.this,
                        "Card Details Saved Successfully", Toast.LENGTH_SHORT).show();
            }
        });


    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    };


}