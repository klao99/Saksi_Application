package com.chutikarn.saksi_application.Result;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chutikarn.saksi_application.R;
import com.chutikarn.saksi_application.Store.StoreProfileActivity;
import com.chutikarn.saksi_application.model.Stores;
import com.chutikarn.saksi_application.viewHolder.ResultStoreViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ResultStoreListActivity extends AppCompatActivity {



    RecyclerView recyclerView;
    Query databaseReference;
    String Cat_id;
    FirebaseRecyclerOptions<Stores> options;
    FirebaseRecyclerAdapter<Stores, ResultStoreViewHolder> adapter;

    TextView textType;
    String textTattooType;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_store_list);

        Intent intent = getIntent();
        textTattooType= intent.getStringExtra("catId");

        textType = findViewById(R.id.textType);
        textType.setText("" + textTattooType);
        //



        if (getIntent() != null)
            Cat_id = getIntent().getStringExtra("catId");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Stores").orderByChild("type").equalTo(Cat_id);
        options = new FirebaseRecyclerOptions.Builder<Stores>().setQuery(databaseReference, Stores.class).build();

        recyclerView = (RecyclerView) findViewById(R.id.store_list);
        recyclerView.setHasFixedSize(true);

        if (!Cat_id.isEmpty() && Cat_id!= null) {

        adapter = new FirebaseRecyclerAdapter<Stores, ResultStoreViewHolder>(options) {
            @Override
            protected void onBindViewHolder(ResultStoreViewHolder holder, int position, final Stores model) {
                holder.storeName.setText(model.getStoreName());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       Intent intent=new Intent(ResultStoreListActivity.this, StoreProfileActivity.class);
                       intent.putExtra("storeId", model.getId());
                       intent.putExtra("storeBanner", model.getBanner());
                       intent.putExtra("storeProfile", model.getProfile());
                       intent.putExtra("storeLocation", model.getLocation());
                       intent.putExtra("storeName", model.getStoreName());
                       intent.putExtra("storeDetail", model.getStoreDetail());

                        startActivity(intent);
                    }
                });

                Picasso.get().load(model.getBanner()).into(holder.storeImage, new Callback() {
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
            public ResultStoreViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_result_store, viewGroup, false);
                return new ResultStoreViewHolder(view);
            }
        };

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        }
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