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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    DatabaseHelper helper = new DatabaseHelper(this);
    Button google;
    GoogleApiClient googleApiClient;
    LinearLayout load;
    RelativeLayout main;
    Button signout;
    Button map;
    private static final int REQ_CODE = 9001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        google = (Button)findViewById(R.id.google);
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions).build();
        load = (LinearLayout)findViewById(R.id.Load);
        signout =(Button)findViewById(R.id.logout);
        main=(RelativeLayout)findViewById(R.id.activity_main);
        map =(Button)findViewById(R.id.map);
       // load.setVisibility(View.GONE);
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

    public void logoutclick(View v)
    {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                updateUI(false);
            }
        });
    }

    public void mapclick(View v)
    {
        Intent i = new Intent(MainActivity.this,Signup.class);
        startActivity(i);


    }

    private void handleResult(GoogleSignInResult result)
    {


    }

    private void updateUI(boolean isLogin)
    {
        if (isLogin)
        {

            Intent i = new Intent(String.valueOf(MapsActivity.class));

            startActivity(i);
        }
        else
        {
            Intent i = new Intent(MainActivity.this,MainActivity.class);

            startActivity(i);
        }
    }
    public void google(View v)
    {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent,REQ_CODE);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQ_CODE)
        {
            updateUI(true);
            Bundle bundle = data.getExtras();
        }
    }
}
