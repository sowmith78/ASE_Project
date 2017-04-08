package com.example.satheeshchandra.storageservice;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private Button submitbutton;
    private EditText editText_value;
   // private ProgressDialog progressDialog;
    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submitbutton=(Button) findViewById(R.id.button_submit);
        //progressDialog =new ProgressDialog(this);
        editText_value=(EditText)findViewById(R.id.editText_data);
        submitbutton.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view)
                {
                  String value=editText_value.getText().toString().trim();
                    //progressDialog.setMessage("Storing Values");
                    //progressDialog.show();
                    // Write a message to the database
                    database = FirebaseDatabase.getInstance();
                    myRef = database.getReference("");
                    myRef.child("coordinates").setValue(value.trim());
                    Toast.makeText(MainActivity.this, "Values stored in firebase successfully", Toast.LENGTH_SHORT);
                    //progressDialog.dismiss();
                }
        });
    }
}