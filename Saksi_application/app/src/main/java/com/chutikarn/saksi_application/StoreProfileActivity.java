package com.chutikarn.saksi_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class StoreProfileActivity extends AppCompatActivity {

    String storeId;
    String storeBanner;
    String storeProfile;
    String storeLocation;
    String storeName;
    String storeDetail;

    ImageView banner,imgPro;
    TextView txt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_profile);

        banner = findViewById(R.id.banner);
        imgPro = findViewById(R.id.imgPro);
        txt = findViewById(R.id.txt);


        storeId = getIntent().getStringExtra("storeId");
        storeBanner = getIntent().getStringExtra("storeBanner");
        storeProfile = getIntent().getStringExtra("storeProfile");
        storeLocation = getIntent().getStringExtra("storeLocation");
        storeName = getIntent().getStringExtra("storeName");
        storeDetail = getIntent().getStringExtra("storeDetail");

        txt.setText("" + storeDetail);
        Picasso.get().load("" + storeBanner).centerCrop().into(banner);
        Picasso.get().load("" + storeProfile).into(imgPro);

    }

}