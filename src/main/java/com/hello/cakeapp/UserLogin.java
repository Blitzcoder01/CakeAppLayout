package com.hello.cakeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserLogin extends AppCompatActivity implements View.OnClickListener{
    private Button login;
    private FirebaseAuth mAuth;
    private EditText editTextTextPassword ,editTextTextEmailAddress ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        try{
            this.getSupportActionBar().hide();
        }catch(NullPointerException e){

        }
        //login=findViewById(R.id.login);
        mAuth = FirebaseAuth.getInstance();
        editTextTextEmailAddress=findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword=findViewById(R.id.editTextTextPassword);
        findViewById(R.id.baker_view).setOnClickListener(this);

        findViewById(R.id.login).setOnClickListener(this);
    }

    private void UserLoginFunction() {
        String email=editTextTextEmailAddress.getText().toString().trim();
        String password= editTextTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextTextEmailAddress.setError("Email_id is required");
            editTextTextEmailAddress.requestFocus();
            return;

        }


        else if(password.isEmpty()){
            editTextTextPassword.setError("Password is required");
            editTextTextPassword.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextTextEmailAddress.setError("enter valid email id");
            editTextTextEmailAddress.requestFocus();
            return;
        }
        if(password.length()<8){
            editTextTextPassword.setError("minimum length of password must be eight");
            editTextTextPassword.requestFocus();
            return;

        }
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Intent intent =new Intent(UserLogin.this,UserMainActivity.class);
                    intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"LoggedIn Successfully",Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(getApplicationContext(),"Wrong email or password",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.login:
                UserLoginFunction();
                break;

            case R.id.baker_view:
                startActivity(new Intent(this,BakerLogIn.class));
                break;
        }

    }


}
