package com.example.wojtek.meditationnews;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewsObject {
    private final String LOG_TAG = NewsObject.class.getSimpleName();

    private String mTitle;
    private String mUrl;
    private String mDate;
    private Date mDateDate;

    public NewsObject(String title, String url, String date) {
        mTitle = title;
        mUrl = url;
        mDate = date;

    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmUrl() {
        return mUrl;
    }

    public String getmDate() {
        int length = mDate.length();
        String sDate1= mDate.substring(0, length-1);
        Date date1= null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(sDate1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("MMM/dd/yyyy");
        return formatter.format(date1);
    }


    public String printDate() throws ParseException {
        int length = mDate.length();
        String sDate1= mDate.substring(0, length-1);
        Date date1=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(sDate1);
        SimpleDateFormat formatter = new SimpleDateFormat("MMM/dd/yyyy");

        return formatter.format(date1);
    }
}
