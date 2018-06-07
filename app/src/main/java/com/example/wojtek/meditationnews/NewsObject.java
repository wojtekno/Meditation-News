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

}
