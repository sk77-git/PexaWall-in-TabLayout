package com.example.defaulttabs;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.defaulttabs.fragments.Fragment4;
import com.example.defaulttabs.fragments.Fragment6;

import java.net.CookieHandler;
import java.util.List;

public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperViewHolder> {
    private Fragment6 context;
    private List<WallpaperModel> wallpaperModelList;
    private OnImageItemClickListener imageItemClickListener;



    public WallpaperAdapter(Fragment6 context, List<WallpaperModel> wallpaperModelList) {
        this.context = context;
        this.wallpaperModelList = wallpaperModelList;
    }

    @NonNull
    @Override
    public WallpaperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item,parent,false);
            WallpaperViewHolder wallpaperViewHolder= new WallpaperViewHolder(view);
            return new WallpaperViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WallpaperViewHolder holder, int position) {
        Glide.with(context).load(wallpaperModelList.get(position).getMediumUrl()).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return wallpaperModelList.size();
    }

    public interface OnImageItemClickListener {
        public void onImageClicked(WallpaperModel wallpaperModel);
    }
}



class WallpaperViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    View view;
    ImageView imageView;
    private List<WallpaperModel> wallpaperModelList;


    public WallpaperViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.itemImage);
        view=itemView.findViewById(R.id.imageItemLayout);

        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();
        Toast.makeText(v.getContext(), "Image Clicked", Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(v.getContext(),Fullscreen.class);
        //intent.putExtra("original",wallpaperModelList.get(position).getOriginalUrl());
        v.getContext().startActivity(intent);


    }
}
