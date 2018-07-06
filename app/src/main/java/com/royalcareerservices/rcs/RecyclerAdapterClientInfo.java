package com.royalcareerservices.rcs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerAdapterClientInfo extends RecyclerView.Adapter<RecyclerAdapterClientInfo.MyViewHolder>{
    private Context ctx;
    private ArrayList<String> arrayList;
    private ArrayList<String> desc;
    private String url;
    private ImageView imageView;
    public RecyclerAdapterClientInfo(String url,ImageView imageView,ArrayList<String> arrayList,ArrayList<String> desc, Context ctx){
        this.ctx=ctx;
        this.arrayList=arrayList;
        this.desc=desc;
        this.imageView=imageView;
        this.url=url;
    }


    @Override
    public RecyclerAdapterClientInfo.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.client_card_view,parent,false);
        RecyclerAdapterClientInfo.MyViewHolder myViewHolder=new RecyclerAdapterClientInfo.MyViewHolder(url,view,arrayList,ctx,imageView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterClientInfo.MyViewHolder holder, int position) {
        holder.post.setText(arrayList.get(position));
        holder.desc.setText(desc.get(position));
    }

    @Override
    public int getItemCount() {
        Integer size= arrayList.size();
//        Log.d("Debug",size.toString());
        return arrayList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ArrayList<String> arrayList;
        private Context ctx;
        public TextView post,desc;
        private ImageView imageView;
        private String url;
        public MyViewHolder(String url,final View itemView, ArrayList<String> arrayList, final Context ctx,ImageView imageView) {
            super(itemView);
            this.arrayList=arrayList;
            this.ctx=ctx;
            this.imageView=imageView;
            this.url=url;
            post= (TextView)itemView.findViewById(R.id.postdesc);
            desc =(TextView)itemView.findViewById(R.id.desc);
            Log.d("Size2",url);

            Glide.with(ctx)
                    .load(url)
                    .into(imageView);

            Button apply = itemView.findViewById(R.id.apply);
            apply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(),Register_Activity.class);
                    ctx.startActivity(intent);
                }
            });
        }

    }
}
