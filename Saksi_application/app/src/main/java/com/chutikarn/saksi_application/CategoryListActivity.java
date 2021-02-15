package com.chutikarn.saksi_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chutikarn.saksi_application.firebase.FirebaseManager;
import com.chutikarn.saksi_application.model.Category;
import com.chutikarn.saksi_application.viewHolder.CategoryViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class CategoryListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Query databaseReference;
    FirebaseRecyclerOptions<Category> options;
    FirebaseRecyclerAdapter<Category, CategoryViewHolder> adapter;
    private FirebaseManager firebaseManager;
    String typeTattoo;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);


        // CategoryList

        typeTattoo = getIntent().getStringExtra("typeTattoo");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("category");
        options = new FirebaseRecyclerOptions.Builder<Category>().setQuery(databaseReference, Category.class).build();

        recyclerView = (RecyclerView) findViewById(R.id.room_list);
        recyclerView.setHasFixedSize(true);

            adapter = new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(options) {
                @Override
                protected void onBindViewHolder(CategoryViewHolder holder, int position, final Category model) {
                    holder.roomPrice.setText(model.getCatTitle());
                    //holder.idCat.setText(model.getCatId());
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(CategoryListActivity.this, ResultTattooListActivity.class);
                            intent.putExtra("catId", model.getCatTitle());
                            startActivity(intent);
                        }
                    });

                    Picasso.get().load(model.getCatIcon()).into(holder.roomImage, new Callback() {
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
                public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_category_tattoo, viewGroup, false);
                    return new CategoryViewHolder(view);
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
}

