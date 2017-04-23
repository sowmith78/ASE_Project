package com.example.application.locus_proj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class Home extends AppCompatActivity {
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        user=getIntent().getStringExtra("id");
    }

    public void logout(View view)
    {
        Intent i = new Intent(Home.this,MainActivity.class);
        startActivity(i);


    }
    public void displayFavoriteLocationManager(View v){
        Intent i= new Intent(Home.this,FavoriteLocationManager.class);
        Toast.makeText(Home.this,"In Home"+user,Toast.LENGTH_SHORT).show();
        i.putExtra("user",user);
        startActivity(i);
    }
    public void displayUserTracker(View v){
        Intent i= new Intent(Home.this,UserTracker.class);
        i.putExtra("user", user);
        startActivity(i);
    }
}
