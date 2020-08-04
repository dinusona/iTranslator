package com.example.mobilecw_02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.language_translator.v3.LanguageTranslator;
import com.ibm.watson.language_translator.v3.model.IdentifiableLanguage;
import com.ibm.watson.language_translator.v3.model.IdentifiableLanguages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.xmlpull.v1.XmlPullParser.TYPES;

public class ActivityForLanguageSubscription extends AppCompatActivity {

    private LanguageTranslator translationService;
    TextView tv;

    ArrayList<String> selectedItems = new ArrayList<>();

    public List<IdentifiableLanguage> allLanguages;

    DatabaseHelperForSubs myDb;
    String[] languageName = new String[6];
    Button buttonForAddCheckList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_language_subscription);
        myDb = new DatabaseHelperForSubs(this);


//        translationService = initLanguageTranslatorService();
//        new TranslationTask().execute("Hello World and my friend");
        ////////
//
//        IamAuthenticator authenticator = new IamAuthenticator(getString(R.string.language_translator_apikey));
//            LanguageTranslator serv = new LanguageTranslator("2018-05-01", (com.ibm.cloud.sdk.core.security.Authenticator) authenticator);
//            serv.setServiceUrl(getString(R.string.language_translator_url));
//
//            IdentifiableLanguages languages= serv.listIdentifiableLanguages().execute().getResult();
//            allLanguages=languages.getLanguages();
//
//
////        array for store language code
//            String[] languageCode=new String[allLanguages.size()];
//
//
//        String Arraystring = languageCode[0].toString();
//
//        for(int i = 1; i < languageCode.length; i++) {
//            Arraystring += "," + languageCode[i].toString();
//
//        }
//


        ListView ch1 = findViewById(R.id.checkable_list);
        ch1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        final String[] languageCode = {"ar", "bn", "gu", "cs", "da", "en", "es"};

//        String ArraystringLangCode = languageCode[0].toString();
//
//        for(int i = 1; i < languageCode.length; i++) {
//            ArraystringLangCode += "," + languageCode[i].toString();
//        }


        //language Name array setting
        languageName = new String[]{"Arabic", "Bengali", "Greek", "Czech", "Danish", "English", "Spain"};

//        String ArraystringLangName = languageName[0].toString();
//
//        for(int i = 1; i < languageName.length; i++) {
//            ArraystringLangName += "," + languageName[i].toString();
//        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.rowlayout, R.id.txt_lan, languageName);
        ch1.setAdapter(adapter);
        ch1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = ((TextView) view).getText().toString();
                if (selectedItems.contains(selectedItem)) {
                    selectedItems.remove(selectedItem);
//                    AddData(selectedItem);


                } else {
                    selectedItems.add(selectedItem);

                }
//
            }
        });
        buttonForAddCheckList = findViewById(R.id.bt);

        buttonForAddCheckList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < selectedItems.size(); i++) {
                    for (int j = 0; j < languageName.length; j++) {
                        if (languageName[j] == (selectedItems.get(i))) {
                            System.out.println("code " + languageCode[j]);//code
                            System.out.println(i);
                            System.out.println("name" + selectedItems.get(i));//name
                            System.out.println(">>>>>>>>>>>");
                            System.out.println(languageName[j]);
                            System.out.println(">>>>>>>>>>>");
                            System.out.println("Lang name " + i);
                            myDb.insertDataForSubs(selectedItems.get(i),languageCode[j]);


                        }
                    }
                }
            }
        });
    }

//    public void AddData(int i,int j) {
//        boolean insertData = myDb.insertDataForSubs(i,j);
//        if (insertData == true) {
//            Toast.makeText(ActivityForLanguageSubscription.this, "Data entered", Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(ActivityForLanguageSubscription.this, "Data NOT entered", Toast.LENGTH_LONG).show();
//        }
//    }


}

//    public void showSelectedItems(View view) {
//
//        for (int i = 0; i < selectedItems.size(); i++) {
//            for (int j = 0; i < languageName.length; j++) {
//                if (languageName[j].equals(selectedItems.get(j))) {
//                    System.out.println(i);
//                    System.out.println(">>>>>>>>>>>");
//                    System.out.println(languageName[i]);
//                    System.out.println(">>>>>>>>>>>");
//                    System.out.println("Lang name " + i);
//
//                }
//            }
////        String items="";
////        for (String item:selectedItems){
////            items+="-"+item+"\n";
////
////        }
////        Toast.makeText(this, "You have Seleced \n"+ items,Toast.LENGTH_SHORT).show();
//
//        }
//    }


//    private class TranslationTask extends AsyncTask<String, Void, List<IdentifiableLanguage>> {
//
//        public List<IdentifiableLanguage> allLanguages;
//
//
//        @Override
//        protected List<IdentifiableLanguage> doInBackground(String... params) {
//
//            IamAuthenticator authenticator = new IamAuthenticator(getString(R.string.language_translator_apikey));
//            LanguageTranslator serv = new LanguageTranslator("2018-05-01", (com.ibm.cloud.sdk.core.security.Authenticator) authenticator);
//            serv.setServiceUrl(getString(R.string.language_translator_url));
//
//            IdentifiableLanguages languages= serv.listIdentifiableLanguages().execute().getResult();
//            allLanguages=languages.getLanguages();
//
//
//
//            //array for store language code
//            String[] languageCode=new String[allLanguages.size()];
//
//            //array for store language names
//            String[] languageName=new String[allLanguages.size()];
//
//
//
//
//            /////////
//            for(int i=0;i<allLanguages.size();i++){
//                languageCode[i]=allLanguages.get(i).getLanguage();
//                System.out.print("^^^^^^^^^^^^");
//            }
//            System.out.println(languageCode);
//
//            for(int i=0;i<allLanguages.size();i++){
//                languageName[i]=allLanguages.get(i).getName();
//            }
//
//            System.out.println("***************************");
//
//            for (int i = 0; i < languageName.length; i++) {
//                System.out.print(languageName[i] + " , ");
//            }
//            for (int i = 0; i < languageCode.length; i++) {
//                System.out.print(languageCode[i] + " , ");
//
//            }
//
//            return allLanguages;
//        }
//
//
//        @Override
//        protected void onPostExecute(List<IdentifiableLanguage> list) {
//            super.onPostExecute(list);
//        }
//
//    }

//System.out.println(">>>>>>>>>>>");
//
//                for (int i = 0; i < selectedItems.size(); i++) {
//        System.out.println(selectedItems.get(i));
//    }
//                System.out.println(">>>>>>>>>>>");
