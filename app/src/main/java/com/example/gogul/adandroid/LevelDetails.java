package com.example.gogul.adandroid;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by e0046709 on 1/18/2017.
 */

public class LevelDetails extends java.util.HashMap<String,String> {


    public LevelDetails(String levelMode, String levelNumber, String levelStage) {
        put("LevelMode", levelMode);
        put("LevelNumber", levelNumber);
        put("LevelStage", levelStage);
    }

    public LevelDetails() {
    }


}