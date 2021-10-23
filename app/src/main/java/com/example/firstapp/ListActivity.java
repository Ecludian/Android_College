package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity {


    //Array of strings
    ListView simpleList;
    String factionList[] = {"Rhodes Island", "Team Rainbow", "Penguin Logistic", "Blacksteel", "Rhine Lab", "Lungmen"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        simpleList = (ListView) findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.activity_listview, R.id.textView, factionList);
        simpleList.setAdapter(arrayAdapter);





    }
}