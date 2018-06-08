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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewsAdapter extends ArrayAdapter<NewsObject> {

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
        String currentDate = currentNewsObject.getmDate();

        TextView titleTV = newsListItem.findViewById(R.id.title_tv);
        titleTV.setText(currentNewsObject.getmTitle());

        TextView monthTV = newsListItem.findViewById(R.id.month_tv);
        monthTV.setText(getMonthAndDayToPrint(currentDate));

        TextView sectionNameTV = newsListItem.findViewById(R.id.section_tv);
        sectionNameTV.setText(currentNewsObject.getmSectionName());

        TextView authorTV = newsListItem.findViewById(R.id.author_tv);
        String firstName = currentNewsObject.getmAuthorFirstName();
        String lastName = currentNewsObject.getmAuthorLastName();
        authorTV.setText(getAuthorNameToPrint(firstName, lastName));


        return newsListItem;
    }


    private String getMonthAndDayToPrint(String mDate) {
        if (mDate == null || mDate.equals("")) {
            return null;
        }
        int length = mDate.length();
        String sDate1 = mDate.substring(0, length - 1);
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(sDate1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd");
        return formatter.format(date1);
    }

    private String getAuthorNameToPrint(String firstName, String lastName) {
        if ((firstName == null || firstName.equals("")) & (lastName == null || lastName.equals(""))) {
            return null;
        } else if (firstName == null || firstName.equals("")) {
            lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
            return lastName;
        } else if (lastName == null || lastName.equals("")) {
            firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
            return firstName;
        } else {
            firstName = firstName.substring(0, 1).toUpperCase();
            lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
            String output = String.format("%s. %s", firstName, lastName);
            return output;
        }
    }
}
