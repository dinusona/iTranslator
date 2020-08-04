package com.example.mobilecw_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = findViewById(R.id.btnAddPhrases);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ActivityForAddPhrases.class);
                startActivity(i);
            }
        });


        //intent set eka
        button = findViewById(R.id.btnDisplayPhrase);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityForDisplayPhrases.class);
                startActivity(intent);
            }
        });

        button = findViewById(R.id.btnEditPhrases);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i=new Intent(getApplicationContext(),ActivityForEditPhrases.class);
//                startActivity(i);
                Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
                startActivity(intent);
            }
        });

        button = findViewById(R.id.btnLanguageSubscription);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ActivityForLanguageSubscription.class);
                startActivity(i);
            }
        });

        button = findViewById(R.id.btnTranslate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ActivityForTranslate.class);
                startActivity(i);
            }
        });

        button = findViewById(R.id.btnSpeech);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ListDataViewForSPEECH.class);
                startActivity(i);
            }
        });

    }


}
