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

public class Payment_Edit_acvtivity extends AppCompatActivity {

    DrawerLayout mdrawerLayout;
    ActionBarDrawerToggle mToggle;
    Toolbar mytoolbar;
    private Button submitButton;

    //payment activity
    private EditText cardNo;
    private EditText cardHolder;
    private EditText cardYr;
    private EditText cardMonth;
    private EditText cardCCV;
    private RadioGroup radioGroup;
    private RadioButton radioButton;

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
        cardNo = (EditText) findViewById(R.id.ETno);
        cardHolder = (EditText) findViewById(R.id.ETcardholder);
        cardYr = (EditText) findViewById(R.id.ETyear);
        cardMonth = (EditText) findViewById(R.id.ETmonth);
        cardCCV = (EditText) findViewById(R.id.ETccv);


        //radio button lister call
        addListenerOnButton();



    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    };

    public void addListenerOnButton() {

        radioGroup = (RadioGroup) findViewById(R.id.rd_cardtype);
        submitButton = (Button) findViewById(R.id.payment_edit);

        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);

                Toast.makeText(Payment_Edit_acvtivity.this,
                        radioButton.getText(), Toast.LENGTH_SHORT).show();

            }

        });

    }


}