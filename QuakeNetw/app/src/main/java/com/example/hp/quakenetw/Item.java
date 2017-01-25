package com.example.hp.quakenetw;

/**
 * Created by HP on 1/4/2017.
 */

public class Item {
    double mag;
    String offset;
    String place;
    String date;
    String time;
    String url;
    public Item(double s1,String s5,String s2,String s3,String s4,String s6){
        mag=s1;
        place=s2;
        date=s3;
        time=s4;
        offset=s5;
        url=s6;
    }

    public double getMag() {
        return mag;
    }

    public String getUrl() {
        return url;
    }

    public String getOffset() {
        return offset;

    }

    public String getTime() {

        return time;
    }

    public String getPlace() {
        return place;
    }

    public String getDate() {
        return date;
    }
}
