package com.example.defaulttabs.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.defaulttabs.R;
import com.example.defaulttabs.WallpaperModel;

import java.util.List;

public class WallpaperAdapter4 extends RecyclerView.Adapter<WallpaperViewHolder4>{
    private Fragment4 context;
    private List<WallpaperModel> wallpaperModelList4;

    public WallpaperAdapter4(Fragment4 context, List<WallpaperModel> wallpaperModelList4) {
        this.context = context;
        this.wallpaperModelList4 = wallpaperModelList4;
    }

    @NonNull
    @Override
    public WallpaperViewHolder4 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item,parent,false);

        return new WallpaperViewHolder4(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WallpaperViewHolder4 holder, int position) {

        Glide.with(context).load(wallpaperModelList4.get(position).getMediumUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return wallpaperModelList4.size();
    }
}
class WallpaperViewHolder4 extends RecyclerView.ViewHolder {
    ImageView imageView;

    public WallpaperViewHolder4(@NonNull View itemView) {
        super(itemView);
        imageView= itemView.findViewById(R.id.itemImage);
    }
}