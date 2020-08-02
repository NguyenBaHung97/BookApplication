package com.hthh.bookapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hthh.bookapp.R;
import com.hthh.bookapp.model.Story;
import com.hthh.bookapp.model.StoryOfBookcase;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<Story> stories;
    public OnClickItemListener clickItemListener;

    public void setClickItemListener(OnClickItemListener clickItemListener) {
        this.clickItemListener = clickItemListener;
    }

    public OfferAdapter(Context context, List<Story> stories) {
        this.context = context;
        this.stories = stories;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_offer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Story story = stories.get(position);
        holder.txtName.setText(story.getName_story());
        Glide.with(context).load(story.getImage_story()).into(holder.roundedImageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItemListener.onClickItem(story);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                clickItemListener.onClickLong(story);
                return true;
            }
        });
    }


    @Override
    public int getItemCount() {
        return stories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public RoundedImageView roundedImageView;
        public TextView txtName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roundedImageView = itemView.findViewById(R.id.imgAvatar);
            txtName = itemView.findViewById(R.id.txtName);
        }
    }

    public interface OnClickItemListener {
        void onClickItem(Story story);

        void onClickLong(Story story);
    }
}
