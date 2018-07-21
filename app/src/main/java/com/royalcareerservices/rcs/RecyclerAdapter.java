package com.royalcareerservices.rcs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder1> {
    private Context ctx;
    private ArrayList<ClientDetails> arrayList;

    public RecyclerAdapter(ArrayList<ClientDetails> arrayList, Context ctx){
        this.ctx=ctx;
        this.arrayList=arrayList;
    }

    @Override
    public MyViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        MyViewHolder1 myViewHolder=new MyViewHolder1(view,arrayList,ctx);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder1 holder, final int position) {
        holder.name.setText(arrayList.get(position).getName());
        holder.num.setText(arrayList.get(position).getOpenings());
        holder.clientDetails = arrayList.get(position);
        holder.url=arrayList.get(position).getUrl();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx,ClientInfo.class);
                intent.putExtra("name",arrayList.get(position).getName());
                intent.putExtra("id",arrayList.get(position).getId());
                intent.putExtra("post",arrayList.get(position).getPost());
                intent.putExtra("desc",arrayList.get(position).getDescription());
                intent.putExtra("url",arrayList.get(position).getUrl());
                ctx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        Integer size= arrayList.size();
        return arrayList.size();
    }

    public class MyViewHolder1 extends RecyclerView.ViewHolder {
        private ArrayList<ClientDetails> arrayList;
        private Context ctx;
        private TextView name,num;
        private String url;
        ClientDetails clientDetails;
        public MyViewHolder1(View itemView, ArrayList<ClientDetails> arrayList, final Context ctx) {
            super(itemView);
            this.arrayList=arrayList;
            this.ctx=ctx;
            name= (TextView)itemView.findViewById(R.id.name);
            num =(TextView)itemView.findViewById(R.id.number);
        }

    }
}
