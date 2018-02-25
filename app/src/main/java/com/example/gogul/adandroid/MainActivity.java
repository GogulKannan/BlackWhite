package com.example.gogul.adandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    SharedPreferences pref;
    Button Loginbtn;
    TextView showCoin;
    StoredObject so = null;
    StoredObjectFunction sof= null;

    MediaPlayer myMediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);


        pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sof = new StoredObjectFunction();
        Intent i = getIntent();
        if (i.getBooleanExtra("passing", false)) {
            so = (StoredObject) i.getSerializableExtra("MyObject");
        } else {

            File file = new File(getDir("data", MODE_PRIVATE), "myobject.lock");
            StoredObjectFunction sof = new StoredObjectFunction();
            so = sof.loadAllObject(file);
            SharedPreferences.Editor editor = pref.edit();
            Log.e("token", String.valueOf(sof.isFirstTimeLoad()));

            if (sof.isFirstTimeLoad()) {
                so = sof.defaultValue();
                sof.saveObject(file, so);
            }

            if(so.isSoundOn())
                sof.SoundPlayer(this);
            else
                sof.stopSound();
        }



        Loginbtn = (Button) findViewById(R.id.button);
        showCoin = (TextView) findViewById(R.id.coinId);
        showCoin.setText(String.valueOf(so.getCoins()));
    }

    public void login(View v) {
        SharedPreferences.Editor editor = pref.edit();
        Intent intent = new Intent(this, GameMode.class);
        intent.putExtra("MyObject", so);
        startActivity(intent);
        // finishAffinity();
    }

    public void menuClick(View v) {
        SharedPreferences.Editor editor = pref.edit();
        Intent intent = new Intent(this, Settings.class);
        intent.putExtra("MyObject", so);
        startActivity(intent);
        // finishAffinity();

    }

    public void coinClick(View v) {
        SharedPreferences.Editor editor = pref.edit();
        Intent intent = new Intent(this, CoinDetails.class);
        intent.putExtra("MyObject", so);
        intent.putExtra("from",MainActivity.class);
        startActivity(intent);
        // finishAffinity();

    }
    @Override
    protected void onStop() {
        super.onPause();
        sof.myMediaPlayer.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sof.myMediaPlayer.pause();
    }

}
