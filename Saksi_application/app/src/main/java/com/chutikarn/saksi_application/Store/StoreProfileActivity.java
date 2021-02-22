package com.chutikarn.saksi_application.Store;

import android.content.Intent;
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
import com.chutikarn.saksi_application.RequestActivity;
import com.chutikarn.saksi_application.Result.ResultTattooListActivity;
import com.chutikarn.saksi_application.firebase.FirebaseManager;
import com.chutikarn.saksi_application.model.tattooPic;
import com.chutikarn.saksi_application.viewHolder.TattooinStoreProfile;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
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
    TextView txt,nameStore;
    Button btnRequest;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_profile);

        banner = findViewById(R.id.banner);
        imgPro = findViewById(R.id.imgPro);
        txt = findViewById(R.id.txt);
        nameStore = findViewById(R.id.nameStore);
        btnRequest = findViewById(R.id.btnRequest);


        storeId = getIntent().getStringExtra("storeId");
        storeBanner = getIntent().getStringExtra("storeBanner");
        storeProfile = getIntent().getStringExtra("storeProfile");
        storeLocation = getIntent().getStringExtra("storeLocation");
        storeName = getIntent().getStringExtra("storeName");
        storeDetail = getIntent().getStringExtra("storeDetail");

        txt.setText("" + storeDetail);
        nameStore.setText("" + storeName);
        Picasso.get().load("" + storeBanner).into(banner);
        Picasso.get().load("" + storeProfile).into(imgPro);

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StoreProfileActivity.this, RequestActivity.class);
                startActivity(intent);
            }
        });




        // CategoryList

        store_id = getIntent().getStringExtra("storeId");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("tattooPic");
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
                        Intent intent=new Intent(StoreProfileActivity.this, ResultTattooListActivity.class);
                       // intent.putExtra("catId", model.getCatTitle());
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