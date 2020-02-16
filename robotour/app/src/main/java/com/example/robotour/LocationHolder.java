package com.example.robotour;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class LocationHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

    private TextView id;
    private TextView title;
    public Button del;
    private int num;

    public LocationHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.layout, parent, false));
        itemView.setOnClickListener(this);
        id = itemView.findViewById(R.id.id);
        title = itemView.findViewById(R.id.name);
        imger = itemView.findViewById(R.id.imageViewer);
        del = itemView.findViewById(R.id.delete);
    }

    public void bind(Image img, int position) {
        mImage = img;
        id.setText((String.valueOf(img.getID())));
        title.setText(img.getName());
        num = img.getID();
    }
    public int delete()
    {
        return num;
    }
    @Override
    public void onClick(View view) {
    }

}