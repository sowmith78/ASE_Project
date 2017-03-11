package com.example.application.ase4;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.audiofx.BassBoost;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }



    public void onLogin(View v)
    {
        EditText a = (EditText)findViewById(R.id.username);
        String str = a.getText().toString();

        EditText b = (EditText)findViewById(R.id.password);
        String pass = b.getText().toString();

        String password = helper.searchPass(str);

        if (pass.equals(password))
        {
            Intent i = new Intent(MainActivity.this,MapsActivity.class);

            startActivity(i);
        }

        else
        {
            Toast tem = Toast.makeText(MainActivity.this,"Username and password does'nt match ", Toast.LENGTH_SHORT);
            tem.show();
        }




    }

    public void onSignup(View v)
    {
        Intent i = new Intent(MainActivity.this,Signup.class);
        startActivity(i);


    }

}
