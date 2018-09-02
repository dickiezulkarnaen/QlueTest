package com.example.dickiez.cluetest;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dickiez.cluetest.Model.Data;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyHolder> {

    List<Data> dataList;
    Context context;

    public CustomAdapter(List<Data> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_data, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        holder.username.setText(dataList.get(position).getUsername());
        holder.kelurahan.setText(dataList.get(position).getKelurahan());
        holder.description.setText(dataList.get(position).getDescription());

        Picasso.with(holder.itemView.getContext())
                .load(dataList.get(position).getImage_url())
                .into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data data = dataList.get(position);
                Intent i = new Intent(holder.itemView.getContext(), DetailActivity.class);
                i.putExtra("id", new GsonBuilder().create().toJson(data));
                holder.itemView.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView username;
        TextView kelurahan;
        TextView description;
        ImageView image;

        public MyHolder(View itemView) {
            super(itemView);

            username = (TextView) itemView.findViewById(R.id.txt_username);
            kelurahan = (TextView) itemView.findViewById(R.id.txt_kelurahan);
            description = (TextView) itemView.findViewById(R.id.txt_descripion);
            image = (ImageView) itemView.findViewById(R.id.img);

        }
    }
}
