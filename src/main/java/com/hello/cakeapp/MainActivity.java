package com.hello.cakeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button sign_in_button ;
    private Button user_sign_up_button;
    private Button baker_sign_up_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            this.getSupportActionBar().hide();
        }catch(NullPointerException e){

        }

        sign_in_button= findViewById(R.id.sign_in_button);

        sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login=new Intent(MainActivity.this, UserLogin.class);
                startActivity(login);
            }
        });

        user_sign_up_button=findViewById(R.id.user_sign_up_button);

        user_sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login=new Intent(MainActivity.this, UserRegister.class);
                startActivity(login);
            }
        });

        baker_sign_up_button=findViewById(R.id.baker_sign_up_button);

        baker_sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login=new Intent(MainActivity.this, BakerRagister.class);
                startActivity(login);
            }
        });

    }
}
