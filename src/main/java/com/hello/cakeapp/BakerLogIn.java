package com.hello.cakeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class BakerLogIn extends AppCompatActivity implements View.OnClickListener{
    private Button login2;
    private FirebaseAuth mAuth;
    private EditText editTextTextPassword ,editTextTextEmailAddress ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baker_log_in);
        try{
            this.getSupportActionBar().hide();
        }catch(NullPointerException e){

        }
        //login=findViewById(R.id.login);
        mAuth = FirebaseAuth.getInstance();
        editTextTextEmailAddress=findViewById(R.id.editTextTextEmailAddressBaker);
        editTextTextPassword=findViewById(R.id.editTextTextPasswordBaker);
        //findViewById(R.id.baker_view).setOnClickListener(this);

        findViewById(R.id.login2).setOnClickListener(this);
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

                    Intent intent =new Intent(BakerLogIn.this,BakerActionWin.class);
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
            case R.id.login2:
                UserLoginFunction();
                break;


        }

    }

}
