package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class CustomListActivity extends AppCompatActivity {
    ListView simpleList;
    String factionList[] = {"Rhodes Island", "Team Rainbow", "Penguin Logistic", "Blacksteel", "Rhine Lab", "Lungmen"};

    int[] logos = {R.drawable.rhodes_island, R.drawable.team_rainbow, R.drawable.penguin_logistics, R.drawable.blacksteel, R.drawable.rhine_lab, R.drawable.lungmen};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);

        simpleList = (ListView) findViewById(R.id.simpleListView);
        CustomAdapter customAdapter = new CustomAdapter(this, factionList, logos);
        simpleList.setAdapter(customAdapter);

    }

    private class CustomAdapter extends BaseAdapter {

        Context context;
        String factionList[];
        int logos[];
        LayoutInflater inflater;

        public CustomAdapter(Context context, String factionList[], int logos[]) {

            this.context = context;
            this.factionList = factionList;
            this.logos = logos;
            inflater = (LayoutInflater.from(context));
        }

        @Override
        public int getCount() {
          return  factionList.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup parent) {
            view = inflater.inflate(R.layout.activity_custom_listview, null);
            TextView Factions = (TextView) view.findViewById(R.id.textView);
            ImageView icons = (ImageView) view.findViewById(R.id.icon);
            Factions.setText(factionList[i]);
            icons.setImageResource(logos[i]);
            return view;
        }
    }
}