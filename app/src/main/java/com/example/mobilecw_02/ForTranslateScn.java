package com.example.mobilecw_02;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.language_translator.v3.LanguageTranslator;
import com.ibm.watson.language_translator.v3.model.TranslateOptions;
import com.ibm.watson.language_translator.v3.model.TranslationResult;
import com.ibm.watson.language_translator.v3.util.Language;

import java.util.ArrayList;
import java.util.List;

public class ForTranslateScn extends AppCompatActivity {
    private static final String TAG = "EditDataActivity";

    DatabaseHelper mDatabaseHelper;

    private Button btnForTrans;
    private EditText editable_showSelectedWord;


    private String selectedName;
    private String itemValue;
    private int selectedID;

    //
    private LanguageTranslator translationService;
    TextView tv;

    Spinner dropdownmenu;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_testing_trans_phrases);
        btnForTrans = (Button) findViewById(R.id.btnTranslate);
        tv = findViewById(R.id.tv);
        editable_showSelectedWord = (EditText) findViewById(R.id.editText_ForShowSelectedWord);
        mDatabaseHelper = new DatabaseHelper(this);

        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();
        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("id", -1); //NOTE: -1 is just the default value

        //now get the name we passed as an extra
        selectedName = receivedIntent.getStringExtra("name");
        //set the text to show the current selected name
        editable_showSelectedWord.setText(selectedName);

        //////


        btnForTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editable_showSelectedWord.getText().toString();
                if (!item.equals("")) {
                    translationService = initLanguageTranslatorService();
                    new TranslationTask().execute(selectedName);


//                    Intent j = new Intent(getApplicationContext(), TransShowTesting.class);
//                    startActivity(j);
//                    Run();

                } else {
                    toastMessage("You must enter a name");
                }
            }
        });

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

        //Spinner
        dropdownmenu = findViewById(R.id.spinner1);

        List<String> list = new ArrayList<>();
        list.add("ar");
        list.add("bn");
        list.add("gu");
        list.add("cs");
        list.add("da");
        //list.add("dn");
        //list.add("en");
        list.add("es");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownmenu.setAdapter(adapter);
        dropdownmenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemValue = parent.getItemAtPosition(position).toString();
//                Toast.makeText(ForTranslateScn.this, "Selected:"+itemValue, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }




    private LanguageTranslator initLanguageTranslatorService() {
        IamAuthenticator authenticator = new IamAuthenticator(getString(R.string.language_translator_apikey));
        LanguageTranslator service = new LanguageTranslator("2018-05-01", (com.ibm.cloud.sdk.core.security.Authenticator) authenticator);
        service.setServiceUrl(getString(R.string.language_translator_url));
        return service;
    }

    private class TranslationTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            TranslateOptions translateOptions = new TranslateOptions.Builder()
                    .addText(params[0]).source(Language.ENGLISH).target(itemValue)
                    .build();
            TranslationResult result = translationService.translate(translateOptions).execute().getResult();
            String firstTranslation = result.getTranslations().get(0).getTranslation();
            return firstTranslation;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tv.setText(s);
        }
    }

    /**
     * customizable toast
     *
     * @param message
     */
    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}


