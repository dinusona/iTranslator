package com.example.mobilecw_02;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ActivityForEditPhrases extends AppCompatActivity {

    private static final String TAG = "EditDataActivity";

    private Button btnForUpdate, btnForDelete;
    private EditText editable_item;

    DatabaseHelper mDatabaseHelper;

    private String selectedName;
    private int selectedID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_edit_phrases);
        btnForUpdate = (Button) findViewById(R.id.btnRead);
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

        btnForUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editable_item.getText().toString();
                if (!item.equals("")) {
                    mDatabaseHelper.updateData(item, selectedID, selectedName);
                    editable_item.setText("");
                    toastMessage("Data Updated");
                    Intent j = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(j);

                } else {
                    toastMessage("You must enter a name");
                }
            }
        });

        btnForDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editable_item.getText().toString();
                if (!item.equals("")) {
                    mDatabaseHelper.deleteName(selectedID, selectedName);
                    editable_item.setText("");
                    toastMessage("removed from database");
                    Intent j = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(j);
                }else {
                    toastMessage("You must enter a name to delete");
                }
            }
        });

    }

    /**
     * customizable toast
     *
     * @param message
     */
    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
