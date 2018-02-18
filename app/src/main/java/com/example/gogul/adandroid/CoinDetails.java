package com.example.gogul.adandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class CoinDetails extends AppCompatActivity {

    static  Long timerTime= Long.valueOf(30000);
    SharedPreferences pref;
    TextView showCoin;
    StoredObject so;
    TextView countDown;
    CountDownTimer cTimer = null;
    Serializable comingFrom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_details);
        pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Intent i = getIntent();
        getSupportActionBar().hide();
        so = (StoredObject) i.getSerializableExtra("MyObject");
        showCoin = (TextView) findViewById(R.id.coinDetailsid);
        countDown = (TextView) findViewById(R.id.countDownid);
        showCoin.setText(String.valueOf(so.getCoins()));
        Long timerNow = shouldTimerRun();
        Log.e("time0", String.valueOf(timerNow));
        if (timerNow == 0) {
            countDown.setTextSize(30);
            countDown.setText("COLLECT");
        } else {
            startTimer(timerNow);
        }
        comingFrom =getIntent().getSerializableExtra("from");
    }


    //start timer function
    void startTimer(Long timeLeft) {
        cTimer = new CountDownTimer(timeLeft, 1000) {
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000;
                String show = String.format("%02d:%02d", seconds / 60, seconds % 60);
                countDown.setTextSize(20);
                countDown.setText("Collect in: " + show);
            }
            public void onFinish() {
                countDown.setTextSize(30);
                countDown.setText("COLLECT");
                cancelTimer();
            }
        };
        cTimer.start();
    }
    //cancel timer
    void cancelTimer() {
        if(cTimer!=null)
            cTimer.cancel();
    }

     public void collectClickHour(View v )
     {
         if(countDown.getText().equals("COLLECT")) {
             int now = so.getCoins();
             int newNow = now+10;
             so.setCoins(newNow);
             so.setTimeLeftHour(System.currentTimeMillis());
             Log.e("seting", String.valueOf(System.currentTimeMillis()));
             showCoin.setText(String.valueOf(newNow));
             startTimer(timerTime);
         }
     }
    public Long shouldTimerRun()
    {   Long present = System.currentTimeMillis();
        Long old = so.getTimeLeftHour()!=null?so.getTimeLeftHour():0;
        Long diff = (present>old)?(present-old):0;
        Long send = diff>timerTime?0:(timerTime-diff);

        Log.e("old", String.valueOf(old));
        Log.e("timerTime", String.valueOf(timerTime));
        Log.e("send", String.valueOf(send));
        Log.e("diff", String.valueOf(diff));
        Log.e("present", String.valueOf(present));

        return send;
    }





    @Override
    public void onBackPressed() {
        cancelTimer();
        Log.d("CDA", "onBackPressed Called");
        Intent intent = new Intent(this, (Class<?>)comingFrom);
        intent.putExtra("MyObject",so);
        intent.putExtra("passing",true);
        startActivity(intent);
        finishAffinity();
    }

}
