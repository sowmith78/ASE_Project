package com.example.application.ase4;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class Home extends AppCompatActivity {

    private TextView resultText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        resultText = (TextView) findViewById(R.id.tv);
    }

    public void logout(View v)
    {
        Intent i = new Intent(Home.this,MainActivity.class);

        startActivity(i);
    }

    public void onMic(View v)
    {

        promptSpeech();
    }


    public void promptSpeech()
    {
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL ,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM );
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE , Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say Something..!!");

        try {
            startActivityForResult(i, 100);
        }
        catch (ActivityNotFoundException a)
        {
            Toast.makeText(Home.this,"Sorry your device does not support speech language!", Toast.LENGTH_LONG).show();
        }
    }

    public  void onActivityResult( int request_code , int result_code , Intent i)
    {
        super.onActivityResult(request_code,result_code,i);

        switch (request_code)
        {
            case 100 : if(result_code == RESULT_OK && i!= null)
            {

                ArrayList<String> result = i.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                resultText.setText(result.get(0));


            }

                break;
        }


    }



}
