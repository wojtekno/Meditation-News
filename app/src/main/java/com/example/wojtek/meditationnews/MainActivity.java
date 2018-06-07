package com.example.wojtek.meditationnews;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsObject>> {

    private final String JSON_REQUEST = "https://content.guardianapis.com/search?order-by=newest&q=mindfulness%20AND%20meditation&api-key=test";
    private NewsAdapter arrayAdapter;
    private View spinner;
    private TextView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.list);
        spinner = findViewById(R.id.loading_spinner);
        emptyView = findViewById(R.id.display_communicates_to_user_tv);
        arrayAdapter = new NewsAdapter(this, 0, new ArrayList<NewsObject>());

        listView.setEmptyView(emptyView);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsObject currentNewsObject = arrayAdapter.getItem(position);
                openWebPage(currentNewsObject.getmUrl());
            }
        });

        LoaderManager loaderManager = getLoaderManager();
        if (checkInternetConnection()) {
            loaderManager.initLoader(0, null, this);
        } else {
            emptyView.setText(R.string.no_internet_connection);
            spinner.setVisibility(View.GONE);
        }

    }

    @Override
    public Loader<List<NewsObject>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this, JSON_REQUEST);
    }

    @Override
    public void onLoadFinished(Loader<List<NewsObject>> loader, List<NewsObject> data) {
        emptyView.setText(R.string.no_data);
        arrayAdapter.clear();
        spinner.setVisibility(View.GONE);
        if (data != null) arrayAdapter.addAll(data);
    }

    @Override
    public void onLoaderReset(Loader<List<NewsObject>> loader) {
        arrayAdapter.clear();
    }

    public void openWebPage(String url) {
        Uri webPage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webPage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private boolean checkInternetConnection() {
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }
}