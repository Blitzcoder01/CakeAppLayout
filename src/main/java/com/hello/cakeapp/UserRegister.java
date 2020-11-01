package com.hello.cakeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class UserRegister extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    EditText editTextTextPersonName,  editTextTextEmailAddress2,  editTextPhone,  editTextTextMultiLine,  editTextTextPassword2;
    private DatabaseReference RootReference;
    private Button register;
    private  DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);



        try{
            this.getSupportActionBar().hide();
        }catch(NullPointerException e){

        }

        editTextTextPersonName=findViewById(R.id.editTextTextPersonName);
        editTextTextEmailAddress2=findViewById(R.id.editTextTextEmailAddress2);
        editTextPhone =findViewById(R.id.editTextPhone);
        editTextTextMultiLine=findViewById(R.id.editTextTextMultiLine);
        editTextTextPassword2=findViewById(R.id.editTextTextPassword2);
        register=findViewById(R.id.register);
        mAuth = FirebaseAuth.getInstance();
        RootReference= FirebaseDatabase.getInstance().getReference();
        findViewById(R.id.register).setOnClickListener(this);

        ref= FirebaseDatabase.getInstance().getReference();
    }
    private void registerUser(){
        String u_name= editTextTextPersonName.getText().toString().trim();

        String email= editTextTextEmailAddress2.getText().toString().trim();

        String phone_number= editTextPhone.getText().toString().trim();

        String address= editTextTextMultiLine.getText().toString().trim();

        String password= editTextTextPassword2.getText().toString().trim();

        if(email.isEmpty()){
            editTextTextEmailAddress2.setError("Email_id is required");
            editTextTextEmailAddress2.requestFocus();
            return;

        }
        else if(u_name.isEmpty()){
            editTextTextPersonName.setError("user name is required");
            editTextTextPersonName.requestFocus();
            return;
        }
        else if(phone_number.isEmpty())
        {
            editTextPhone.setError("phone number required");
            editTextPhone.requestFocus();
        }
        else if(address.isEmpty())
        {
            editTextTextMultiLine.setError("Address required");
            editTextTextMultiLine.requestFocus();
        }
        else if(password.isEmpty()){
            editTextTextPassword2.setError("Password is required");
            editTextTextPassword2.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextTextEmailAddress2.setError("enter valid email id");
            editTextTextEmailAddress2.requestFocus();
            return;
        }
        if(password.length()<8){
            editTextTextPassword2.setError("minimum length of password must be eight");
            editTextTextPassword2.requestFocus();
            return;

        }
        if(phone_number.length()<10){
            editTextPhone.setError("count the digits of typed number");
            editTextPhone.requestFocus();
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
                            Intent intent =new Intent(UserRegister.this,UserMainActivity.class);
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
            case R.id.register:
                registerUser();

        }

    }
}