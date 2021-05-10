package com.requests.sesatha_mad_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.requests.sesatha_mad_android.models.User;

import java.util.concurrent.TimeUnit;


public class RegisterActivity extends AppCompatActivity {

    //declaring variables
    TextInputLayout userName, email, phone, address1, address2,address3,password,password2;
    Button registerSubmit;
    String userType;


    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Bundle extras = getIntent().getExtras();
        userType = extras.getSerializable("userType").toString().trim();

        //getting values from user
        userName =  findViewById(R.id.regUsername);
        email =  findViewById(R.id.regEmail);
        phone = findViewById(R.id.regPhone);
        address1 = findViewById(R.id.regAddress1);
        address2 = findViewById(R.id.regAddress2);
        address3 = findViewById(R.id.regAddress3);
        password = findViewById(R.id.regPassword);
        password2 = findViewById(R.id.regPassword2);
        registerSubmit = (Button) findViewById(R.id.btnPost);

        registerSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get details from user
                registerUser();




                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void registerUser(){

        String userId = "U" + String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        String dUsername = userName.getEditText().getText().toString().trim();
        String dEmail = email.getEditText().getText().toString().trim();
        int dPhone = Integer.parseInt(phone.getEditText().getText().toString().trim());
        String dAddress1 = address1.getEditText().getText().toString().trim();
        String dAddress2 = address2.getEditText().getText().toString().trim();
        String dAddress3= address3.getEditText().getText().toString().trim();
        String dPassword = password.getEditText().getText().toString().trim();
        String dPassword2 = password2.getEditText().getText().toString().trim();

        //form validations
        if(dUsername.isEmpty()){
            userName.setError("Username is Required");
            userName.requestFocus();
        }else{
            userName.setError(null);
            userName.setErrorEnabled(false);

            if (dEmail.isEmpty()){
                email.setError("Email is Required");
                email.requestFocus();
            }else{
                email.setError(null);
                email.setErrorEnabled(false);

                if(String.valueOf(dPhone).isEmpty()){
                    phone.setError("Email is Required");
                    phone.requestFocus();
                }else{
                    phone.setError(null);
                    phone.setErrorEnabled(false);

                    if(dAddress1.isEmpty()){
                        address1.setError("Address is Required");
                        address1.requestFocus();
                    }else{
                        address1.setError(null);
                        address1.setErrorEnabled(false);

                        if(dAddress2.isEmpty()){
                            address2.setError("Address is Required");
                            address2.requestFocus();
                        }else{
                            address2.setError(null);
                            address2.setErrorEnabled(false);

                            if(dAddress3.isEmpty()){
                                address3.setError("Address is Required");
                                address3.requestFocus();
                            }else{
                                address3.setError(null);
                                address3.setErrorEnabled(false);

                                if(dPassword.isEmpty()){
                                    password.setError("Password is Required");
                                    password.requestFocus();
                                }else{
                                    password.setError(null);
                                    password.setErrorEnabled(false);

                                    if(dPassword2.isEmpty()){
                                        password2.setError("Password is Required");
                                        password2.requestFocus();
                                    }else{
                                        password2.setError(null);
                                        password2.setErrorEnabled(false);

                                        if(dPassword.equals(dPassword2) == false){
                                            password.setError("Password Does Not Mach");
                                            password2.setError("Password Does Not Mach");
                                            password.requestFocus();
                                            password2.requestFocus();
                                        }else{
                                            password.setError(null);
                                            password.setErrorEnabled(false);
                                            password2.setError(null);
                                            password2.setErrorEnabled(false);

                                            //save user data in user object
                                            User user = new User(userId, userType, dUsername, dEmail, dPhone, dAddress1, dAddress2,dAddress3,dPassword);

                                            //getting firebase instance
                                            FirebaseDatabase database = FirebaseDatabase.getInstance("https://sesathaandroid-default-rtdb.asia-southeast1.firebasedatabase.app/");
                                            DatabaseReference myRef = database.getReference("Users");

                                            //save user object on firebase
                                            myRef.child(userId).setValue(user);
                                            Log.e("login", "User registered !");
                                            Toast.makeText(RegisterActivity.this,
                                                    "Registration Successful", Toast.LENGTH_SHORT).show();
                                            finish();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


}