package com.chutikarn.saksi_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ViewActivity2 extends AppCompatActivity {

    String ImageUrl,detail;


    ImageView imgtattooId;
    TextView detailId,storeNameholder;

    Button btnTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_2);

        imgtattooId =findViewById(R.id.imgtattooId);
        detailId =findViewById(R.id.detailId);
        btnTest = findViewById(R.id.btnTest);
        storeNameholder = findViewById(R.id.storeNameholder);

        ImageUrl = getIntent().getStringExtra("imageUrl");
        detail = getIntent().getStringExtra("detail");


        detailId.setText("" + detail);
        Picasso.get().load("" + ImageUrl).into(imgtattooId);

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