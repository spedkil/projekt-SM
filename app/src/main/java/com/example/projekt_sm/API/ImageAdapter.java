package com.example.projekt_sm.API;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projekt_sm.R;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder>{

    private Context context;
    private List<ImageModel> imageModelList;

    public ImageAdapter(Context context, List<ImageModel> imageModelList) {
        this.context = context;
        this.imageModelList = imageModelList;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Glide.with(context).load(imageModelList.get(position).getMediumUrl()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, FullscreenImage.class).putExtra("mediumUrl", imageModelList.get(position).getMediumUrl()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageModelList.size();
    }
}

class ImageViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    public ImageViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageViewItem);
    }
}
