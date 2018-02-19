package com.example.gogul.adandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Set;

public class Settings extends AppCompatActivity {
    TextView showCoin;
    SharedPreferences pref;
    StoredObject so = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().hide();
        pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Intent i = getIntent();
        showCoin = (TextView) findViewById(R.id.coinIdsetting);

        so = (StoredObject) i.getSerializableExtra("MyObject");
        showCoin.setText(String.valueOf(so.getCoins()));

    }
    public void coinClicksetting(View v) {
        SharedPreferences.Editor editor = pref.edit();
        Intent intent = new Intent(this, CoinDetails.class);
        intent.putExtra("MyObject",so);
        intent.putExtra("from",Settings.class);
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
