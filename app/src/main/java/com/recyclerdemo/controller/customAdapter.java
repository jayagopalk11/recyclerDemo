package com.recyclerdemo.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.recyclerdemo.R;
import com.recyclerdemo.model.ListItem;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;

/**
 * Created by Jai on 4/14/2017.
 */

public class customAdapter extends RecyclerView.Adapter<customAdapter.customHolder>{


    public ItemClickCallback ItemClickCallback;



    public interface ItemClickCallback{
        void onImageClick(int p);
        void onItemClick(int p);
    }

    public void setItemClickCallback(final ItemClickCallback ItemClickCallback){
        this.ItemClickCallback = ItemClickCallback;
    }

    public List<ListItem> data;
    private List<ListItem> orig;
    private LayoutInflater inflater;

    public customAdapter(List<ListItem> data, Context c) {
        this.inflater = LayoutInflater.from(c);
        this.data = data;
    }

    @Override
    public customHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new customHolder(view);
    }

    @Override
    public void onBindViewHolder(customHolder holder, int position) {
        ListItem a = data.get(position);
        holder.title.setText(a.getTitle());
        holder.icon.setImageResource(a.getImageResId());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }




    class customHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView title;
        public ImageView icon;
        public View container;

        public customHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.ListText);
            icon = (ImageView) itemView.findViewById(R.id.listImage);
            container = itemView.findViewById(R.id.cont_item_root);
            container.setOnClickListener(this);
            icon.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(view.getId()==R.id.listImage){
                ItemClickCallback.onImageClick(getAdapterPosition());
            }else{
                ItemClickCallback.onItemClick(getAdapterPosition());
            }
        }
    }
}
