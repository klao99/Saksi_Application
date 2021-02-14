package com.chutikarn.saksi_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chutikarn.saksi_application.bottomNev.Home_page;
import com.chutikarn.saksi_application.firebase.FirebaseCallbacks;
import com.chutikarn.saksi_application.firebase.FirebaseManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ViewActivity extends AppCompatActivity {

    private ImageView imgtattooholder;
    TextView detailholder;
    Button btnTest;
    String ImageUrl;
    private FirebaseManager firebaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        imgtattooholder = findViewById(R.id.imgtattooholder);
        detailholder = findViewById(R.id.detailholder);
        btnTest = findViewById(R.id.btnTest);

        firebaseManager = new FirebaseManager();
        getFirebaseData();

        initControl();


    }

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