package com.example.gogul.adandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PlayBoxLevelEasy extends AppCompatActivity {
    SharedPreferences pref;
    StoredObject so;
    TextView showCoin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_play_box_level_easy);
        pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Intent i = getIntent();
        so= (StoredObject) i.getSerializableExtra("MyObject");
        String levelNumber=i.getStringExtra("levelNumber");
        showCoin = (TextView) findViewById(R.id.coinIdplay);
        showCoin.setText(String.valueOf(so.getCoins()));

    }
    public void coinClick(View v) {
        SharedPreferences.Editor editor = pref.edit();
        Intent intent = new Intent(this, CoinDetails.class);
        intent.putExtra("MyObject",so);
        intent.putExtra("from",PlayBoxLevelEasy.class);
        startActivity(intent);
        // finishAffinity();

    }
}
