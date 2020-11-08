package com.example.markotesic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Date;
import java.util.List;

public class SymptomActivity extends AppCompatActivity {

    EditText comment;
    EditText symtoms;
    ListView listView;
    Button button;

    Button buttontomap;
    Button buttontolist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom);
        FeedReederDbHelper feedReederDbHelper=new FeedReederDbHelper(getApplicationContext());
        //dobijanje id-a koji je sacuvan kod logovanje
        SharedPreferences sharedPreferences=getSharedPreferences("marko",Context.MODE_PRIVATE);
        int value=sharedPreferences.getInt("korisnikid",-1);
        Cursor c=feedReederDbHelper.getDnevnikByKorisnik(value);
        String [] niz=new String[c.getCount()];
        int i=0;
        c.moveToFirst();
        do{
            String komentar=c.getString(2);
            String terapija=c.getString(3);
            niz[i]=" "+komentar+" "+terapija;
            i++;
        }
        while (c.moveToNext());

        listView=findViewById(R.id.symptomslist);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,niz);
        listView.setAdapter(adapter);

        buttontomap=findViewById(R.id.symtomsbuttontomap);
        buttontolist=findViewById(R.id.symtomsbuttontolist);

        buttontomap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SymptomActivity.this,MapActivity.class);
                startActivity(intent);
            }
        });
        buttontolist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SymptomActivity.this,ListActivity.class);
                startActivity(intent);
            }
        });
        comment=findViewById(R.id.symptomscomment);
        symtoms=findViewById(R.id.symptmsymstoms);

        button=findViewById(R.id.symptomsbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String commenttext=comment.getText().toString();
               String symptomtext=symtoms.getText().toString();
               feedReederDbHelper.insertDnevnik(value,commenttext,symptomtext);
               String[] niz2=new String[niz.length+1];
               for(int i=0;i<niz.length;i++){
                   niz2[i]=niz[i];
               }
               niz2[niz.length]=commenttext+ " "+ symptomtext;
               ArrayAdapter<String> adapter1=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,niz2);
               listView.setAdapter(adapter1);
            }
        });
    }
}