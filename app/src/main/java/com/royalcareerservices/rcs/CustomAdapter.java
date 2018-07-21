package com.royalcareerservices.rcs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {


    Context context;
    int flags[];
    String[] countryNames;
    LayoutInflater inflater;


    public CustomAdapter(Context applicationContext,int[] flags,String[] countryNames){

        this.context = applicationContext;
        this.flags = flags;
        this.countryNames = countryNames;
        inflater= (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return flags.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.spinner,null);
        ImageView icon = (ImageView)view.findViewById(R.id.img);
        TextView names = (TextView) view.findViewById(R.id.tt);
        icon.setImageResource(flags[1]);
        names.setText(countryNames[1]);
        return view;
    }
}
