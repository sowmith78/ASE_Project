package com.example.application.locus_proj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
    ArrayList<String> ssss;
    Double Latitude;
    Double Longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_user);
        database = FirebaseDatabase.getInstance();
        ssss = new ArrayList<>();
        myRef = database.getReference("users/rk@gmail@com/favoritelocation");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue().toString();
                Toast.makeText(getApplicationContext(),value,Toast.LENGTH_LONG).show();
                ssss.add(value);

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
       /* myRef.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String abc=snapshot.child("rohithkumar@n94@gmail@com").child("coordinates").child("latitude").getValue().toString();
                String xyz=snapshot.child("rohithkumar@n94@gmail@com").child("coordinates").child("longitude").getValue().toString();
                String stu=snapshot.child("rohithkumar@n94@gmail@com").child("favoritelocation").getValue().toString();
                Latitude=Double.parseDouble(abc);
                Longitude=Double.parseDouble(xyz);
                Toast.makeText(ProjectUser.this,stu,Toast.LENGTH_LONG).show();  //prints "Do you have data? You'll love Firebase."
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });*/
    }
    public void trackUser(View view){
        Intent i=new Intent(ProjectUser.this, MapsActivity.class);
        i.putExtra("usermail",usermail);
        startActivity(i);
    }
}
