package com.example.wojtek.meditationnews;

import android.app.LoaderManager;
import android.arch.lifecycle.livedata.core.BuildConfig;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsObject>> {

    private final String GUARDIAN_API_KEY = com.example.wojtek.meditationnews.BuildConfig.GuardianApiKey;
    private final String JSON_EDITABLE = "https://content.guardianapis.com/search?show-tags=contributor&q=meditation%20AND%20mindfulness&api-key=" + GUARDIAN_API_KEY;
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
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        String orderBy = sharedPrefs.getString(getString(R.string.settings_order_by_key), getString(R.string.settings_order_by_default));
        String showSections = sharedPrefs.getString(getString(R.string.settings_show_sections_key), getString(R.string.settings_show_sections_default));
        String pageSize = sharedPrefs.getString(getString(R.string.settings_page_size_key), getString(R.string.settings_page_size_default));

        Uri baseUri = Uri.parse(JSON_EDITABLE);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("order-by", orderBy);
        uriBuilder.appendQueryParameter("page-size", pageSize);
        if (!showSections.equals("")) {
            uriBuilder.appendQueryParameter("section", showSections);
        }

        return new NewsLoader(this, uriBuilder.toString());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}