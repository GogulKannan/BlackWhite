package com.example.gogul.adandroid;

import android.util.Log;



import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class StoredObject implements Serializable {

    static final long serialVersionUID = 0L;
    private int coins = 0;
    private Long timeLeftHour;
    private Long timeLeftDay;
    private boolean soundOn;
    private List<LevelDetails> listLevelDetails = new ArrayList<LevelDetails>();

    private int[][] currentLearnBox = new int[2][2];

   // private LinkedHashMap<String, levelStage> isLevelCompleted = new LinkedHashMap<String, levelStage>();
    //  public enum LevelStage {completed, notCompleted, nowplaying, locked};


    public List<LevelDetails> getListLevelDetails() {
        return listLevelDetails;
    }

    public void setListLevelDetails(List<LevelDetails> listLevelDetails) {
        this.listLevelDetails = listLevelDetails;
    }

    public boolean isSoundOn() {
        return soundOn;
    }

    public void setSoundOn(boolean soundOn) {
        this.soundOn = soundOn;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public Long getTimeLeftHour() {
        return timeLeftHour;
    }

    public void setTimeLeftHour(Long timeLeftHour) {
        this.timeLeftHour = timeLeftHour;
    }

    public Long getTimeLeftDay() {
        return timeLeftDay;
    }

    public void setTimeLeftDay(Long timeLeftDay) {
        this.timeLeftDay = timeLeftDay;
    }

    public int[][] getCurrentLearnBox() {
        return currentLearnBox;
    }

    public void setCurrentLearnBox(int[][] currentLearnBox) {
        this.currentLearnBox = currentLearnBox;
    }
}