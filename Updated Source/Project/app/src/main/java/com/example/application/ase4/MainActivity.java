package com.example.application.ase4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
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
            Intent i = new Intent(MainActivity.this,FavoriteSpots.class);

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
