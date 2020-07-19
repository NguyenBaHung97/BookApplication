package com.hthh.bookapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hthh.bookapp.R;
import com.hthh.bookapp.model.Story;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {
    private Context context;
    private List<Story> stories;
    private LayoutInflater inflater;

    public StoryAdapter(Context context, List<Story> stories) {
        this.context = context;
        this.stories = stories;
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_story, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Story story = stories.get(position);
        holder.imgAvatar.setImageDrawable(context.getResources().getDrawable(story.getId()));
        holder.txtName.setText(story.getName());
        String classify = "";
        for (int i = 0; i < story.getClassify().size(); i++) {
            if (i == 0) {
                classify = classify + story.getClassify().get(i);
            } else {
                classify = classify + " , " + story.getClassify().get(i);
            }
        }
        holder.txtClassify.setText(classify);
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgAvatar;
        private TextView txtName;
        private TextView txtClassify;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            txtName = itemView.findViewById(R.id.txtName);
            txtClassify = itemView.findViewById(R.id.txtClassify);
        }
    }
}
