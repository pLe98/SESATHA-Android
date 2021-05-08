package com.requests.sesatha_mad_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.requests.sesatha_mad_android.models.User;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout enteredEmail, enteredPassword;
    private Button login, forgotPw, register;
    GlobalClass globalVariables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        enteredEmail = findViewById(R.id.loginEmail);
        enteredPassword = findViewById(R.id.loginPassword);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        forgotPw = (Button) findViewById(R.id.forgotPw);

        //Get global class object
        globalVariables = (GlobalClass) getApplicationContext();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterTypeActivity.class);
                startActivity(intent);

            }
        });
        forgotPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, PasswordRestActivity.class);
                startActivity(intent);
            }
        });
    }

    public void loginUser(){

        String inputEmail = enteredEmail.getEditText().getText().toString().trim();
        String inputPassword = enteredPassword.getEditText().getText().toString().trim();
        DatabaseReference db = FirebaseDatabase.getInstance("https://sesathaandroid-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
        db.child("Users").orderByChild("email").equalTo(inputEmail).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(!task.isSuccessful()){
                    Log.e("login", "Error getting user data", task.getException());
                }else{
                    Log.e("login", "Retrieved user data");

                    if (task.getResult().hasChildren()) {
                        Log.e("login", task.getResult().getValue().toString());
                        Log.e("login", task.getResult().getChildren().iterator().next().getValue().toString());
                        User usr = task.getResult().getChildren().iterator().next().getValue(User.class);

                        //Verify user credentials
                        if(usr.getPassword().equals(inputPassword)){
                            globalVariables.setUser(usr); //after setting this user object is accessible from all activities
                            Log.e("login", "Successfully logged in !");
                            Toast.makeText(LoginActivity.this,
                                    "Login Successful", Toast.LENGTH_SHORT).show();
                            Log.e("login", "User : "+ globalVariables.getUser().getUserName()); //example usage of globalvariables
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }else{
                            Log.e("login", "Incorrect password !");
                            enteredPassword.setError("Invalid Email/Password");
                            enteredPassword.requestFocus();
                        }
                    } else {
                        Log.e("login", "Email not found !");
                        enteredPassword.setError("Invalid Email/Password");
                        enteredEmail.requestFocus();
                        enteredPassword.requestFocus();
                    }
                    enteredPassword.getEditText().getText().clear();

                }
            }
        });


        /*
        DatabaseReference reff = FirebaseDatabase.getInstance("https://sesathaandroid-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("Users");
        Query checkEmail = reff.orderByChild("email").equalTo(inputEmail);

        checkEmail.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    enteredEmail.setError(null);
                    enteredEmail.setErrorEnabled(false);
                    String pwFromDb = snapshot.child(inputEmail).child("password").getValue(String.class);

                    if(pwFromDb.equals(inputPassword)){

                        enteredPassword.setError(null);
                        enteredPassword.setErrorEnabled(false);

                        String DBuserID = snapshot.child(inputEmail).child("userID").getValue(String.class);
                        String DBuserName = snapshot.child(inputEmail).child("username").getValue(String.class);
                        String DBemail = snapshot.child(inputEmail).child("email").getValue(String.class);
                        String DBuserType = snapshot.child(inputEmail).child("userType").getValue(String.class);
                        String DBphone = snapshot.child(inputEmail).child("phone").getValue(String.class);
                        String DBaddress1 = snapshot.child(inputEmail).child("address1").getValue(String.class);
                        String DBaddress2 = snapshot.child(inputEmail).child("address2").getValue(String.class);
                        String DBaddress3 = snapshot.child(inputEmail).child("address3").getValue(String.class);

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("userID", DBuserID);
                        intent.putExtra("userName", DBuserName);
                        intent.putExtra("userType", DBuserType);
                        intent.putExtra("email", DBemail);
                        intent.putExtra("phone", DBphone);
                        intent.putExtra("address1", DBaddress1);
                        intent.putExtra("address2", DBaddress2);
                        intent.putExtra("address3", DBaddress3);
                        startActivity(intent);

                        Toast.makeText(LoginActivity.this,
                                "Login Successful", Toast.LENGTH_SHORT).show();

                    }else{
                        enteredPassword.setError("Invalid Password");
                        enteredPassword.requestFocus();
                    }
                }else {
                    enteredEmail.setError("Invalid User Email");
                    enteredEmail.requestFocus();
                    enteredPassword.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

    }
}