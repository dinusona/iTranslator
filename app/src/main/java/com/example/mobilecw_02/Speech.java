package com.example.mobilecw_02;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class Speech extends AppCompatActivity {

    TextToSpeech t1;
    private Button btnForRead, btnForDelete;
    private EditText editable_item;

    DatabaseHelper mDatabaseHelper;

    private String selectedName;
    private int selectedID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speech);

        btnForRead = (Button) findViewById(R.id.btnRead);
        btnForDelete = (Button) findViewById(R.id.btnDelete);
        editable_item = (EditText) findViewById(R.id.editText_ForUpdateData);
        mDatabaseHelper = new DatabaseHelper(this);

        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("id", -1); //NOTE: -1 is just the default value

        //now get the name we passed as an extra
        selectedName = receivedIntent.getStringExtra("name");

        //set the text to show the current selected name
        editable_item.setText(selectedName);


        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });
        btnForRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = editable_item.getText().toString();
                if (!item.equals("")) {
                    mDatabaseHelper.updateData(item, selectedID, selectedName);
                    String toSpeak = editable_item.getText().toString();
                    Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                    t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                }else {
                    toastMessage("Nothing to Speech !");
                }
            }
        });
    }

    public void onPause() {
        if (t1 != null) {
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }

//        btnForUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String item = editable_item.getText().toString();
//                if (!item.equals("")) {
//                    mDatabaseHelper.updateData(item, selectedID, selectedName);
//                    editable_item.setText("");
//                    toastMessage("Data Updated");
//                    Intent j = new Intent(getApplicationContext(), MainActivity.class);
//                    startActivity(j);
//
//                } else {
//                    toastMessage("You must enter a name");
//                }
//            }
//        });

//        btnForDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mDatabaseHelper.deleteName(selectedID, selectedName);
//                editable_item.setText("");
//                toastMessage("removed from database");
//                Intent j = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(j);
//            }
//        });


    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
