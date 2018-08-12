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
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerAdapterClientInfo extends RecyclerView.Adapter<RecyclerAdapterClientInfo.MyViewHolder>{
    private Context ctx;
    private ArrayList<Post> post;
    private ArrayList<String> desc;
    private String url;
    private long id;
    private ImageView imageView;
    public RecyclerAdapterClientInfo(long id,String url,ImageView imageView,ArrayList<Post> post,ArrayList<String> desc, Context ctx){
        this.ctx=ctx;
        this.post=post;
        this.desc=desc;
        this.imageView=imageView;
        this.url=url;
        this.id = id;
    }


    @Override
    public RecyclerAdapterClientInfo.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.client_card_view,parent,false);
        RecyclerAdapterClientInfo.MyViewHolder myViewHolder=new RecyclerAdapterClientInfo.MyViewHolder(url,view,post,desc,ctx,imageView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerAdapterClientInfo.MyViewHolder holder, final int position) {
        holder.post.setText(post.get(position).getName());
        holder.joblocation.setText(post.get(position).getStates().toString());
        Button apply = holder.itemView.findViewById(R.id.apply);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(),Description.class);
                intent.putExtra("id",id);
                intent.putExtra("position",position);
                intent.putExtra("desc",desc.get(position));
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        Integer size= post.size();
        return post.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ArrayList<Post> postarr;
        private ArrayList<String> descarr;
        private Context ctx;
        public TextView post,joblocation;
        private ImageView imageView;
        private String url;
        public MyViewHolder(String url,final View itemView, ArrayList<Post> postarr,final ArrayList<String> descarr, final Context ctx,ImageView imageView) {
            super(itemView);
            this.postarr=postarr;
            this.ctx=ctx;
            this.imageView=imageView;
            this.url=url;
            this.descarr=descarr;
            post= (TextView)itemView.findViewById(R.id.postdesc);
            joblocation =(TextView)itemView.findViewById(R.id.joblocation);
            Glide.with(ctx)
                    .load(url)
                    .into(imageView);
        }

    }
}
