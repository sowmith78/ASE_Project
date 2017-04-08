package com.example.satheeshchandra.tracker;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private   Button start_btn;
    private Button stop_btn;
    private TextView tv;
    private BroadcastReceiver broadcastReceiver;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    protected void onResume(){
        super.onResume();
        Toast.makeText(MainActivity.this,"in onReceive()",Toast.LENGTH_SHORT).show();
        if(broadcastReceiver== null){
            broadcastReceiver=new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    //Toast.makeText(MainActivity.this,"in onRec",Toast.LENGTH_SHORT).show();
                    tv=(TextView) findViewById(R.id.coordinates_lbl);
                   //database = FirebaseDatabase.getInstance();
                  // myRef = database.getReference();
                   //myRef.child("coordinates").setValue(tv.getText().toString().trim());
                    tv.append("\n"+intent.getExtras().get("coordinates"));
                   // myRef.child("coordinates").setValue(tv.getText().toString().trim());

                }
            };
        }
        registerReceiver(broadcastReceiver,new IntentFilter("location_update"));
    }
    protected void onDestroy(){
        super.onDestroy();
        Toast.makeText(MainActivity.this,"in ondestroy()",Toast.LENGTH_SHORT).show();
        if(broadcastReceiver!= null) {
            unregisterReceiver(broadcastReceiver);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(MainActivity.this,"in oncreate()",Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_main);
        start_btn=(Button) findViewById(R.id.start_btn);
        stop_btn=(Button) findViewById(R.id.stop_btn);
        if(!runtime_permissions())
            enable_buttons();
    }

    private void enable_buttons(){
        start_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Toast.makeText(MainActivity.this,"button clicked",Toast.LENGTH_SHORT).show();
                Intent i= new Intent(getApplicationContext(),LocationHelper.class);
                startService(i);
            }
        });
        stop_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i= new Intent(getApplicationContext(),LocationHelper.class);
                stopService(i);
            }
        });
    }
    private boolean runtime_permissions(){

        if(Build.VERSION.SDK_INT>=23 && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},100);
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
