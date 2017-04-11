package com.example.application.locus_proj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;



public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void logout(View view)
    {
        Intent i = new Intent(Home.this,MainActivity.class);
        startActivity(i);


    }
    public void displayFavoriteLocationManager(View v){
        Intent i= new Intent(Home.this,FavoriteLocationManager.class);
        startActivity(i);
    }
    public void displayUserTracker(View v){
        Intent i= new Intent(Home.this,UserTracker.class);
        startActivity(i);
    }

}
