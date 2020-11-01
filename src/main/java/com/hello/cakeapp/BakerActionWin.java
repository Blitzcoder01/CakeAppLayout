package com.hello.cakeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class BakerActionWin extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle t;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baker_action_win);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerbaker);
        t = new ActionBarDrawerToggle(this, drawerLayout,R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(t);
        t.syncState();
        navigationView = (NavigationView)findViewById(R.id.nav_view2);
        mAuth = FirebaseAuth.getInstance();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.notification:
                        //Toast.makeText(MainActivity.this, "My Account",Toast.LENGTH_SHORT).show();break;
                        //  Intent notification =new Intent(UserActionWin.this,UserNotificationsWin.class);
                        //   startActivity(notification);
                        break;
                    case R.id.gallery:
                        Intent order =new Intent(BakerActionWin.this,GalleryPickUp.class);
                        startActivity(order);
                        break;
                    case R.id.logout:

                        mAuth.signOut();
                        Intent login_intent = new Intent(BakerActionWin.this, BakerLogIn.class);
                        startActivity(login_intent);
                        Toast.makeText(getApplicationContext(), "Logged out Successfully!!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.help:
                        break;
                    case R.id.tracking:
                      //  Intent gallery = new Intent(BakerActionWin.this, CustomisationActivity.class);
                     //   startActivity(gallery);
                      //  break;

                    default:
                        return true;
                }
                return true;
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

}
