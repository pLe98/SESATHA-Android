package com.requests.sesatha_mad_android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class PostItem extends AppCompatActivity {



    DrawerLayout mdrawerLayout;
    ActionBarDrawerToggle mToggle;
    Toolbar mytoolbar;

    //variables for firebase instances
    DatabaseReference dbRef;
    StorageReference storageRef;

    //Objects for elements
    private Button submiBtn;
    private TextInputLayout title,description,price;
    private Spinner CatSelector;
    private ImageView imView;
    private Uri imUrI;
    private String pImUrl = null;
    private ProgressBar progressBar;

    //Other variables for objects
    Item item;
    GlobalClass globalVariables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_item);

        //Get global class object
        globalVariables = (GlobalClass) getApplicationContext();

        //toolbar
        mytoolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setTitle("Add Item");

        //navigation bar//navigation bar
        mdrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mdrawerLayout, R.string.open, R.string.close);
        mdrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get firebase instance
        dbRef = FirebaseDatabase.getInstance("https://sesathaandroid-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
        storageRef = FirebaseStorage.getInstance().getReference();


        //Set elements to objects
        submiBtn = findViewById(R.id.btnPost);
        title = findViewById(R.id.editTextTitle);
        CatSelector = findViewById(R.id.spinner);
        description = findViewById(R.id.editTextDesc);
        price = findViewById(R.id.editTextPrice);
        imView = findViewById(R.id.imageView);
        progressBar =findViewById(R.id.progressBar);

        submiBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String pTitle = title.getEditText().getText().toString().trim();
                    String pCat = CatSelector.getSelectedItem().toString();
                    String pDesc = description.getEditText().getText().toString().trim();
                    String pPriceStr = price.getEditText().getText().toString().trim();
                    Float pPrice = null;
                    if (pPriceStr.length()>0){
                        pPrice = Float.parseFloat(price.getEditText().getText().toString().trim());
                    }

                    if(pTitle.length() > 0 && pCat.length() > 0 && pDesc.length() > 0 && pPrice != null && !pCat.equals("Category")){
                        if(imUrI != null){
                            uploadImage(imUrI);
                            submiBtn.setEnabled(false);
                            Log.d("Item",pCat);
                            item = new Item(pTitle, pCat, pDesc, pPrice, globalVariables.getUserId());

                        }else{
                            Toast.makeText(PostItem.this, "Please select an image", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(PostItem.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
        }
        );

        imView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2 && resultCode == RESULT_OK && data != null){
            imUrI = data.getData();
            imView.setImageURI(imUrI);
        }
    }

    private void uploadImage(Uri imUrI){
        progressBar.setVisibility(View.VISIBLE);
        StorageReference imFile = storageRef.child("item_images/"+UUID.randomUUID().toString()+"."+getExtension(imUrI));
        imFile.putFile(imUrI).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.d("Item","Image uploaded successfully !!!");
                imFile.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        pImUrl = uri.toString();
                        if(pImUrl != null){
                            Log.d("Item","Image url found : "+ uri.toString());
                            item.setImUrl(pImUrl);
                            dbRef.child("items").child(UUID.randomUUID().toString()).setValue(item);
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(PostItem.this, "Item is submitted for approval !", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                Log.d("Item","Image uploading.....");
                progressBar.setVisibility(View.VISIBLE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.INVISIBLE);
                submiBtn.setEnabled(true);
                Toast.makeText(PostItem.this, "Image uploading failed!", Toast.LENGTH_SHORT).show();
                Log.d("Item","Image uploading failed !!!");
            }
        });
    }

    private String getExtension(Uri imUrI){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(imUrI));
    }
}