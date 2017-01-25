package com.example.hp.quakenetw;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.security.AccessController.getContext;

/**
 * Created by HP on 1/4/2017.
 */

public class QueryUtelis {
    public static final String LOG_TAG = QueryUtelis.class.getSimpleName();
    private static final String LOCATION_SEPARATOR = " of ";

    /**
     * Create a private constructor because no one should ever create a {@link  //object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtelis() {
    }
public static URL urlcreater(String s){
    URL url=null;
    if(s==null){
        return null;
    }
    else{try{
        url=new URL(s);}
    catch (Exception e){Log.e(LOG_TAG,"Error",e);}
    return url;}
}
    public static String makeHttpreq(URL url){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String jsonres="";
        if(url==null){
            return jsonres;
        }
        HttpURLConnection con=null;

        try {
            con = (HttpURLConnection) url.openConnection();
con.connect();
            con.setRequestMethod("GET");
            if(con.getResponseCode()==200){
                InputStream ins=con.getInputStream();
                jsonres=reader(ins);
                return jsonres;
            }
            else
                return jsonres;

        }catch (IOException e){
            Log.e(LOG_TAG,"Err",e);
        }
        return jsonres;
    }

    private static String reader(InputStream ins) {
        StringBuilder bs=new StringBuilder();
        InputStreamReader ir=new InputStreamReader(ins);
        BufferedReader bf=new BufferedReader(ir);
        try{String line=bf.readLine();
        while(line!=null){
            bs.append(line);
            line=bf.readLine();
        }}
        catch (IOException e){
            Log.e(LOG_TAG,"Err",e);
        }
        return bs.toString();
    }

    /**
     * Return a list of } objects that has been built up from
     * parsing a JSON response.
     */

    public static ArrayList<Item> extractEarthquakes(String str) {

        URL url=urlcreater(str);
        String Jsonres=makeHttpreq(url);

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<Item> earthquakes = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {
            String s3,s5;
            JSONObject obi=new JSONObject(Jsonres);
            JSONArray arr=obi.getJSONArray("features");
            for(int i=0;i<arr.length();++i){
                JSONObject prop=arr.getJSONObject(i);
                JSONObject pr=prop.getJSONObject("properties");
                double d=pr.getDouble("mag");
                String s=pr.getString("place");
                String s6=pr.getString("url");
                if (s.contains(LOCATION_SEPARATOR)) {
                    String[] parts = s.split(LOCATION_SEPARATOR);
                     s3 = parts[0] + LOCATION_SEPARATOR;
                    s5 = parts[1];
                } else {
                    s3 = "near the";
                    s5 = s;
            }
                long l=pr.getLong("time");
                Date dt=new Date(l);
                SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
                SimpleDateFormat timef=new SimpleDateFormat("h:mm a");
String s2=timef.format(dt);
                String s1=dateFormatter.format(dt);
                earthquakes.add(new Item(d,s3,s5,s1,s2,s6));
            }


            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }

}