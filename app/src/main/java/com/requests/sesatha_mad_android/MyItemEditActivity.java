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
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.iceteck.silicompressorr.SiliCompressor;
import com.requests.sesatha_mad_android.models.Item;

import java.io.File;
import java.util.UUID;

public class MyItemEditActivity extends AppCompatActivity {

    DrawerLayout mdrawerLayout;
    ActionBarDrawerToggle mToggle;
    Toolbar mytoolbar;

    //variables for firebase instances
    FirebaseDatabase dbRef;
    StorageReference storageRef;

    //Objects for elements
    private Button submiBtn;
    private TextInputLayout title,description,price;
    private Spinner CatSelector;
    private ImageView imView;
    Uri imUrI;
    private String pImUrl = null;
    private ProgressBar progressBar;

    //Other variables for objects
    //Item item;
    Uri cImUrI;
    GlobalClass globalVariables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_item_edit);

        //Get global class object
        globalVariables = (GlobalClass) getApplicationContext();
        Item model= (Item) getIntent().getSerializableExtra("Data");  //Item object from recycler view

        //toolbar
        mytoolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(mytoolbar);
        //getSupportActionBar().setTitle("Add Item");

        //navigation bar//navigation bar
        mdrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mdrawerLayout, R.string.open, R.string.close);
        mdrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //customize action bar
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.grey));

        //get firebase instance and initialize vars
        dbRef = FirebaseDatabase.getInstance("https://sesathaandroid-default-rtdb.asia-southeast1.firebasedatabase.app/");
        storageRef = FirebaseStorage.getInstance().getReference();

        //Set elements to objects
        submiBtn = findViewById(R.id.btnPost);
        title = findViewById(R.id.editTextTitle);
        CatSelector = findViewById(R.id.spinner);
        description = findViewById(R.id.editTextDesc);
        price = findViewById(R.id.editTextPrice);
        imView = findViewById(R.id.imageView);
        progressBar =findViewById(R.id.progressBar);

        //Set data to edit form
        title.getEditText().setText(model.getTitle());
        CatSelector.setSelection(getCatIndex(model.getCategory()));
        description.getEditText().setText(model.getDescription());
        price.getEditText().setText(String.valueOf(model.getPrice()));
        //Show imageMyItemEditActivity
        Glide.with(MyItemEditActivity.this)
                .load(model.getImUrl())
                .placeholder(R.drawable.image_default) // any placeholder to load at start
                .error(R.drawable.image_broken)
                .into(imView);


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

                    submiBtn.setEnabled(false);
                    model.updateItem(pTitle, pCat, pDesc, pPrice,model.getId());
                    if(imUrI != null){
                        uploadImage(imUrI,model);
                    }else{
                        progressBar.setVisibility(View.VISIBLE);
                        updateDB(model);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                }else{
                    Toast.makeText(MyItemEditActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        //Image onClick handler
        imView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,2);
            }
        });
    }

    //Executes when image is selected
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2 && resultCode == RESULT_OK && data != null){
            imUrI = data.getData();
            imView.setImageURI(imUrI);
        }
    }

    //Upload image to firebase and get url. Then store Item object in firebase rt db
    private void uploadImage(Uri imUrI,Item passedModel){
        progressBar.setVisibility(View.VISIBLE);
        StorageReference imFile = storageRef.child("item_images/"+ UUID.randomUUID().toString()+"."+getExtension(imUrI));

        //File comFile = null;
        try {
            //File oFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
            String cFilePath = SiliCompressor.with(MyItemEditActivity.this).compress(imUrI.toString(), new File(this.getCacheDir(), "temp"));
            cImUrI = Uri.parse(cFilePath);
            Log.d("Item","Image compressed.........");
        }catch (Exception e){
            e.printStackTrace();
        }

        //upload to firebase storage asynchronously
        imFile.putFile(cImUrI).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.d("Item","Image uploaded successfully !!!");
                imFile.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        pImUrl = uri.toString();
                        if(pImUrl != null){
                            Log.d("Item","Image url : "+ uri.toString());
                            passedModel.setImUrl(pImUrl);
                            updateDB(passedModel);

                            progressBar.setVisibility(View.INVISIBLE);
                            MyItemEditActivity.this.getContentResolver().delete(cImUrI,null,null);    //Delete compressed file after uploading
                            //Toast.makeText(MyItemEditActivity.this, "Item submitted for approval !", Toast.LENGTH_LONG).show();
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
                MyItemEditActivity.this.getContentResolver().delete(cImUrI,null,null);    //Delete compressed image after
                Toast.makeText(MyItemEditActivity.this, "Image uploading failed!", Toast.LENGTH_SHORT).show();
                Log.d("Item","Image uploading failed !!!");
            }
        });
    }

    //get image file extension
    private String getExtension(Uri imUrI){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(imUrI));
    }


    //For the drawer
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private int getCatIndex(String cat){
        if(cat.equals("Art & collectibles")){
            return 1;
        }else if(cat.equals("Jewellery & accessories")){
            return 2;
        }else if(cat.equals("Home & living")){
            return 3;
        }else if(cat.equals("Crafts & tools")){
            return 4;
        }else if(cat.equals("Clothing & shoes")){
            return 5;
        }else{
            return 0;
        }
    }

    private void updateDB(Item model){
        //dbRef.getReference().child("items").child(UUID.randomUUID().toString()).setValue(item);
        Query myRef = dbRef.getReference("items").orderByChild("id").equalTo(model.getId());
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot itemSnap: snapshot.getChildren()){
                    String itemKey = itemSnap.getRef().getKey();
                    dbRef.getReference().child("items").child(itemKey).setValue(model);
                    Log.d("Item","Successfully updated the listing !");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Item", "DB Error. Failed to retrieve item key !", error.toException());
            }
        });

        Toast.makeText(MyItemEditActivity.this, "Item updated !", Toast.LENGTH_LONG).show();
        final Intent intent = new Intent(MyItemEditActivity.this, MyItemsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}