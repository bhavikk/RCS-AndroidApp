package com.royalcareerservices.rcs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private Context ctx;
    private ArrayList<ClientDetails> arrayList;

    public RecyclerAdapter(ArrayList<ClientDetails> arrayList, Context ctx){
        this.ctx=ctx;
        this.arrayList=arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view,arrayList,ctx);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.name.setText(arrayList.get(position).getName());
        holder.num.setText(arrayList.get(position).getNumberOpenings());
    }

    @Override
    public int getItemCount() {
        Integer size= arrayList.size();
        Log.d("ARRAY SIZE",size.toString());
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ArrayList<ClientDetails> arrayList;
        private Context ctx;
        private TextView name,num;
        public MyViewHolder(View itemView,ArrayList<ClientDetails> arrayList,Context ctx) {
            super(itemView);
            this.arrayList=arrayList;
            this.ctx=ctx;
            name= (TextView)itemView.findViewById(R.id.name);
            num =(TextView)itemView.findViewById(R.id.number);
        }

    }
}
