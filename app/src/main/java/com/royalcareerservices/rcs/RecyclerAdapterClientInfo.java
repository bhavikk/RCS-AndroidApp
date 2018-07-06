package com.royalcareerservices.rcs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapterClientInfo extends RecyclerView.Adapter<RecyclerAdapterClientInfo.MyViewHolder>{
    private Context ctx;
    private ArrayList<String> arrayList;

    public RecyclerAdapterClientInfo(ArrayList<String> arrayList, Context ctx){
        this.ctx=ctx;
        this.arrayList=arrayList;
    }


    @Override
    public RecyclerAdapterClientInfo.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.client_card_view,parent,false);
        RecyclerAdapterClientInfo.MyViewHolder myViewHolder=new RecyclerAdapterClientInfo.MyViewHolder(view,arrayList,ctx);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterClientInfo.MyViewHolder holder, int position) {
        holder.post.setText(arrayList.get(position));
        holder.desc.setText("Description here");
        Log.d("HERE", "onBindViewHolder: "+arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        Integer size= arrayList.size();
        Log.d("Debug",size.toString());
        return arrayList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ArrayList<String> arrayList;
        private Context ctx;
        public TextView post,desc;
        public MyViewHolder(View itemView, ArrayList<String> arrayList, final Context ctx) {
            super(itemView);
            this.arrayList=arrayList;
            this.ctx=ctx;
            post= (TextView)itemView.findViewById(R.id.postdesc);
            desc =(TextView)itemView.findViewById(R.id.desc);
        }

    }
}
