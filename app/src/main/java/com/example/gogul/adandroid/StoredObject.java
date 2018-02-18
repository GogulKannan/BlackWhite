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
    private List<LevelDetails> listLevelDetails = new ArrayList<LevelDetails>();

   // private LinkedHashMap<String, levelStage> isLevelCompleted = new LinkedHashMap<String, levelStage>();
    //  public enum LevelStage {completed, notCompleted, nowplaying, locked};


    public List<LevelDetails> getListLevelDetails() {
        return listLevelDetails;
    }

    public void setListLevelDetails(List<LevelDetails> listLevelDetails) {
        this.listLevelDetails = listLevelDetails;
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
}