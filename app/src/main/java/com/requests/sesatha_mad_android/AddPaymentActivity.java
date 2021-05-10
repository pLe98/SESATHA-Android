package com.requests.sesatha_mad_android;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.requests.sesatha_mad_android.models.Card;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AddPaymentActivity extends AppCompatActivity {

    private TextInputLayout enCardholder, enCardno, enMonth, enYear, enCcv;
    Button addCard;
    GlobalClass globalVariables;
    private String userID;

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
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.grey));



        //user details from global variable
        globalVariables = (GlobalClass) getApplicationContext();
        userID = globalVariables.getUser().getUserID();

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

                addCard();

            }
        });


    }

    public void addCard(){



        //get current date
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int currMonth = localDate.getMonthValue();
        String currYr = String.valueOf(localDate.getYear());
        int currYear = Integer.parseInt(currYr.substring(currYr.length()-2));

        //assign user entered data into variables
        String cardHolder = enCardholder.getEditText().getText().toString().trim();
        String cardNo = enCardno.getEditText().getText().toString().trim();
        String month = enMonth.getEditText().getText().toString().trim();
        String cYear = enYear.getEditText().getText().toString().trim();
        String ccv = enCcv.getEditText().getText().toString().trim();

        //validate card holder
        if(cardHolder.isEmpty()){
            enCardholder.setError("Card Holder's Name is Required");
            enCardholder.requestFocus();
        }else{
            enCardholder.setError(null);
            enCardholder.setErrorEnabled(false);

            //validate card number
            if(cardNo.isEmpty()){
                enCardno.setError("Card Number is Required");
                enCardno.requestFocus();
            }else if(cardNo.length() < 15){

                enCardno.setError("Enter Valid Card Number");
                enCardno.requestFocus();
            }else{
                enCardno.setError(null);
                enCardno.setErrorEnabled(false);

                //validate month
                if(month.isEmpty()){
                    enMonth.setError("Required");
                    enMonth.requestFocus();
                }else{
                    if(Integer.parseInt(month) > 12){
                    enMonth.setError("Invalid");
                    enMonth.requestFocus();
                    }else{
                        enMonth.setError(null);
                        enMonth.setErrorEnabled(false);

                        //validate year
                        if(cYear.isEmpty()){
                            enYear.setError("Required");
                            enYear.requestFocus();
                        }else {
                                if(Integer.parseInt(cYear) < currYear){
                                enYear.setError("Invalid");
                                enYear.requestFocus();
                            }
                            else if((Integer.parseInt(cYear) == currYear) && (Integer.parseInt(month) <= currMonth)){
                                enYear.setError("Invalid");
                                enYear.requestFocus();
                                enMonth.setError("Invalid");
                                enMonth.requestFocus();
                            }else if(cYear.length() != 2){
                                enCcv.setError("Invalid");
                                enCcv.requestFocus();
                            }else{
                                enYear.setError(null);
                                enYear.setErrorEnabled(false);

                                //validate ccv
                                if(ccv.isEmpty()){
                                    enCcv.setError("Required");
                                    enCcv.requestFocus();
                                }else if(ccv.length() != 3){
                                    enCcv.setError("Invalid");
                                    enCcv.requestFocus();
                                }else{
                                    enCcv.setError(null);
                                    enCcv.setErrorEnabled(false);

                                    //assign user entered data into a card object
                                    Card card = new Card( cardHolder, cardNo, month, cYear, ccv, userID);

                                    //getting firebase instance
                                    FirebaseDatabase database = FirebaseDatabase.getInstance("https://sesathaandroid-default-rtdb.asia-southeast1.firebasedatabase.app/");
                                    DatabaseReference myRef = database.getReference("Card");

                                    //save card objec in firebase
                                    myRef.child(userID).setValue(card);

                                    //clearing data fields
                                    enCardholder.getEditText().getText().clear();
                                    enCardno.getEditText().getText().clear();
                                    enMonth.getEditText().getText().clear();
                                    enYear.getEditText().getText().clear();
                                    enCcv.getEditText().getText().clear();

                                    //success message
                                    Toast.makeText(AddPaymentActivity.this,
                                            "Card Details Added Successfully", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(AddPaymentActivity.this, PaymentActivity.class);
                                    startActivity(intent);

                                }
                            }
                    }   }
                }}
            }
        }
    }
