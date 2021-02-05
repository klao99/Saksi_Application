package com.chutikarn.saksi_application.bottomNev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chutikarn.saksi_application.Details_page;
import com.chutikarn.saksi_application.R;
import com.chutikarn.saksi_application.listHomeViewholder;
import com.chutikarn.saksi_application.model.tattooImg;
import com.chutikarn.saksi_application.myAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Home_page extends AppCompatActivity {

    RecyclerView recview;

    DatabaseReference databaseReference;
    FirebaseRecyclerOptions<tattooImg>options;
    FirebaseRecyclerAdapter<tattooImg, listHomeViewholder> adapter;

    ArrayList<tattooImg> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        arrayList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("tattooImg");
        options = new FirebaseRecyclerOptions.Builder<tattooImg>().setQuery(databaseReference, tattooImg.class).build();

        recview = (RecyclerView) findViewById(R.id.recView);
        recview.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recview.setHasFixedSize(true);
//        recview.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        adapter = new FirebaseRecyclerAdapter<tattooImg, listHomeViewholder>(options) {
            @Override
            protected void onBindViewHolder(listHomeViewholder holder, final int position, final tattooImg model) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Home_page.this, Details_page.class);
//                        i.putExtra("imagePreview", model.getImage());
//                        Toast.makeText(getApplicationContext(), model.getName(), Toast.LENGTH_LONG).show();
                        startActivity(intent);
                    }
                });

                Picasso.get().load(model.getImage()).into(holder.imgtatoo, new Callback() {
                    @Override
                    public void onSuccess() { }

                    @Override
                    public void onError(Exception e) {

                    }
                });

            }

            @NonNull
            @Override
            public listHomeViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.singlerow, viewGroup, false);
                return new listHomeViewholder(view);
            }
        };

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recview.setLayoutManager(gridLayoutManager);
        recview.setAdapter(adapter);

//        //BottomNav
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
//
//        bottomNavigationView.setSelectedItemId(R.id.home);
//
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch (menuItem.getItemId()){
//                    case R.id.home:
//                        return true;
//
//                    case R.id.store:
//                        startActivity(new Intent(getApplicationContext(), Store_page.class));
//                        overridePendingTransition(0,0);
//                        return true;
//
//                    case R.id.profile:
//                        startActivity(new Intent(getApplicationContext(), profile_page.class));
//                        overridePendingTransition(0,0);
//                        return true;
//                }
//                return false;
//            }
//        }); //BottomNav



//        recview=findViewById(R.id.recView);
//        //recview.setLayoutManager(new LinearLayoutManager(this));
//        recview.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
//
//        FirebaseRecyclerOptions<tattooImg> options =
//                new FirebaseRecyclerOptions.Builder<tattooImg>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("tattooImg"), tattooImg.class)
//                        .build();
//
//        adapter = new myAdapter(options);
//        recview.setAdapter(adapter);


    }//savedIn

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}//appCompat