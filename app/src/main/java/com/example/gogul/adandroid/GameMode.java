package com.example.gogul.adandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameMode extends AppCompatActivity {

    Button btneasy;
    Button btnmedium;
    Button btnhard;
    Button btnevil;
    TextView showCoin;
    TextView btnLearn;
    SharedPreferences pref;
    StoredObject so = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);
        getSupportActionBar().hide();
        pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Intent i = getIntent();


        btneasy = (Button)findViewById(R.id.easyid);
        btnmedium = (Button)findViewById(R.id.mediumid);
        btnhard = (Button)findViewById(R.id.hardid);
        btnevil = (Button)findViewById(R.id.evilid);
        showCoin =(TextView) findViewById(R.id.coinId);
        btnLearn =(TextView) findViewById(R.id.learnid);

        so = (StoredObject) i.getSerializableExtra("MyObject");
        showCoin.setText(String.valueOf(so.getCoins()));


    }

    public void easyclick(View v)
    {
        Intent intent = new Intent(this, Levels.class);
        intent.putExtra("MyObject", so);
        startActivity(intent);
       // finishAffinity();
    }
    public void coinClick(View v) {
        SharedPreferences.Editor editor = pref.edit();
        Intent intent = new Intent(this, CoinDetails.class);
        intent.putExtra("MyObject",so);
        intent.putExtra("from",GameMode.class);
        startActivity(intent);
        // finishAffinity();

    }
    public void learnClick(View v) {
        SharedPreferences.Editor editor = pref.edit();
        Intent intent = new Intent(this, PlayBoxLevelEasy.class);
        intent.putExtra("MyObject",so);
        startActivity(intent);
        // finishAffinity();

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("MyObject",so);
        intent.putExtra("passing",true);
        startActivity(intent);
        finishAffinity();
    }
}
