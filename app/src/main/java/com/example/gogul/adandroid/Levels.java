package com.example.gogul.adandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.List;

public class Levels extends AppCompatActivity {

    StoredObject so;
    SharedPreferences pref;
    ListView listapprove;
    TextView showCoin;
    StoredObjectFunction sof;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        getSupportActionBar().hide();
        pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        listapprove= (ListView) findViewById(R.id.ListLevel);
        showCoin= (TextView) findViewById(R.id.coinIdinLevel);
        Intent i = getIntent();
        sof= new StoredObjectFunction();
        so = (StoredObject) i.getSerializableExtra("MyObject");
        showCoin.setText(String.valueOf(so.getCoins()));
        showlist(so.getListLevelDetails());

    }
    public void showlist(List<LevelDetails> result)
    {
        final ListView lv = (ListView) findViewById(R.id.ListLevel);
        lv.setAdapter(new SimpleAdapter
                (this, result, R.layout.rowapprove, new String[]{"LevelNumber","LevelStage"},
                        new int[]{ R.id.text1,R.id.text2}));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LevelDetails listItem = (LevelDetails) lv.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), PlayBoxLevelEasy.class);
                String levelNumber=listItem.get("LevelNumber");
                intent.putExtra("levelNumber", levelNumber);
                intent.putExtra("MyObject",so);
                startActivity(intent);
            }
        });
    }

    public void coinClick(View v) {
        SharedPreferences.Editor editor = pref.edit();
        Intent intent = new Intent(this, CoinDetails.class);
        intent.putExtra("MyObject",so);
        intent.putExtra("from",Levels.class);
        startActivity(intent);
        // finishAffinity();

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,GameMode.class);
        intent.putExtra("MyObject",so);
        intent.putExtra("passing",true);
        startActivity(intent);
        finishAffinity();
    }

}
