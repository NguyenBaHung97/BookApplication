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
import com.hthh.bookapp.model.Book;
import com.hthh.bookapp.model.Story;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private Context context;
    private List<Story> books;
    private LayoutInflater inflater;
    private OnClickItemListener onClickItemListener;

    public BookAdapter(Context context, List<Story> books) {
        this.context = context;
        this.books = books;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Story book = books.get(position);
        Glide.with(context).load(book.getImage_story()).into(holder.imgAvatar);
        holder.txtName.setText(book.getName_story());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItemListener.onClicked(book);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onClickItemListener.onLongClick(book);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgAvatar;
        private TextView txtName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            txtName = itemView.findViewById(R.id.txtName);
        }
    }

    public interface OnClickItemListener{
        void onClicked(Story story);

        void onLongClick(Story story);
    }
}
