package com.example.gymhelperapp;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CustomBaseAdapter extends BaseAdapter {

    Context context;
    List<String> exerciseList;
    List<String> imageList;

    LayoutInflater inflater;

    public CustomBaseAdapter(Context ctx, List<String> exerciseList, List<String> images){
        this.context = ctx;
        this.exerciseList = exerciseList;
        this.imageList = images;
        inflater = LayoutInflater.from(ctx);
    }


    @Override
    public int getCount() {
        return exerciseList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_list_cell, null);
        TextView txtView = (TextView) convertView.findViewById(R.id.textView);
        ImageView exerciseImg = (ImageView) convertView.findViewById(R.id.imageIcon);
        txtView.setText(exerciseList.get(position));
        Uri newUri = Uri.parse(imageList.get(position));
        exerciseImg.setImageURI(newUri);
        return convertView;
    }
}
