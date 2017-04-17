package com.example.application.locus_proj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ProjectUser extends AppCompatActivity {
    String usermail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_user);
    }
    public void trackUser(View view){
        Intent i=new Intent(ProjectUser.this, MapsActivity.class);
        i.putExtra("usermail",usermail);
        startActivity(i);
    }
}
