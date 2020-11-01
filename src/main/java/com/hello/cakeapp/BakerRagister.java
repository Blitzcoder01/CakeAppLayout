package com.hello.cakeapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class BakerRagister extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    private EditText baker_name,baker_id,phone_number,shop_address,baker_password,shop_name;
    private CheckedTextView occupation;
    private DatabaseReference RootReference;
    private Button baker_register;
    private  DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baker_ragister);

        try{
            this.getSupportActionBar().hide();
        }catch(NullPointerException e){

        }


        baker_name=findViewById(R.id.baker_name);
        baker_id=findViewById(R.id.baker_id);
        phone_number=findViewById(R.id.phone_number);
        shop_address=findViewById(R.id.shop_address);
        baker_password=findViewById(R.id.baker_password);
        shop_name=findViewById(R.id.shop_name);
        occupation=findViewById(R.id.occupation);
        mAuth = FirebaseAuth.getInstance();

        // baker_register=findViewById(R.id.baker_register);

        RootReference= FirebaseDatabase.getInstance().getReference();
        findViewById(R.id.baker_register).setOnClickListener(this);
        ref= FirebaseDatabase.getInstance().getReference();
    }
    private void registerUser(){
        String u_name= baker_name.getText().toString().trim();

        String email= baker_id.getText().toString().trim();

        String phone_no= phone_number.getText().toString().trim();

        String address= shop_address.getText().toString().trim();

        String password= baker_password.getText().toString().trim();

        String shop= shop_name.getText().toString().trim();



        if(email.isEmpty()){
            baker_id.setError("Email_id is required");
            baker_id.requestFocus();
            return;

        }
        else if(u_name.isEmpty()){
            baker_name.setError("user name is required");
            baker_name.requestFocus();
            return;
        }
        else if(phone_no.isEmpty())
        {
            phone_number.setError("phone number required");
            phone_number.requestFocus();
        }
        else if(address.isEmpty())
        {
            shop_address.setError("Address required");
            shop_address.requestFocus();
        }
        else if(password.isEmpty()){
            baker_password.setError("Password is required");
            baker_password.requestFocus();
            return;
        }
        else if(shop.isEmpty()){
            shop_name.setError("Name of shop is required");
            shop_name.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            baker_id.setError("enter valid email id");
            baker_id.requestFocus();
            return;
        }
        if(password.length()<8){
            baker_password.setError("minimum length of password must be eight");
            baker_password.requestFocus();
            return;

        }
        if(phone_no.length()<10){
            phone_number.setError("count the digits of typed number");
            phone_number.requestFocus();
            return;

        }
        if(occupation==null){
            phone_number.setError("Tick at occupation option");
            phone_number.requestFocus();
            return;

        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String currentUserId =mAuth.getCurrentUser().getUid();
                            ref.child("AppUsers").child(currentUserId).setValue(" ");

                            Toast.makeText(getApplicationContext(),"Register Successfully",Toast.LENGTH_SHORT).show();
                            Intent intent =new Intent(BakerRagister.this,BakerActionWin.class);
                            intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);

                        }
                        else{
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(getApplicationContext(),"You are already Register",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Error in Registration",Toast.LENGTH_SHORT).show();
                            }
                        }
                        // ...
                    }
                });

    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.baker_register:
                registerUser();

        }

    }
}