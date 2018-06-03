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
    private String mSectionName;
    private String mAuthor;

    public NewsObject(String title, String url, String date, String sectionName) {
        mTitle = title;
        mUrl = url;
        mDate = date;
        mSectionName = sectionName;

    }

    public NewsObject(String title, String url, String date, String sectionName, String author) {
        mTitle = title;
        mUrl = url;
        mDate = date;
        mSectionName = sectionName;
        mAuthor = author;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmUrl() {
        return mUrl;
    }

    public String getmDate() {
       return mDate;
    }

    public String getmSectionName() {
        return mSectionName;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public String printDate() throws ParseException {
        int length = mDate.length();
        String sDate1= mDate.substring(0, length-1);
        Date date1=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(sDate1);
        SimpleDateFormat formatter = new SimpleDateFormat("MMM/dd/yyyy");

        return formatter.format(date1);
    }
}
