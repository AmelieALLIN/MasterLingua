package com.example.masterlingua;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

public class CustomerAdapter extends BaseAdapter {


    Context context;
    List<Bitmap> img;
    LayoutInflater inflter;

    public CustomerAdapter(Context applicationContext, List<Bitmap> img) {
        this.context = applicationContext;
        this.img = img;
        inflter = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return img.size();
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.question_image_item, null);
        ImageView image = (ImageView) view.findViewById(R.id.imgQuestion);
        image.setImageBitmap(img.get(i));
        return view;
    }
}
