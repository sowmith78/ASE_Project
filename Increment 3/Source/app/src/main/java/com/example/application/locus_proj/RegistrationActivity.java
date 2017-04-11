package com.example.application.locus_proj;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private EditText email;
    private EditText passwrd;
    private EditText confpswd;
    private EditText phonenum;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        email = (EditText)findViewById(R.id.editText1);
        passwrd = (EditText)findViewById(R.id.pass1);
        confpswd = (EditText)findViewById(R.id.confpsw);
        phonenum = (EditText)findViewById(R.id.phone);
        firebaseAuth = FirebaseAuth.getInstance();

    }


    public void onRegister(View v)
    {
        String mailstr = email.getText().toString();
        String passstr = passwrd.getText().toString();
        String confstr = confpswd.getText().toString();
        String phonestr = phonenum.getText().toString();

        if(!passstr.equals(confstr))
        {
            Toast.makeText(RegistrationActivity.this,"Password doesnot match...", Toast.LENGTH_SHORT).show();
        }

        else if (mailstr.matches("")||passstr.matches("")||confstr.matches("")||phonestr.matches(""))
        {
            Toast.makeText(RegistrationActivity.this,"Fields should not be empty...", Toast.LENGTH_SHORT).show();
        }



        else {

            final ProgressDialog progressDialog = ProgressDialog.show(RegistrationActivity.this, " Please wait...", "processing...", true);
            (firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), passwrd.getText().toString()))
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();


                            if (task.isSuccessful()) {
                                Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                                startActivity(i);
                            } else {
                                Log.e("ERROR", task.getException().toString());
                                Toast.makeText(RegistrationActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }

    }

}
