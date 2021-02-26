package com.chutikarn.saksi_application.store;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chutikarn.saksi_application.R;
import com.chutikarn.saksi_application.ViewActivity2;
import com.chutikarn.saksi_application.firebase.FirebaseManager;
import com.chutikarn.saksi_application.loginRegis.LoginActivity;
import com.chutikarn.saksi_application.model.tattooPic;
import com.chutikarn.saksi_application.request.RequestActivity;
import com.chutikarn.saksi_application.viewHolder.TattooinStoreProfile;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class StoreProfileActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    Query databaseReference;
    FirebaseRecyclerOptions<tattooPic> options;
    FirebaseRecyclerAdapter<tattooPic, TattooinStoreProfile> adapter;
    private FirebaseManager firebaseManager;
    String store_id;

    String storeId;
    String storeBanner;
    String storeProfile;
    String storeLocation;
    String storeName;
    String storeDetail;

    ImageView banner,imgPro;
    TextView txt,nameStore,closePopupImage;
    Button btnRequest,btngoToLogin;

    //Userที่Log in
    FirebaseUser firebaseUser;
    String userID;

    //dialog
    Dialog epicdialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_profile);

        initViewData();
        initControl();
        listTattooStore();

        txt.setText("" + storeDetail);
        nameStore.setText("" + storeName);
        Picasso.get().load("" + storeBanner).into(banner);
        Picasso.get().load("" + storeProfile).into(imgPro);


    } // Oncrate

    private void initViewData() {
        banner = findViewById(R.id.banner);
        imgPro = findViewById(R.id.imgPro);
        txt = findViewById(R.id.txt);
        nameStore = findViewById(R.id.nameStore);
        btnRequest = findViewById(R.id.btnRequest);

        epicdialog = new Dialog(this);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


        storeId = getIntent().getStringExtra("storeId");
        storeBanner = getIntent().getStringExtra("storeBanner");
        storeProfile = getIntent().getStringExtra("storeProfile");
        storeLocation = getIntent().getStringExtra("storeLocation");
        storeName = getIntent().getStringExtra("storeName");
        storeDetail = getIntent().getStringExtra("storeDetail");
    }

    private void initControl() {
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null){
                    Intent intent=new Intent(StoreProfileActivity.this, RequestActivity.class);
                    intent.putExtra("storeId",storeId);
                    userID = firebaseUser.getUid();
                    startActivity(intent);
                }else {
                    ShowAlertLogin();
//                    startActivity(new Intent(StoreProfileActivity.this, LoginActivity.class));
//                    finish();
                }
            }
        });
    }


    //PopUp
    private void ShowAlertLogin() {
        epicdialog.setContentView(R.layout.custom_dialog);
        closePopupImage = epicdialog.findViewById(R.id.btnClose);
        btngoToLogin = epicdialog.findViewById(R.id.btngoToLogin);

        closePopupImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicdialog.dismiss();
            }
        });
        btngoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StoreProfileActivity.this, LoginActivity.class));
                finish();
            }
        });
        epicdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicdialog.show();

    }

    private void listTattooStore() {
        // TattooList

        store_id = getIntent().getStringExtra("storeId");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("tattooPic").orderByChild("store_id").equalTo(storeId);
        options = new FirebaseRecyclerOptions.Builder<tattooPic>().setQuery(databaseReference, tattooPic.class).build();

        recyclerView = (RecyclerView) findViewById(R.id.recViewtattoo);
        recyclerView.setHasFixedSize(true);

        adapter = new FirebaseRecyclerAdapter<tattooPic, TattooinStoreProfile>(options) {
            @Override
            protected void onBindViewHolder(TattooinStoreProfile holder, int position, final tattooPic model) {
                //holder.tattooImage.setText(model.getCatTitle());
                //holder.idCat.setText(model.getCatId());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(StoreProfileActivity.this, ViewActivity2.class);
                        intent.putExtra("imageUrl",model.getImageUrl());
                        intent.putExtra("detail",model.getDetail());
                        //
                        intent.putExtra("storeProfile",storeProfile);
                        intent.putExtra("storeName",storeName);
                        startActivity(intent);
                    }
                });

                Picasso.get().load(model.getImageUrl()).into(holder.tattooImage, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
            }

            @NonNull
            @Override
            public TattooinStoreProfile onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tattoo_in_store_profile, viewGroup, false);
                return new TattooinStoreProfile(view);
            }
        };

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {

        if(adapter!=null)
            adapter.startListening();
        super.onStart();
    }

    @Override
    protected void onStop() {
        if (adapter!=null)
            adapter.stopListening();
        super.onStop();
    }

}