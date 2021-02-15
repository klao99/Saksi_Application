package com.chutikarn.saksi_application.bottomNev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.chutikarn.saksi_application.CategoryListActivity;
import com.chutikarn.saksi_application.R;
import com.chutikarn.saksi_application.ResultTattooListActivity;
import com.chutikarn.saksi_application.firebase.FirebaseManager;
import com.chutikarn.saksi_application.model.Category;
import com.chutikarn.saksi_application.viewHolder.CategoryStoreListActivity;
import com.chutikarn.saksi_application.viewHolder.CategoryViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class StoreActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Query databaseReference;
    FirebaseRecyclerOptions<Category> options;
    FirebaseRecyclerAdapter<Category, CategoryStoreListActivity> adapter;
    private FirebaseManager firebaseManager;
    String typeTattoo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_page);

        //BottomNav
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.store);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.store:
                        return true;

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        // CategoryList

       // typeTattoo = getIntent().getStringExtra("typeTattoo");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("category");
        options = new FirebaseRecyclerOptions.Builder<Category>().setQuery(databaseReference, Category.class).build();

        recyclerView = (RecyclerView) findViewById(R.id.catStore_list);
        recyclerView.setHasFixedSize(true);

        adapter = new FirebaseRecyclerAdapter<Category, CategoryStoreListActivity>(options) {
            @Override
            protected void onBindViewHolder(CategoryStoreListActivity holder, int position, final Category model) {
                holder.catName.setText(model.getCatTitle());
                //holder.idCat.setText(model.getCatId());
//                holder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent=new Intent(StoreActivity.this, ResultTattooListActivity.class);
//                        intent.putExtra("catId", model.getCatTitle());
//                        startActivity(intent);
//                    }
//                });

                Picasso.get().load(model.getCatIcon()).into(holder.catImage, new Callback() {
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
            public CategoryStoreListActivity onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_category_store, viewGroup, false);
                return new CategoryStoreListActivity(view);
            }
        };

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
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

}//Appcompat