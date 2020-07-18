package com.hthh.bookapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.hthh.bookapp.R;

import java.util.List;

public class SlideAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<Integer> integerList;

    public SlideAdapter(Context context, List<Integer> integers) {
        this.context = context;
        this.integerList = integers;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return integerList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.item_slide, container, false);
        ImageView imgSlide = view.findViewById(R.id.imgSlide);
        imgSlide.setImageDrawable(context.getResources().getDrawable(integerList.get(position)));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
