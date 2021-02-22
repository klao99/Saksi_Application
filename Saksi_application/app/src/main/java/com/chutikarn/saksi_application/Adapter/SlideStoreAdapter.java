package com.chutikarn.saksi_application.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.chutikarn.saksi_application.R;

public class SlideStoreAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SlideStoreAdapter(Context context){
        this.context =context;
    }

    public int[] slide_images = {
            R.drawable.slide_tattoo_1,
            R.drawable.slide_store_1,
            R.drawable.slide_store_2
    };

    public String[] slide_heading = {
            "เลือกประเภทลายสัก",
            "โชว์ร้านจากประเภทลายที่ชอบ",
            "เลือกร้านค้าที่ตรงใจ"
    };


    public String[] slide_descs = {
            "ตามหาลายที่ใช้ด้วยการเลือกประเภทลายที่ชอบ",

            "ร้านค้าจะโชว์แต่ประเภทร้านที่คุณเลือก",

            "เมื่อขึ้นร้านที่ตรงกับประเภทลายแล้ว\n" +
                    "ก็สามารถเลือกร้านที่คุณชอบได้เลย",
    };

    @Override
    public int getCount() { return slide_heading.length; }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view =layoutInflater.inflate(R.layout.slide_tattoo_layout,container,false);

        ImageView imgSlide = (ImageView) view.findViewById(R.id.imgSlide);
        TextView headSlide = (TextView) view.findViewById(R.id.headSlide);
        TextView textSlide = (TextView) view.findViewById(R.id.textSlide);

        imgSlide.setImageResource(slide_images[position]);
        headSlide.setText(slide_heading[position]);
        textSlide.setText(slide_descs[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
