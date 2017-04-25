package com.example.application.locus_proj;

import android.*;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserTracker extends AppCompatActivity {
    private Button start_btn;
    private Button stop_btn;
    private TextView tv;
    private BroadcastReceiver broadcastReceiver;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private String id;
    private String uid;
    private double latitude;
    private double longitude;
    private Button button_map;
    protected void onResume(){
        super.onResume();
        Toast.makeText(UserTracker.this,"in onReceive()",Toast.LENGTH_SHORT).show();
        if(broadcastReceiver== null){
            broadcastReceiver=new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    Toast.makeText(UserTracker.this,"in on Rec",Toast.LENGTH_SHORT).show();
                    tv=(TextView) findViewById(R.id.coordinates_lbl);
                    database = FirebaseDatabase.getInstance();
                    myRef = database.getReference();
                    String muid=uid.replace(".","@");
                    String s=(String) intent.getExtras().get("coordinates");
                    String[] cdn=s.split(" ");
                    id=myRef.push().getKey();
                    latitude=Double.parseDouble(cdn[0]);
                    longitude=Double.parseDouble(cdn[1]);
                    CoordinatesTracker ct=new CoordinatesTracker(id,latitude,longitude );
                    myRef.child("users").child(muid).child("coordinates").setValue(ct);
                    tv.append("\n"+"Latitude:"+latitude+"Longitude:"+longitude);
                }
            };
        }
        registerReceiver(broadcastReceiver,new IntentFilter("location_update"));
    }
    protected void onDestroy(){
        super.onDestroy();
        Toast.makeText(UserTracker.this,"in ondestroy()",Toast.LENGTH_SHORT).show();
        if(broadcastReceiver!= null) {
            unregisterReceiver(broadcastReceiver);
        }

    }
    public void displaymap(View v){
        Intent i=new Intent(UserTracker.this,MapsActivity.class);
        i.putExtra("latitude",latitude);
        i.putExtra("longitude", longitude);
        i.putExtra("class","usertracker");
        startActivity(i);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(UserTracker.this,"in oncreate()",Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_user_tracker);
        start_btn=(Button) findViewById(R.id.start_btn);
        stop_btn=(Button) findViewById(R.id.stop_btn);
        button_map=(Button) findViewById(R.id.button_map);
        Intent intent = getIntent();
        uid=intent.getStringExtra("user");
        if(!runtime_permissions())
            enable_buttons();
    }

    private void enable_buttons(){
        start_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Toast.makeText(UserTracker.this," start button clicked",Toast.LENGTH_SHORT).show();
                Intent i= new Intent(UserTracker.this,LocationHelper.class);
                startService(i);
            }
        });
        stop_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Toast.makeText(UserTracker.this," stop button clicked",Toast.LENGTH_SHORT).show();
                Intent i= new Intent(UserTracker.this,LocationHelper.class);
                stopService(i);
            }
        });

    }
    private boolean runtime_permissions(){

        if(Build.VERSION.SDK_INT>=23 && ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION},100);
            return true;
        }
        return  false;
    }
    public void onRequestPermissionsResult(int requestcode, @NonNull String[] permissions, @NonNull int[] grantresults){
        super.onRequestPermissionsResult(requestcode, permissions, grantresults);
        if(requestcode==100){
            if(grantresults[0]==PackageManager.PERMISSION_GRANTED && grantresults[1]==PackageManager.PERMISSION_GRANTED){
                enable_buttons();
            }
            else
                runtime_permissions();
        }
    }
}


