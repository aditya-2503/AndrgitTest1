package com.example.hp.quakenetw;

import android.content.Context;
import android.content.AsyncTaskLoader;

import java.util.ArrayList;

/**
 * Created by HP on 1/6/2017.
 */

public class EqLoader extends AsyncTaskLoader<ArrayList<Item>> {
    String s;
    public EqLoader(Context context,String str){
        super(context);
        s=str;
    }
    protected void onStartLoading() {
        forceLoad();
    }
    @Override
    public ArrayList<Item> loadInBackground() {
        ArrayList<Item> earthquakes=QueryUtelis.extractEarthquakes(s);
        return earthquakes;

    }
}
