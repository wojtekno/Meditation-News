package com.example.wojtek.meditationnews;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<NewsObject>> {
    private String url;

    public NewsLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<NewsObject> loadInBackground() {
        if (url == null) return null;
        List<NewsObject> newsObjectList = QueryUtils.fetchNewsData(url);
        return newsObjectList;
    }
}
