package com.example.robotour;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationHolder> {
    public Locationer locationer;

    public LocationAdapter(Locationer locationer) {
        this.locationer = locationer;
    }

    @Override
    public LocationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        db = ImageDatabase.getInstance(parent.getContext());
        return new ImageHolder(layoutInflater, parent);
    }

    @Override
    public void onBindViewHolder(final LocationHolder holder, final int position){
        holder.bind(mImage.get(position), position);
        holder.del.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                db.imageDelete(holder.delete());
                mImage.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, getItemCount());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImage.size();
    }
}
