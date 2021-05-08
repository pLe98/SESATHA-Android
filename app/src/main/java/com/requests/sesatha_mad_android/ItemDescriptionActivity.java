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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.requests.sesatha_mad_android.models.Cart;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.requests.sesatha_mad_android.models.Item;

public class ItemDescriptionActivity extends AppCompatActivity {

    //navigation bar variables
    DrawerLayout mdrawerLayout;
    ActionBarDrawerToggle mToggle;
    Toolbar mytoolbar;

    private String userID;
    GlobalClass globalVariables;

    private String vtitle = "Necklace", vitemNo = "24943";
    private int vqty = 1;
    private float vunitPrice = (float) 500.00, vshipping = (float) 150.00;

    //xml values added
    TextView titleTv, unitPriceTv, shippingTv, sellerNameTv, locationTv, qtyTv;
    Button addToCart, plus, minus;
    //private TextInputLayout  qtyTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_description);

        //inertialize ids
        globalVariables = (GlobalClass) getApplicationContext();
        userID = globalVariables.getUser().getUserID();
        Item model= (Item) getIntent().getSerializableExtra("Data");  //Item object from recycler view

        mytoolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(mytoolbar);
        mdrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mdrawerLayout, R.string.open, R.string.close);
        mdrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        titleTv = findViewById(R.id.itd_title);
        unitPriceTv = findViewById(R.id.itd_price);
        qtyTv = findViewById(R.id.itd_qty);
        shippingTv = findViewById(R.id.itd_shipping);
        sellerNameTv = findViewById(R.id.itd_sellerName);
        locationTv = findViewById(R.id.itd_sellerlocation);
        plus = findViewById(R.id.itd_addbtn);
        minus = findViewById(R.id.itd_removebtn);
        addToCart = findViewById(R.id.itd_addtocart);

        titleTv.setText(vtitle);
        unitPriceTv.setText(String.valueOf(vunitPrice));
        qtyTv.setText(String.valueOf(vqty));
        shippingTv.setText(String.valueOf(vshipping));

        //minus function
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vqty < 2){
                    minus.setEnabled(false);
                }else{
                    vqty -= 1;
                    qtyTv.setText(String.valueOf(vqty));
                }
            }
        });

        //plus function
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vqty += 1;
                qtyTv.setText(String.valueOf(vqty));
            }
        });

        //add cart function
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //calling to firebase databse

                FirebaseDatabase database = FirebaseDatabase.getInstance("https://sesathaandroid-default-rtdb.asia-southeast1.firebasedatabase.app/");
                DatabaseReference myRef = database.getReference("Cart");

                Cart cart = new Cart(vitemNo, vtitle, vqty, vunitPrice, vshipping);

                myRef.child(userID).child(vitemNo).setValue(cart);

                Toast.makeText(ItemDescriptionActivity.this,
                        "Item Added to the Cart Successfully ", Toast.LENGTH_SHORT).show();
            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}