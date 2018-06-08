package com.example.wojtek.meditationnews;

import android.util.Log;

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
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class QueryUtils {
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();
    private final static int URL_READ_TIME_OUT = 10000;
    private final static int URL_CONNECTION_TIME_OUT = 15000;

    public static List<NewsObject> fetchNewsData(String urlString) {
        URL url = makeURL(urlString);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpReq(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        List<NewsObject> newsObjectList = extractNewsFromData(jsonResponse);
        return newsObjectList;
    }

    private static List<NewsObject> extractNewsFromData(String jsonResponseString) {
        if (jsonResponseString == null) return null;
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
                String sectionName = null;
                if (resultObject.has("sectionName")) {
                    sectionName = resultObject.getString("sectionName");
                }

                JSONArray tagsArray = resultObject.getJSONArray("tags");
                String authorFirstName = null;
                String authorLastName = null;
                if (tagsArray.length() > 0) {
                    JSONObject contributorTag = tagsArray.getJSONObject(0);
                    authorFirstName = contributorTag.getString("firstName");
                    authorLastName = contributorTag.getString("lastName");
                }
                newsObjectList.add(new NewsObject(title, url, date, sectionName, authorFirstName, authorLastName));
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);
        }

        return newsObjectList;
    }

    private static URL makeURL(String urlString) {
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem with creating URL", e);
        }
        return url;
    }

    private static String makeHttpReq(URL url) throws IOException {
        String stringJsonResponse = null;
        if (url == null) return stringJsonResponse;

        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(URL_READ_TIME_OUT);
            httpURLConnection.setConnectTimeout(URL_CONNECTION_TIME_OUT);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                inputStream = httpURLConnection.getInputStream();
                stringJsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + httpURLConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (httpURLConnection != null) httpURLConnection.disconnect();
            if (inputStream != null) inputStream.close();
        }

        return stringJsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                line = bufferedReader.readLine();
            }
        }
        return stringBuilder.toString();
    }
}