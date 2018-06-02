package com.example.wojtek.meditationnews;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<NewsObject>> {
    private String url;
    private final String LOG_TAG = NewsLoader.class.getSimpleName();

    public NewsLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        Log.e(LOG_TAG, "onStartLoading()");
        forceLoad();
    }

    @Override
    public List<NewsObject> loadInBackground() {
        Log.e(LOG_TAG, "loadInBackground");
        List<NewsObject> newsObjectList = QueryUtils.doEverything(url);
//        Log.e(LOG_TAG, "value of newsObjectList[0]" + newsObjectList.get(0));
        return newsObjectList;
    }
}
