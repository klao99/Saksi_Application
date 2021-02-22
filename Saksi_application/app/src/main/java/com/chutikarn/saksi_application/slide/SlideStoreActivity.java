package com.chutikarn.saksi_application.slide;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.chutikarn.saksi_application.Adapter.SlideStoreAdapter;
import com.chutikarn.saksi_application.R;
import com.chutikarn.saksi_application.bottomNev.StoreActivity;

public class SlideStoreActivity extends AppCompatActivity {

    private ViewPager mSlideView;
    private LinearLayout mDotLayout;
    private TextView btnPassIntro;
    private Button btnNext;

    private TextView[] mDots;
    private SlideStoreAdapter slideStoreAdap;
    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_store);

        mDotLayout=findViewById(R.id.mDotLayout);
        mSlideView=findViewById(R.id.mSlideView);
        btnPassIntro=findViewById(R.id.btnPassIntro);
        btnNext=findViewById(R.id.btnNext);

       slideStoreAdap = new SlideStoreAdapter(this);
       mSlideView.setAdapter(slideStoreAdap);

        addDotsIndicator(0);
        mSlideView.addOnPageChangeListener(viewListenner);

        initControl();
    }

    private void initControl() {
        btnPassIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SlideStoreActivity.this, StoreActivity.class);
                startActivity(intent);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentPage == 2 ){
                    Intent intent=new Intent(SlideStoreActivity.this, StoreActivity.class);
                    startActivity(intent);
                }else {
                    mSlideView.setCurrentItem(mCurrentPage + 1);
                }
            }
        });
    }

    private void addDotsIndicator(int position) {
        mDots = new TextView[3];

        mDotLayout.removeAllViews();
        for (int i = 0; i < mDots.length; i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorPrimary));

            mDotLayout.addView(mDots[i]);
        }

        if (mDots.length >0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    ViewPager.OnPageChangeListener viewListenner = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            mCurrentPage = position;

            if (position == 0){
                btnNext.setEnabled(true);
                btnPassIntro.setEnabled(true);
                btnPassIntro.setVisibility(View.VISIBLE);
                btnNext.setText("ต่อไป");

            }else if (position == mDots.length - 1){
                btnNext.setEnabled(true);
                btnPassIntro.setEnabled(false);
                btnPassIntro.setVisibility(View.INVISIBLE);
                btnNext.setText("ฉันเข้าใจแล้ว");

            }else {
                btnNext.setEnabled(true);
                btnPassIntro.setEnabled(false);
                btnPassIntro.setVisibility(View.INVISIBLE);
                btnNext.setText("ต่อไป");

            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}