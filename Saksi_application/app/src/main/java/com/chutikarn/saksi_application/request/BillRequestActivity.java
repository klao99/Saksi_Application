package com.chutikarn.saksi_application.request;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.chutikarn.saksi_application.R;
import com.squareup.picasso.Picasso;

public class BillRequestActivity extends AppCompatActivity {

    private TextView inputPosition,inputSize,inputColor,inputDetail,inPutDateTime;
    private ImageView imgtattoo;

    String dateTime;
    String position;
    String color;
    String imageUrl;
    String detail;
    String size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_request);

        inputColor=findViewById(R.id.color);
        inputSize=findViewById(R.id.size);
        inputPosition=findViewById(R.id.position);
        inputDetail=findViewById(R.id.detail);
        inPutDateTime=findViewById(R.id.dateTime);
        imgtattoo=findViewById(R.id.imgtattoo);


        dateTime = getIntent().getStringExtra("dateTime");
        position = getIntent().getStringExtra("position");
        color = getIntent().getStringExtra("color");
        imageUrl = getIntent().getStringExtra("imageUrl");
        detail = getIntent().getStringExtra("detail");
        size = getIntent().getStringExtra("size");

        inputColor.setText("" + color);
        inputSize.setText("" + size);
        inputPosition.setText("" + position);
        inputDetail.setText("" + detail);
        inPutDateTime.setText("" + dateTime);
        Picasso.get().load("" + imageUrl).into(imgtattoo);
    }
}