package com.requests.sesatha_mad_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;


public class sql extends AppCompatActivity {

    DrawerLayout mdrawerLayout;
    ActionBarDrawerToggle mToggle;
    Toolbar mytoolbar;

    //variables for firebase instances
    //DatabaseReference dbRef;
    //StorageReference storageRef;

    //Objects for elements
    /*private Button submiBtn;
    private TextInputLayout title,description,price;
    private Spinner CatSelector;
    private ImageView imView;
    Uri imUrI;
    private String pImUrl = null;
    private ProgressBar progressBar;
    private TextView feeTxt;*/

    EditText name,desc,price;
    Button create,update,delete,viewAll;
    String name_txt = null;
    String desc_txt =null;
    float price_txt = 0.00f;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);

        Bundle extras = getIntent().getExtras();
        String intStr = extras.getString("Key");
        TextView hdr = findViewById(R.id.sql_hdr);
        hdr.setText(intStr);


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

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.grey));

        name = findViewById(R.id.input_name);
        desc = findViewById(R.id.input_desc);
        price = findViewById(R.id.input_price);

        create = findViewById(R.id.db_create);
        update = findViewById(R.id.db_edit);
        delete = findViewById(R.id.db_delete);
        viewAll = findViewById(R.id.db_view);

        db = new DBHelper(this);  //create instance of our DBHelper class

        create.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                name_txt = name.getText().toString();
                desc_txt = desc.getText().toString();
                price_txt = Float.parseFloat(price.getText().toString());

                db.createItem(name_txt,desc_txt,price_txt);  //create an entry in items table
                Toast.makeText(sql.this, "Item created !", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                name_txt = name.getText().toString();

                db.deleteItem(name_txt);       //delete item
                Toast.makeText(sql.this, "Item deleted !", Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                name_txt = name.getText().toString();
                desc_txt = desc.getText().toString();
                price_txt = Float.parseFloat(price.getText().toString());

                db.updateItem(name_txt,desc_txt,price_txt);     //update item
                Toast.makeText(sql.this, "Item updated !", Toast.LENGTH_SHORT).show();
            }
        });

        viewAll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Cursor cr = db.getAllItems();   //retrieve all table items

                if(cr.getCount()==0) {
                    Toast.makeText(sql.this, "No items in the table !", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuffer buf = new StringBuffer();

                //create a line separated string buffer to display
                while(cr.moveToNext()){
                    buf.append("Item ID : "+ cr.getString(0)+"\n");
                    buf.append("Item Name : "+ cr.getString(1)+"\n");
                    buf.append("Item Desc : "+ cr.getString(2)+"\n");
                    buf.append("Item Price : "+ cr.getString(3)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(sql.this);
                builder.setCancelable(true);
                builder.setTitle("All Items");
                builder.setMessage(buf.toString());
                builder.show();

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