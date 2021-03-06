package com.hthh.bookapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.hthh.bookapp.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import jp.wasabeef.blurry.Blurry;

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
        final RoundedImageView imgSlide = view.findViewById(R.id.imgSlide);
        TextView txtName = view.findViewById(R.id.txtName);
        final ViewGroup viewGroup = view.findViewById(R.id.viewGroup);
        final ImageView imgBackground = view.findViewById(R.id.imgBackground);
        switch (position) {
            case 0:
                txtName.setText("Doraemon mặt trăng phiêu lưu ký");
                break;
            case 1:
                txtName.setText("Conan thám tử lừng danh");
                break;
            case 2:
                txtName.setText("Naruto ngoại truyện");
                break;
            case 3:
                txtName.setText("One piece đảo hải tặc");
                break;
            case 4:
                txtName.setText("Bảy viên ngọc rồng");
                break;
        }
        imgSlide.setImageDrawable(context.getResources().getDrawable(integerList.get(position)));
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                Blurry.with(context).radius(25).sampling(2).color(Color.argb(92, 255, 255, 255))
                        .async().capture(imgSlide).into(imgBackground);

            }
        },200);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
