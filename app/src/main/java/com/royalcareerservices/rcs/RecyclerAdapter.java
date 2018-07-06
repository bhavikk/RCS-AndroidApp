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
    public void onBindViewHolder(MyViewHolder1 holder, int position) {
        holder.name.setText(arrayList.get(position).getName());
        holder.num.setText(arrayList.get(position).getOpenings());
        holder.clientDetails = arrayList.get(position);
        Log.d("HERE", "onBindViewHolder: "+arrayList.get(position).getPost().get(0));
    }

    @Override
    public int getItemCount() {
        Integer size= arrayList.size();
        Log.d("Debug",size.toString());
        return arrayList.size();
    }

    public class MyViewHolder1 extends RecyclerView.ViewHolder {
        private ArrayList<ClientDetails> arrayList;
        private Context ctx;
        private TextView name,num;
        ClientDetails clientDetails;
        public MyViewHolder1(View itemView, ArrayList<ClientDetails> arrayList, final Context ctx) {
            super(itemView);
            this.arrayList=arrayList;
            this.ctx=ctx;
            name= (TextView)itemView.findViewById(R.id.name);
            num =(TextView)itemView.findViewById(R.id.number);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ctx,ClientInfo.class);
                    intent.putExtra("post",clientDetails.getPost());
                    intent.putExtra("desc",clientDetails.getDescription());
                    ctx.startActivity(intent);
                }
            });
        }

    }
}
