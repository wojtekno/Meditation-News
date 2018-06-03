package com.example.wojtek.meditationnews;

import android.util.Log;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public final class QueryUtils {
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();


    private QueryUtils() {

    }

    public static List<NewsObject> doEverything(String urlString) {
        URL url = makeURL(urlString);
        List<NewsObject> newsObjectList = null;
        try {
            newsObjectList = extractNewsFromData(fetchData(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newsObjectList;
    }

    public static List<NewsObject> extractNewsFromData(String jsonResponseString) {
        List<NewsObject> newsObjectList = new ArrayList<NewsObject>();
        try {
            JSONObject mainJSONObject = new JSONObject(jsonResponseString);
            JSONObject responseObject = mainJSONObject.getJSONObject("response");
            JSONArray resultsArray = responseObject.getJSONArray("results");

            for (int i = 0; i < resultsArray.length(); i++) {
                JSONObject resultObject = resultsArray.getJSONObject(i);

                String title = resultObject.getString("webTitle");
                String url = resultObject.getString("webUrl");
                String date = resultObject.getString("webPublicationDate");
//                String author = resultObject.getString("author");
                String sectionName = resultObject.getString("sectionName");

                newsObjectList.add(new NewsObject(title, url, date, sectionName));
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "exception when extracting jsonResponse", e);

        } finally {

        }

        return newsObjectList;
    }

    public static URL makeURL(String urlString) {
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
//            e.printStackTrace();
            Log.e(LOG_TAG, "Problem with creating URL", e);
        }
        return url;
    }

    public static String fetchData(URL url) throws IOException {
        String stringJSONResponse = null;
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                inputStream = httpURLConnection.getInputStream();
                stringJSONResponse = readFromStream(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) httpURLConnection.disconnect();
            if (inputStream != null) inputStream.close();
        }

        return stringJSONResponse;
    }

    private static String readFromStream(InputStream inputStream) {
        String output;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder = new StringBuilder();

        try {
            String line = bufferedReader.readLine();
            stringBuilder.append(line);
            while (line != null) {
                line = bufferedReader.readLine();
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        output = stringBuilder.toString();
        return output;
    }


}
