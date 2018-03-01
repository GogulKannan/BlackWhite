package com.example.gogul.adandroid;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.logging.Level;

import static com.example.gogul.adandroid.R.drawable.blackoutwhitein;

public class PlayBoxLevelLearn extends AppCompatActivity {
    SharedPreferences pref;
    StoredObject so;
    TextView showCoin;
    Button learnBoxA;
    Button learnBoxB;
    Button learnBoxC;
    Button learnBoxD;
    StoredObjectFunction sof;
    Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_play_box_level_learn);
        pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Intent i = getIntent();
        so= (StoredObject) i.getSerializableExtra("MyObject");
        sof = new StoredObjectFunction();
        String levelNumber=i.getStringExtra("levelNumber");
        showCoin = (TextView) findViewById(R.id.coinIdplay);
        showCoin.setText(String.valueOf(so.getCoins()));
        learnBoxA = (Button)findViewById(R.id.learnbutton1);
        learnBoxB = (Button)findViewById(R.id.learnbutton2);
        learnBoxC = (Button)findViewById(R.id.learnbutton3);
        learnBoxD = (Button)findViewById(R.id.learnbutton4);
        populateBox(so.getCurrentLearnBox());

    }
    public void coinClick(View v) {
        SharedPreferences.Editor editor = pref.edit();
        Intent intent = new Intent(this, CoinDetails.class);
        intent.putExtra("MyObject",so);
        intent.putExtra("from",PlayBoxLevelLearn.class);
        startActivity(intent);
        // finishAffinity();
    }

    public void LevelClick(View v) {
        SharedPreferences.Editor editor = pref.edit();
        Intent intent = new Intent(this, Levels.class);
        intent.putExtra("MyObject",so);
        intent.putExtra("from",PlayBoxLevelLearn.class);
        startActivity(intent);
        // finishAffinity();
    }

    public void populateBox(int [][] box)
    {
        sof.setColorForButton(this,box[0][0],learnBoxA);
        sof.setColorForButton(this,box[0][1],learnBoxB);
        sof.setColorForButton(this,box[1][0],learnBoxC);
        sof.setColorForButton(this,box[1][1],learnBoxD);
    }

    public void learnA(View v) {
        commonButtonTouch(0,0);
    }
    public void learnB(View v) {
        commonButtonTouch(0,1);
    }
    public void learnC(View v) {
        commonButtonTouch(1,0);
    }
    public void learnD(View v) {
        commonButtonTouch(1,1);
    }

    public void commonButtonTouch(int x,int y)
    {
        so.setCurrentLearnBox(sof.changeIt(so.getCurrentLearnBox(),x,y));
        populateBox(so.getCurrentLearnBox());
        if(sof.winYet(so.getCurrentLearnBox()))showWin();
    }

    public void showWin(){
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.outdialog);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void playnextclick(View v)
    {
        dialog.cancel();
    }


}
