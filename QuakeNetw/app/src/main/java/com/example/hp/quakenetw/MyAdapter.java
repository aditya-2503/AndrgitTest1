package com.example.hp.quakenetw;

import android.content.ClipData;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by HP on 1/4/2017.
 */

public class MyAdapter extends ArrayAdapter {

    ArrayList<Item>eq=new ArrayList<Item>();
    public MyAdapter(Context context, int textViewResourceId, ArrayList<Item> objects){
        super(context,textViewResourceId,objects);
        eq=objects;

    }
    public int getCount() {
        return super.getCount();
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater ilf=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);;
        v=ilf.inflate(R.layout.list_view,null);

        TextView textView1 = (TextView) v.findViewById(R.id.txt1);
        TextView textView2 = (TextView) v.findViewById(R.id.txt2);
        TextView textView3 = (TextView) v.findViewById(R.id.txt3);
        TextView textView4 = (TextView) v.findViewById(R.id.txt4);
        TextView textView5 = (TextView) v.findViewById(R.id.txt5);
        GradientDrawable magnitudeCircle = (GradientDrawable) textView1.getBackground();
        String s6=eq.get(position).getUrl();

        // Get the appropriate background color based on the current earthquake magnitude
        double mg=eq.get(position).getMag();
        int magnitudeColor = getMagnitudeColor(mg);
        magnitudeCircle.setColor(magnitudeColor);

        DecimalFormat df=new DecimalFormat("0.0");
        String s=df.format((eq.get(position).getMag()));
        textView1.setText(s);
        textView2.setText(eq.get(position).getPlace());
        textView3.setText(eq.get(position).getDate());
        textView4.setText(eq.get(position).getTime());
        textView5.setText(eq.get(position).getOffset());
        return v;
    }
    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

}

