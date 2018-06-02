package com.example.wojtek.meditationnews;

public class NewsObject {
    private String mTitle;
    private String mUrl;
    private String mDate;

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
        return mDate;
    }
}
