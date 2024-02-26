package com.example.hw04;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

public class CustomListViewAdapter extends ArrayAdapter<String> {
    Context context;
    Integer[] avatars;
    String[] fullnames;
    String[] phones;
    private int selectedItemPosition = -1;

    public CustomListViewAdapter(Context context, int layoutToBeInflated, String[] fullnames, String[] phones, Integer[] avatars) {
        super(context, R.layout.row_layout, fullnames);
        this.context = context;
        this.avatars = avatars;
        this.fullnames = fullnames;
        this.phones = phones;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View row;
        if (convertView == null){
            row = inflater.inflate(R.layout.row_layout, null);
        }else{
            row = (View) convertView;
        }
        TextView fullnameLabel = (TextView) row.findViewById(R.id.fullname);
        TextView phoneLabel = (TextView) row.findViewById(R.id.phone);
        ImageView avatar = (ImageView) row.findViewById(R.id.avatar);
        fullnameLabel.setText(fullnames[position]);
        phoneLabel.setText(phones[position]);
        avatar.setImageResource(avatars[position]);
        if (position == selectedItemPosition) {
            // Set background color for the selected item
            row.setBackgroundColor(0xffffe18f);
        } else {
            // Set default background color for other items
            row.setBackgroundColor(0xffffffff);
        }

        return (row);
    }

    public void setSelectedItemPosition(int position) {
        selectedItemPosition = position;
    }
}
