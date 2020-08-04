package com.example.mobilecw_02;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.language_translator.v3.LanguageTranslator;
import com.ibm.watson.language_translator.v3.model.TranslateOptions;
import com.ibm.watson.language_translator.v3.model.TranslationResult;
import com.ibm.watson.language_translator.v3.util.Language;

import java.util.ArrayList;

public class ActivityForTranslate extends AppCompatActivity {
    private static final String TAG = "ListDataActivity";

    DatabaseHelper mDatabaseHelper;

    private ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontents_layout);
        mListView = (ListView) findViewById(R.id.listView);
        mDatabaseHelper = new DatabaseHelper(this);

        populateListView();
    }

    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

        //get the data and append to a list
        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {
            //get the value from the database in column 1
            //then add it to the ArrayList
            listData.add(data.getString(1));
        }
        //create the list adapter and set the adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);

        //set an onItemClickListener to the ListView
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, "onItemClick: You Clicked on " + name);

                Cursor data = mDatabaseHelper.getItemID(name); //get the id associated with that name
                int itemID = -1;
                while (data.moveToNext()) {
                    itemID = data.getInt(0);
                }
                if (itemID > -1) {

                    Log.d(TAG, "onItemClick: The ID is: " + itemID);
                    Intent editScreenIntent1 = new Intent(ActivityForTranslate.this, ForTranslateScn.class);
                    editScreenIntent1.putExtra("id", itemID);
                    editScreenIntent1.putExtra("name", name);
                    startActivity(editScreenIntent1);
                } else {
                    toastMessage("No ID associated with that name");
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


//    private LanguageTranslator translationService;
//    TextView tv;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_for_translate);
//
//        tv = findViewById(R.id.tv);
//        translationService = initLanguageTranslatorService();
//        new TranslationTask().execute("Hello World and my friend");
//
//    }
//
//    private LanguageTranslator initLanguageTranslatorService() {
//        IamAuthenticator authenticator = new IamAuthenticator(getString(R.string.language_translator_apikey));
//        LanguageTranslator service = new LanguageTranslator("2018-05-01", (com.ibm.cloud.sdk.core.security.Authenticator) authenticator);
//        service.setServiceUrl(getString(R.string.language_translator_url));
//        return service;
//    }
//    private class TranslationTask extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            TranslateOptions translateOptions = new TranslateOptions.Builder()
//                    .addText(params[0]).source(Language.ENGLISH).target("es")
//                    .build();
//            TranslationResult result =
//                    translationService.translate(translateOptions).execute().getResult();
//            String firstTranslation =
//                    result.getTranslations().get(0).getTranslation();
//            return firstTranslation;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            tv.setText(s);
//        }
//    }
}
