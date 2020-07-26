package com.hthh.bookapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hthh.bookapp.R;
import com.hthh.bookapp.model.ChapStory;

import java.util.List;

public class ChapAdapter extends RecyclerView.Adapter<ChapAdapter.ViewHolder> {
    private Context context;
    private List<ChapStory> chapStories;
    private LayoutInflater inflater;

    public ChapAdapter(Context context, List<ChapStory> chapStories) {
        this.context = context;
        this.chapStories = chapStories;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_chap, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChapStory chapStory = chapStories.get(position);
        holder.txtName.setText(chapStory.getName_chap());
        holder.txtTime.setText(chapStory.getDate());
    }

    @Override
    public int getItemCount() {
        return chapStories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private TextView txtTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtNameChapter);
            txtTime = itemView.findViewById(R.id.txtTime);
        }
    }
}
