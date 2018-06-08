package com.example.wojtek.meditationnews;

public class NewsObject {
    private final String LOG_TAG = NewsObject.class.getSimpleName();

    private String mTitle;
    private String mUrl;
    private String mDate;
    private String mSectionName;
    private String mAuthorFirstName;
    private String mAuthorLastName;

    public NewsObject(String title, String url, String date, String sectionName, String authorFisrtName, String authorLastName) {
        mTitle = title;
        mUrl = url;
        mDate = date;
        mSectionName = sectionName;
        mAuthorFirstName = authorFisrtName;
        mAuthorLastName = authorLastName;
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

    public String getmAuthorFirstName() {
        return mAuthorFirstName;
    }

    public String getmAuthorLastName() {
        return mAuthorLastName;
    }
}
