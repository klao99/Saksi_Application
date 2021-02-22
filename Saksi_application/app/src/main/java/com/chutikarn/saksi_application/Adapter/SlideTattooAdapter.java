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

public class SlideTattooAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SlideTattooAdapter(Context context){
        this.context =context;
    }

    public int[] slide_images = {
      R.drawable.slide_tattoo_1,
      R.drawable.slide_tattoo_2,
      R.drawable.slide_tattoo_3,
      R.drawable.slide_tattoo_4
    };

    public String[] slide_heading = {
            "เลือกประเภทลายสัก",
            "เลือกลายสักที่ชื่นชอบ",
            "เลือกวางได้ตามใจคุณ",
            "เซฟ หรือ แชร์"
    };


    public String[] slide_descs = {
            "ตามหาลายที่ใช้ด้วยการเลือกประเภทลายที่ชอบ",

            "เลือกลายที่คุณชื่นชอบเพื่อมาทดลอง",

            "เลือกรูปมาจากอัลบั้มของคุณ ย่อขยาย \n" +
                    "หรือเปลี่ยนสีตามใจของคุณ\n",

            "เเมื่อแนใจแล้วว่าคุณเลือกลายสักที่ตำแหน่ง\n" +
                    "นี้แล้วคุณสามารถกด “บันทึก” เพื่อ\n" +
                    "บันทึกรูปของคุณลงมือถือ \n" +
                    "หรือคุณต้องการแชร์ให้เพื่อให้เพื่อนๆของคุณได้รู้"
    };

    @Override
    public int getCount() {
        return slide_heading.length;
    }

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
