package com.example.application.ase4;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);


    }

    public void onRegister(View v)

    {
        EditText name = (EditText)findViewById(R.id.nam);
        EditText mail = (EditText)findViewById(R.id.mail);
        EditText username = (EditText)findViewById(R.id.uname);
        EditText password1 = (EditText)findViewById(R.id.pword);
        EditText password2 = (EditText)findViewById(R.id.pword2);


        String namestr = name.getText().toString();
        String mailstr = mail.getText().toString();
        String usernamestr = username.getText().toString();
        String password1str = password1.getText().toString();
        String password2str = password2.getText().toString();

        if (!password1str.equals(password2str))
        {
            Toast pass = Toast.makeText(Signup.this,"Password does'nt match ", Toast.LENGTH_SHORT);
            pass.show();
        }

        else if (mailstr.matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]"))
        {
            Toast pass = Toast.makeText(Signup.this,"Invalid mail ", Toast.LENGTH_SHORT);
            pass.show();
        }

        else if (namestr.matches("") || mailstr.matches("") || usernamestr.matches("") || password1str.matches("") || password2str.matches(""))
        {
            Toast pass = Toast.makeText(Signup.this,"field should not be empty", Toast.LENGTH_SHORT);
            pass.show();
        }

        else
        {
            Contact c = new Contact();
            c.setName(namestr);
            c.setEmail(mailstr);
            c.setUname(usernamestr);
            c.setPass(password1str);

            helper.insertContact(c);

            Intent i = new Intent(Signup.this,MainActivity.class);

            startActivity(i);


        }



    }

}
