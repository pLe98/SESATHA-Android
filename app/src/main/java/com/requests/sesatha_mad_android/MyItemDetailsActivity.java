package com.requests.sesatha_mad_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.requests.sesatha_mad_android.models.Cart;
import com.requests.sesatha_mad_android.models.Item;

public class MyItemDetailsActivity extends AppCompatActivity {

    //navigation bar variables
    DrawerLayout mdrawerLayout;
    ActionBarDrawerToggle mToggle;
    Toolbar mytoolbar;



    //Variables for elements
    TextView titleTv, unitPriceTv, description, category;
    Button deleteBtn, editBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_item_details);

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
        description = findViewById(R.id.itd_description);
        category = findViewById(R.id.itd_category);

        deleteBtn = findViewById(R.id.itd_deleteBtn);
        editBtn = findViewById(R.id.itd_editBtn);

        titleTv.setText(model.getTitle());
        unitPriceTv.setText(String.format("%.2f",model.getPrice()));
        description.setText(model.getDescription());
        category.setText(model.getCategory());

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new MaterialAlertDialogBuilder(MyItemDetailsActivity.this,R.style.AlertDialogThemeCustom)
                        .setTitle("Are you sure want to remove this item ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d("Item","Deleting item.....");
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d("Item","Editing item.....");
                            }
                        })
                        .show();
                FirebaseDatabase database = FirebaseDatabase.getInstance("https://sesathaandroid-default-rtdb.asia-southeast1.firebasedatabase.app/");
                DatabaseReference myRef = database.getReference("Cart");

                //Cart cart = new Cart(vitemNo, vtitle, vqty, vunitPrice, vshipping);

                //myRef.child(userID).child(vitemNo).setValue(cart);


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