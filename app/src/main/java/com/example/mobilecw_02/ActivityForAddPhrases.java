package com.example.mobilecw_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityForAddPhrases extends AppCompatActivity {

    DatabaseHelper myDb;
    Button btnForAdd;
    EditText editTextForAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_add_phrases);
        myDb = new DatabaseHelper(this);


        btnForAdd = findViewById(R.id.btnSave);
        editTextForAddData = findViewById(R.id.editText_ForInputs);


        btnForAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editTextForAddData.getText().toString();
                if (editTextForAddData.length() != 0) {
                    AddData(newEntry);
                    editTextForAddData.setText("");
                } else {
                    Toast.makeText(ActivityForAddPhrases.this, "You put so thing in the text field", Toast.LENGTH_LONG).show();

                }
            }
        });

    }
    public void AddData(String newEntry){
        boolean insertData=myDb.insertData(newEntry);

        if(insertData==true){
            Toast.makeText(ActivityForAddPhrases.this, "Data entered", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(ActivityForAddPhrases.this, "Data NOT entered", Toast.LENGTH_LONG).show();
        }
    }
}