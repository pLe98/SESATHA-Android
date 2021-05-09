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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
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

    private String vtitle , vitemNo;
    private int vqty = 1;
    private float vunitPrice, vshipping = (float) 150.00;

    //xml values
    TextView titleTv, unitPriceTv, shippingTv, sellerNameTv, locationTv, qtyTv,description;
    Button addToCart, plus, minus;
    ImageView image;
    //private TextInputLayout  qtyTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_description);

        globalVariables = (GlobalClass) getApplicationContext();
        userID = globalVariables.getUser().getUserID();
        Item model= (Item) getIntent().getSerializableExtra("Data");  //Item object from recycler view

        //Drawer
        mytoolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(mytoolbar);
        mdrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mdrawerLayout, R.string.open, R.string.close);
        mdrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Customize action bar
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.grey));

        titleTv = findViewById(R.id.itd_title);
        unitPriceTv = findViewById(R.id.itd_price);
        qtyTv = findViewById(R.id.itd_qty);
        shippingTv = findViewById(R.id.itd_shipping);
        sellerNameTv = findViewById(R.id.itd_sellerName);
        locationTv = findViewById(R.id.itd_sellerlocation);
        plus = findViewById(R.id.itd_addbtn);
        minus = findViewById(R.id.itd_removebtn);
        addToCart = findViewById(R.id.itd_addtocart);
        description = findViewById(R.id.itd_descrition);
        image = findViewById(R.id.itemImg);

        titleTv.setText(model.getTitle());
        unitPriceTv.setText(String.format("%.2f",model.getPrice()));
        qtyTv.setText(String.valueOf(vqty));
        shippingTv.setText(String.valueOf(vshipping));
        description.setText(model.getDescription());
        Glide.with(ItemDescriptionActivity.this)
                .load(model.getImUrl())
                .placeholder(R.drawable.image_default) // any placeholder to load at start
                .error(R.drawable.image_broken)
                .transform(new CenterInside(),new RoundedCorners(45))
                .into(image);

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

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vqty += 1;
                qtyTv.setText(String.valueOf(vqty));
            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database = FirebaseDatabase.getInstance("https://sesathaandroid-default-rtdb.asia-southeast1.firebasedatabase.app/");
                DatabaseReference myRef = database.getReference("Cart");

                vitemNo = model.getId();
                vtitle = model.getTitle();
                vunitPrice = model.getPrice();
                
                Cart cart = new Cart(vitemNo, vtitle, vqty, vunitPrice, vshipping);

                myRef.child(userID).child(vitemNo).setValue(cart);

                Toast.makeText(ItemDescriptionActivity.this,
                        "Item Added to the Cart Successfully ", Toast.LENGTH_SHORT).show();
                finish();
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