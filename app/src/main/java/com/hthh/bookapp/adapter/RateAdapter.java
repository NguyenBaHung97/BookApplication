package com.hthh.bookapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hthh.bookapp.R;
import com.hthh.bookapp.model.Rate;

import java.util.List;

public class RateAdapter extends RecyclerView.Adapter<RateAdapter.ViewHolder> {
    private List<Rate> rates;
    private Context context;
    private LayoutInflater inflater;

    public RateAdapter(List<Rate> rates, Context context) {
        this.rates = rates;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Rate rate = rates.get(position);
        holder.txtName.setText(rate.getEmail());
        holder.ratingBar.setRating(Float.parseFloat(rate.getRating()));
        holder.txtComment.setText(rate.getComment());

    }

    @Override
    public int getItemCount() {
        return rates.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private RatingBar ratingBar;
        private TextView txtComment;
        private RecyclerView rcvRate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtNameUser);
            ratingBar = itemView.findViewById(R.id.rating);
            txtComment = itemView.findViewById(R.id.txtComment);
            rcvRate = itemView.findViewById(R.id.rcvRate);
        }
    }
}
