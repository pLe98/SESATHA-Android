package com.requests.sesatha_mad_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.requests.sesatha_mad_android.models.Order;
import com.requests.sesatha_mad_android.models.Cart;
import com.requests.sesatha_mad_android.models.Transaction;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CheckoutActivity extends AppCompatActivity {

    //toolbar
    Toolbar mytoolbar;

    //firebase instances
    FirebaseDatabase database;
    DatabaseReference dbRef, dbRef2;

    //checkout activity
    private Button addressChange, paymentChange, purchase;
    private TextView nametv, add1tv, add2tv, add3tv, phonetv, cardnotv, cardExptv,  subTotaltv, noItemstv, shippingtv, totaltv, totaltv2 ;
    Query orderDb;


    //card details
    GlobalClass globalVariables;
    private String userid, cardno, cmonth, cyear;

    //variables for extras
    private Float subtotal, shipping, total;
    private int noOfItems;

    private String transactionID;

    //variables for individual cart items
    private String iitemNo, ititle, istatus;
    private Float isubtotal, ishipping, itotal;
    private int inoOfItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        //user details
        globalVariables = (GlobalClass) getApplicationContext();
        userid = globalVariables.getUser().getUserID();

        DateFormat dateFormat1 = new SimpleDateFormat("ddMMyyyyHHmmss");
        Date date = new Date();
        String oDate = dateFormat1.format(date);

        mytoolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setTitle("Order Confirmation");

        //getting cart details from extras
        Bundle bundle = getIntent().getExtras();
        subtotal = bundle.getFloat("subtotal");
        total = bundle.getFloat("total");
        shipping = bundle.getFloat("shipping");
        noOfItems = bundle.getInt("noOfTItems");
        transactionID = userid + oDate;

        //xml values
        nametv = findViewById(R.id.ch_name);
        add1tv = findViewById(R.id.ch_address1);
        add2tv= findViewById(R.id.ch_address2);
        add3tv = findViewById(R.id.ch_address3);
        phonetv = findViewById(R.id.ch_phone);

        cardnotv = findViewById(R.id.ch_cardNo);
        cardExptv = findViewById(R.id.ch_exp);
        //cardCCVtv = findViewById(R.id.ch_ccv);

        subTotaltv = findViewById(R.id.ch_subtotal);
        totaltv = findViewById(R.id.ch_total);
        shippingtv = findViewById(R.id.ch_shipping);
        noItemstv = findViewById(R.id.ch_noItems);

        totaltv2 = findViewById(R.id.ch_totalFinal);

        //button actions
        addressChange = findViewById(R.id.chgAddress);
        paymentChange = findViewById(R.id.chgCard);
        purchase = findViewById(R.id.puchasebtn);

        //firebase instances
        database = FirebaseDatabase.getInstance("https://sesathaandroid-default-rtdb.asia-southeast1.firebasedatabase.app/");
        dbRef = database.getReference("Orders");


        //display
        showCardDetails();
        //display user data
        showUserDetails();
        //show checkout details
        showCheckout();

        //open edit profile activity
        addressChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });

        //open payment add activity
        paymentChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckoutActivity.this, AddPaymentActivity.class);
                startActivity(intent);
            }
        });
        Log.d("checkout", "-1");
        //place order
        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("checkout", "0");
                placeOrder();
                saveTransaction();
                clearCart();
            }
        });
    }

    public void showCardDetails(){
        DatabaseReference db = FirebaseDatabase.getInstance("https://sesathaandroid-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
        db.child("Card").orderByChild("userID").equalTo(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Log.e("card details", "card found");
                    //retrive values from db
                    String cardNum = snapshot.child(userid).child("cardNo").getValue().toString();
                    cardno = "XXXX XXXX XXXX "+cardNum.substring(cardNum.length()-4);
                    cmonth = snapshot.child(userid).child("cMonth").getValue().toString();
                    cyear = snapshot.child(userid).child("cYear").getValue().toString();

                    //assign into text views
                    cardnotv.setText(cardno);
                    cardExptv.setText(cmonth + " / " + cyear);

                }else{
                    Log.e("card details", "no card");
                    String cNo = "XXXX XXXX XXXX XXXX";
                    cardnotv.setText(cNo);
                    cardExptv.setText( "XX / XX");
                    //cardCCVtv.setText("XXX");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void showUserDetails(){
        //getting user data from global variable and display in text view

        nametv.setText(globalVariables.getUser().getUserName());
        add1tv.setText(globalVariables.getUser().getAddress1());
        add2tv.setText(globalVariables.getUser().getAddress2());
        add3tv.setText(globalVariables.getUser().getAddress3());
        phonetv.setText(String.valueOf(globalVariables.getUser().getPhone()));

    }

    public void showCheckout(){

        subTotaltv.setText(String.valueOf(subtotal));
        totaltv.setText(String.valueOf(total));
        shippingtv.setText(String.valueOf(shipping));
        noItemstv.setText(String.valueOf(noOfItems));
    }

    public void placeOrder(){
        //getting order instance
        orderDb = FirebaseDatabase.getInstance("https://sesathaandroid-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Cart").child(userid);

        String orderID = "O" + String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));

        //getting date
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Date date = new Date();
        String orderDate = dateFormat.format(date);

        //setting default order status
        istatus = "Order Placed";

        orderDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshots : snapshot.getChildren()){
                    Log.e("checkout", "order");

                    //getting cart items relevant to the userID
                    isubtotal  = dataSnapshots.child("unitPrice").getValue(Float.class) * dataSnapshots.child("qty").getValue(Integer.class);
                    ishipping  = dataSnapshots.child("shipping").getValue(Float.class);
                    itotal = isubtotal + ishipping;
                    inoOfItems = dataSnapshots.child("qty").getValue(Integer.class);
                    iitemNo = dataSnapshots.child("itemNo").getValue(String.class);
                    ititle = dataSnapshots.child("title").getValue(String.class);
                    Log.e("checkout", "itemNo"+ iitemNo);

                    //saving cart items in order object
                    // Order(String orderID, String itemNo, String userID, String title, String orderDate, int noOfItems, float netAmount, float shipping, float total, String status)
                    Order order = new Order(orderID, iitemNo, userid, ititle,  orderDate, inoOfItems, isubtotal, ishipping, itotal, istatus);

                    //save order object in firebase database
                    dbRef = database.getReference("Orders");
                    dbRef.child(iitemNo).setValue(order);

                    Log.e("checkout", "itemNo"+ iitemNo);
                }

                Toast.makeText(CheckoutActivity.this,
                        "Order was placed successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void saveTransaction(){
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Date date = new Date();
        String orderDate = dateFormat.format(date);

        //create Transaction object
        Transaction transaction = new Transaction(transactionID,orderDate, total);

        //save transaction object in firebase database
        dbRef = database.getReference("Transaction");
        dbRef.child(userid).child(transactionID).setValue(transaction);

    }

    public void clearCart(){
        //delete cart items relevant to user id
        database.getReference("Cart").child(userid).removeValue();
    }
}