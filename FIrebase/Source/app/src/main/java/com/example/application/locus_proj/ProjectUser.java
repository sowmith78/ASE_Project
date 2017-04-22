package com.example.application.locus_proj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProjectUser extends AppCompatActivity {
    String usermail;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    Double Latitude;
    Double Longitude;
    String s;
    public ArrayList<String> ssss = new ArrayList<>();
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_user);
        database = FirebaseDatabase.getInstance();
        et= (EditText) findViewById(R.id.wanteduser);
    }
    public void trackUser(View view){
        s= et.getText().toString();
        s=s.replace(".","@");
        myRef = database.getReference("users/"+s);
       myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String key = dataSnapshot.getKey().toString();
                if (key.equals("coordinates"))
                {
                    String lat = dataSnapshot.child("latitude").getValue().toString();
                    ssss.add(lat);
                    String longt = dataSnapshot.child("longitude").getValue().toString();
                    ssss.add(longt);
                }
                else
                {
                    for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                        String name = (String) messageSnapshot.getValue().toString();
                        ssss.add(name);
                    }

                    callintent();

                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });



    }
    public void callintent(){
        Intent i=new Intent(ProjectUser.this, MapsActivity.class);
        i.putStringArrayListExtra("arraylist",ssss);
        i.putExtra("class","projectuser");
        startActivity(i);
    }
}
