package com.example.gogul.adandroid;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.example.gogul.adandroid.StoredObject.*;

/**
 * Created by e0046709 on 1/18/2017.
 */


public class StoredObjectFunction implements Serializable {
    static final long serialVersionUID =0L;

   private boolean firstTimeLoad;

    public StoredObject defaultValue()
    {
        LevelDetails levelDetail = new LevelDetails();
        StoredObject so = new StoredObject();
        List<LevelDetails> list = new ArrayList<LevelDetails>();
        list.add(new LevelDetails("1","Level: 1","Completed"));
        list.add(new LevelDetails("1","Level: 2","Now Playing"));
        list.add(new LevelDetails("1","Level: 3","Not Completed"));
        list.add(new LevelDetails("1","Level: 4","Locked"));
        list.add(new LevelDetails("1","Level: 5","Locked"));
        list.add(new LevelDetails("1","Level: 6","Locked"));
        list.add(new LevelDetails("1","Level: 7","Locked"));
        list.add(new LevelDetails("1","Level: 8","Locked"));
        list.add(new LevelDetails("1","Level: 9","Locked"));
        list.add(new LevelDetails("1","Level: 10","Locked"));
        so.setListLevelDetails(list);
        so.setTimeLeftHour(System.currentTimeMillis());
        so.setTimeLeftDay(System.currentTimeMillis());
        so.setCoins(50);
        return so;

    }


    public StoredObject loadAllObject(File file)
    {
        FileInputStream fis = null;
        StoredObject sob = null;
        try {
            fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            sob= (StoredObject) ois.readObject();
            Log.e("load all:", String.valueOf(sob.getCoins()));
            String s = sob.toString();
            setFirstTimeLoad(false);
            ois.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            setFirstTimeLoad(true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sob;
    }
    public void saveObject(File file,StoredObject so)
    {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(so);


            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    public boolean isFirstTimeLoad() {
        return firstTimeLoad;
    }

    public void setFirstTimeLoad(boolean firstTimeLoad) {
        this.firstTimeLoad = firstTimeLoad;
    }
    }