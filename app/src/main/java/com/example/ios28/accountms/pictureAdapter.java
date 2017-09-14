package com.example.ios28.accountms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ios28 on 17/9/14.
 */

public class pictureAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Picture> pictures;

    public pictureAdapter(String[] titles, int[] images, Context context) {
        super();
        pictures = new ArrayList<Picture>();
        inflater=LayoutInflater.from(context);
        for (int i=0;i<images.length;i++)
        {
            Picture picture = new Picture(titles[i],images[i]);
            pictures.add(picture);
        }
    }

    @Override
    public int getCount() {
        if (null!=pictures)
        {
            return pictures.size();
        }
        else
        {
            return 0;
        }
    }

    @Override
    public Object getItem(int i) {
        return pictures.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        viewHolder viewHolder;
        if (view==null)
        {
            view=inflater.inflate(R.layout.gvitem,null);
            viewHolder=new viewHolder();
            viewHolder.title=(TextView)view.findViewById(R.id.ItemTitle);
            viewHolder.image=(ImageView)view.findViewById(R.id.ItemImage);
            view.setTag(viewHolder);
        }
        else {
            viewHolder=(viewHolder)view.getTag();
        }
        viewHolder.title.setText(pictures.get(i).getTitle());
        viewHolder.image.setImageResource(pictures.get(i).getImageId());
        return view;
    }
}
