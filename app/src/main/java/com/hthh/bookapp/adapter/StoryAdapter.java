package com.hthh.bookapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hthh.bookapp.R;
import com.hthh.bookapp.model.Story;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {
    private Context context;
    private List<Story> stories;
    private LayoutInflater inflater;
    private OnClickItemListener onClickItemListener;
    private String type = "";

    public StoryAdapter(Context context, List<Story> stories,String type) {
        this.context = context;
        this.stories = stories;
        this.type = type;
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
        final Story story = stories.get(position);
        holder.txtName.setText(story.getName_story());
        Glide.with(context).load(story.getImage_story()).into(holder.imgAvatar);
        holder.txtClassify.setText(type);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItemListener.onClicked(story);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onClickItemListener.onLongClicked(story);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
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

    public interface OnClickItemListener{
        void onClicked(Story story);


        void onLongClicked(Story story);
    }
}
