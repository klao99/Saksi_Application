package com.chutikarn.saksi_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class ViewActivity2 extends AppCompatActivity {

    String ImageUrl,detail;
    String storeName,storeProfile;


    ImageView imgtattooId,imgPro;
    TextView detailId,storeNameholder;

    Button btnTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_2);

        imgtattooId =findViewById(R.id.imgtattooId);
        imgPro =findViewById(R.id.imgPro);
        detailId =findViewById(R.id.detailId);
        btnTest = findViewById(R.id.btnTest);
        storeNameholder = findViewById(R.id.storeNameholder);

        ImageUrl = getIntent().getStringExtra("imageUrl");
        detail = getIntent().getStringExtra("detail");

        storeName = getIntent().getStringExtra("storeName");
        storeProfile = getIntent().getStringExtra("storeProfile");


        detailId.setText("" + detail);
        Picasso.get().load("" + ImageUrl).into(imgtattooId);


        storeNameholder.setText("" + storeName);
        Picasso.get().load("" + storeProfile).into(imgPro);

        initControl();


    }

    private void initControl() {
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ViewActivity2.this, TestTattooActivity.class);
                intent.putExtra("imageUrl",ImageUrl);
                startActivity(intent);
            }
        });
    }
}