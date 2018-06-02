package com.example.wojtek.meditationnews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.util.List;

public class NewsAdapter extends ArrayAdapter<NewsObject> {
    private final String LOG_TAG = NewsAdapter.class.getSimpleName();


    public NewsAdapter(@NonNull Context context, int resource, @NonNull List<NewsObject> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View newsListItem = convertView;
        if (convertView == null) {
            newsListItem = LayoutInflater.from(getContext()).inflate(R.layout.news_list_item, parent, false);
        }
        NewsObject currentNewsObject = getItem(position);

        TextView titleTV = newsListItem.findViewById(R.id.title_tv);
        titleTV.setText(currentNewsObject.getmTitle());

        TextView dateTV = newsListItem.findViewById(R.id.date_tv);
        dateTV.setText(currentNewsObject.getmDate());

        return newsListItem;
    }
}
