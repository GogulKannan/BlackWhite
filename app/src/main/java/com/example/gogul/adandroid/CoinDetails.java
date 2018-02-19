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
    static  Long timerTimeday= Long.valueOf(60000);
    SharedPreferences pref;
    TextView showCoin;
    StoredObject so;
    TextView countDown;
    TextView countDownday;
    CountDownTimer cTimer = null;
    CountDownTimer cTimerday = null;

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
        countDownday = (TextView) findViewById(R.id.countDownDayid);
        showCoin.setText(String.valueOf(so.getCoins()));

        Long timerNow = shouldTimerRun();
        Log.e("time0", String.valueOf(timerNow));
        if (timerNow == 0) {
            countDown.setTextSize(30);
            countDown.setText("COLLECT");
        } else {
            startTimer(timerNow);
        }

        Long timerNowday = shouldTimerRunday();
        Log.e("time0", String.valueOf(timerNowday));
        if (timerNowday == 0) {
            countDownday.setTextSize(30);
            countDownday.setText("COLLECT");
        } else {
            startTimerday(timerNowday);
        }
        comingFrom =getIntent().getSerializableExtra("from");
    }


    //start timer function hour
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

    void startTimerday(Long timeLeft) {
        cTimerday = new CountDownTimer(timeLeft, 1000) {
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000;
                String show = String.format("%02d:%02d:%02d", (seconds / (1000 * 60 * 60)), seconds / 60, seconds % 60);
                countDownday.setTextSize(20);
                countDownday.setText("Collect in: " + show);
            }
            public void onFinish() {
                countDownday.setTextSize(30);
                countDownday.setText("COLLECT");
                cancelTimerday();
            }
        };
        cTimerday.start();
    }
    //cancel timer
    void cancelTimerday() {
        if(cTimerday!=null)
            cTimerday.cancel();
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

        return send;
    }
    public Long shouldTimerRunday()
    {   Long present = System.currentTimeMillis();
        Long old = so.getTimeLeftDay()!=null?so.getTimeLeftDay():0;
        Long diff = (present>old)?(present-old):0;
        Long send = diff>timerTimeday?0:(timerTimeday-diff);

        return send;
    }
    public void collectClickDay(View v )
    {
        if(countDownday.getText().equals("COLLECT")) {
            int now = so.getCoins();
            int newNow = now+50;
            so.setCoins(newNow);
            so.setTimeLeftDay(System.currentTimeMillis());
            showCoin.setText(String.valueOf(newNow));
            startTimerday(timerTimeday);
        }
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
