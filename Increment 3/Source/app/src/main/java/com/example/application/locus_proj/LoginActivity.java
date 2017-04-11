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

public class LoginActivity extends AppCompatActivity {

    private EditText email2;
    private EditText passwrd2;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email2 = (EditText)findViewById(R.id.edittext11);
        passwrd2 = (EditText)findViewById(R.id.pass2);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void onLogin(View v)
    {   final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this, "Please wait...","Processing...",true);

        (firebaseAuth.signInWithEmailAndPassword(email2.getText().toString(),passwrd2.getText().toString()))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Login Successful  ", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(LoginActivity.this, Home.class);
                    startActivity(i);
                }

                else {
                    Log.e("ERROR",task.getException().toString());
                    Toast.makeText(LoginActivity.this,task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });

    }

}
