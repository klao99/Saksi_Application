package com.chutikarn.saksi_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.chutikarn.saksi_application.firebase.FirebaseCallbacks;
import com.chutikarn.saksi_application.firebase.FirebaseManager;
import com.google.firebase.database.DataSnapshot;
import com.squareup.picasso.Picasso;

public class ViewActivity extends AppCompatActivity {

    private ImageView imgtattooholder,imgPro;
    TextView detailholder,storeNameholder;
    Button btnTest;
    String ImageUrl;

    String storeName,storeProfile;

    private FirebaseManager firebaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        imgtattooholder = findViewById(R.id.imgtattooholder);
        detailholder = findViewById(R.id.detailholder);
        btnTest = findViewById(R.id.btnTest);
        imgPro =findViewById(R.id.imgPro);
        storeNameholder = findViewById(R.id.storeNameholder);

//        storeName = getIntent().getStringExtra("storeName");
//        storeProfile = getIntent().getStringExtra("storeProfile");

//        storeNameholder.setText("" + storeName);
//        Picasso.get().load("" + storeProfile).into(imgPro);

        firebaseManager = new FirebaseManager();
        getFirebaseData();
        initControl();


    }//onCreate



    private void initControl() {
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ViewActivity.this, TestTattooActivity.class);
                intent.putExtra("imageUrl",ImageUrl);
                startActivity(intent);
            }
        });
    }

    private void getFirebaseData() {
        String id =getIntent().getStringExtra("pickey");

        firebaseManager.getTattooImgByID(id,new FirebaseCallbacks() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                    String detail=dataSnapshot.child("detail").getValue().toString();
                    ImageUrl=dataSnapshot.child("imageUrl").getValue().toString();
                    Picasso.get().load(ImageUrl).into(imgtattooholder);
                    detailholder.setText(detail);

            }


            @Override
            public void onLoading() {

            }

            @Override
            public void onFail() {

            }
        });
    }

}//viewActivity