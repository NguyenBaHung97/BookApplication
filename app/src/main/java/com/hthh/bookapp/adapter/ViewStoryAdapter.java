package com.hthh.bookapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hthh.bookapp.R;
import com.hthh.bookapp.model.LinkImage;

import java.util.List;

public class ViewStoryAdapter extends RecyclerView.Adapter<ViewStoryAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<LinkImage> images;

    public ViewStoryAdapter(Context context, List<LinkImage> images) {
        this.context = context;
        this.images = images;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_view_story, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LinkImage linkImage = images.get(position);
        Log.d("8911", "onBindViewHolder: " + linkImage.getImage().substring(2).replaceFirst("https","http"));
        Glide.with(context).load(linkImage.getImage().substring(2).replaceFirst("https","http")).into(holder.imgStory);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgStory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgStory = itemView.findViewById(R.id.imgStory);
        }
    }
}
