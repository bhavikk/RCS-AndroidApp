package com.royalcareerservices.rcs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapterClients extends RecyclerView.Adapter<RecyclerAdapterClients.MyViewHolder1> {
    private Context ctx;
    private ArrayList<Clients> arrayList;

    public RecyclerAdapterClients(ArrayList<Clients> arrayList, Context ctx){
        this.ctx=ctx;
        this.arrayList=arrayList;
    }

    @Override
    public RecyclerAdapterClients.MyViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.clientscardview,parent,false);
        RecyclerAdapterClients.MyViewHolder1 myViewHolder=new RecyclerAdapterClients.MyViewHolder1(view,arrayList,ctx);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterClients.MyViewHolder1 holder, final int position) {
        String names[] = arrayList.get(position).getName().split(" ");
        holder.name1.setText(names[0]);
        holder.name2.setText(names[1]);
    }


    @Override
    public int getItemCount() {
        Integer size= arrayList.size();
        return arrayList.size();
    }

    public class MyViewHolder1 extends RecyclerView.ViewHolder {
        private ArrayList<Clients> arrayList;
        private Context ctx;
        private TextView name1,name2;
        private String url;
        Clients client;
        public MyViewHolder1(View itemView, ArrayList<Clients> arrayList, final Context ctx) {
            super(itemView);
            this.arrayList=arrayList;
            this.ctx=ctx;
            name1= (TextView)itemView.findViewById(R.id.name1);
            name2=(TextView)itemView.findViewById(R.id.name2);
        }
    }
}
