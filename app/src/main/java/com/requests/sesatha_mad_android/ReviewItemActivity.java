package com.requests.sesatha_mad_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.requests.sesatha_mad_android.models.Item;

public class ReviewItemActivity extends AppCompatActivity {

    //navigation bar variables
    DrawerLayout mdrawerLayout;
    ActionBarDrawerToggle mToggle;
    Toolbar mytoolbar;


    //Variables for elements
    TextView titleTv, unitPriceTv, description, category, lastModified;
    Button dismissBtn, updateBtn;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_item);

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
        lastModified = findViewById(R.id.itd_lastmodified);

        dismissBtn = findViewById(R.id.itd_deleteBtn);
        updateBtn = findViewById(R.id.itd_editBtn);

        image = findViewById(R.id.itemImg);

        //Set values
        titleTv.setText(model.getTitle());
        unitPriceTv.setText(String.format("%.2f",model.getPrice()));
        description.setText(model.getDescription());
        category.setText(model.getCategory());
        lastModified.setText("Modified : "+model.getLast_modified());
        Glide.with(ReviewItemActivity.this)
                .load(model.getImUrl())
                .placeholder(R.drawable.image_default) // any placeholder to load at start
                .error(R.drawable.image_broken)
                .transform(new CenterInside(),new RoundedCorners(45))
                //.circleCrop()
                .into(image);

        dismissBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(ReviewItemActivity.this,R.style.AlertDialogThemeCustom)
                        .setTitle("Are you sure want to dismiss this item ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d("Item","Deleting item.....");
                                FirebaseDatabase database = FirebaseDatabase.getInstance("https://sesathaandroid-default-rtdb.asia-southeast1.firebasedatabase.app/");
                                Query myRef = database.getReference("items").orderByChild("id").equalTo(model.getId());
                                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for(DataSnapshot itemSnap: snapshot.getChildren()){
                                            itemSnap.getRef().removeValue();
                                        }
                                        Toast.makeText(ReviewItemActivity.this, "Item dismissed !", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        Log.e("Item", "DB Error. Failed to delete !", error.toException());
                                    }
                                });
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(ReviewItemActivity.this,R.style.AlertDialogThemeCustom)
                        .setTitle("Are you sure want to approve this item ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d("Item","Approving item.....");
                                FirebaseDatabase database = FirebaseDatabase.getInstance("https://sesathaandroid-default-rtdb.asia-southeast1.firebasedatabase.app/");
                                Query myRef = database.getReference("items").orderByChild("id").equalTo(model.getId());
                                model.setApproved(true);
                                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for(DataSnapshot itemSnap: snapshot.getChildren()){
                                            itemSnap.getRef().setValue(model);
                                        }
                                        Toast.makeText(ReviewItemActivity.this, "Item status updated !", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        Log.e("Item", "DB Error. Failed to delete !", error.toException());
                                    }
                                });
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
            }
        });
    }
}