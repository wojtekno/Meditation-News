package com.example.wojtek.meditationnews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<NewsObject> {


    public NewsAdapter(@NonNull Context context, int resource, @NonNull List<NewsObject> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View newsListItem = convertView;
        if(convertView == null) {
            newsListItem = LayoutInflater.from(getContext()).inflate(R.layout.news_list_item,parent,false);
        }
        NewsObject newsObject = getItem(position);

        TextView titleTV = newsListItem.findViewById(R.id.title_tv);
        titleTV.setText(newsObject.getmTitle());

        return newsListItem;
    }
}
