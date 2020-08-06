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
import com.hthh.bookapp.model.StoryOfBookcase;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookcaseAdapter extends RecyclerView.Adapter<BookcaseAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<StoryOfBookcase> stories;
    public OnClickItemListener clickItemListener;

    public void setClickItemListener(OnClickItemListener clickItemListener) {
        this.clickItemListener = clickItemListener;
    }

    public BookcaseAdapter(Context context, List<StoryOfBookcase> stories) {
        this.context = context;
        this.stories = stories;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_book_case, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final StoryOfBookcase storyOfBookcase = stories.get(position);
        holder.txtName.setText(storyOfBookcase.getName());
        Glide.with(context).load(storyOfBookcase.getLinkimage()).into(holder.roundedImageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItemListener.onClickItem(storyOfBookcase);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                clickItemListener.onLongClicked(storyOfBookcase);
                return false;
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
        void onClickItem(StoryOfBookcase storyOfBookcase);

        void onLongClicked(StoryOfBookcase storyOfBookcase);
    }
}
