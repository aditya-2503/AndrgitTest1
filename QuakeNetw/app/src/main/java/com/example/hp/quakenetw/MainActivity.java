package com.example.hp.quakenetw;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Item>> {
String ugs=" http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=5&limit=10";

    MyAdapter adapter1;
    TextView tv,tv2;
    ProgressBar pg;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     /*  BGEQ bg=new BGEQ();
        bg.execute(ugs);*/
        tv2=(TextView)findViewById(R.id.tv2);
        tv=(TextView)findViewById(R.id.tv1);
        pg=(ProgressBar)findViewById(R.id.pgbar);
        ConnectivityManager cm = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        LoaderManager ld=getLoaderManager();
        if(isConnected)
ld.initLoader(0,null,this);
        else{
        tv2.setText("No Internet");
            pg.setVisibility(View.GONE);
        }




    }

    @Override
    public Loader<ArrayList<Item>> onCreateLoader(int i, Bundle bundle) {
        return new EqLoader(MainActivity.this,ugs);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Item>> loader, ArrayList<Item> items) {
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes_list_item, eq);
        // Set the adapter on the {@link ListView}

      adapter1 = new MyAdapter(this, android.R.layout.activity_list_item,items);
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter1);
        earthquakeListView.setEmptyView(tv);
        tv.setText("NO EarthquakeFound");
        pg.setVisibility(View.GONE);
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Item it=(Item)adapter1.getItem(i);
                Uri uty=Uri.parse(it.getUrl());
                Intent in=new Intent(Intent.ACTION_VIEW,uty);
                startActivity(in);
            }
        });
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Item>> loader) {
adapter1.clear();
    }

   /* public class BGEQ extends AsyncTask<String,Void,ArrayList<Item>>{

        @Override
        protected ArrayList<Item> doInBackground(String... urls) {
            ArrayList<Item> earthquakes=QueryUtelis.extractEarthquakes(urls[0]);
            return earthquakes;
        }
        public void onPostExecute(ArrayList<Item> eq){
            updateui(eq);

        }
    }

    private void updateui(ArrayList<Item> eq) {
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes_list_item, eq);
        // Set the adapter on the {@link ListView}

        final MyAdapter adapter1 = new MyAdapter(this, android.R.layout.activity_list_item,eq);
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter1);
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Item it=(Item)adapter1.getItem(i);
                Uri uty=Uri.parse(it.getUrl());
                Intent in=new Intent(Intent.ACTION_VIEW,uty);
                startActivity(in);
            }
        });
    }*/

}
