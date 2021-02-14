package com.chutikarn.saksi_application.bottomNev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chutikarn.saksi_application.MyViewHolder;
import com.chutikarn.saksi_application.R;
import com.chutikarn.saksi_application.ViewActivity;
import com.chutikarn.saksi_application.firebase.FirebaseCallbacks;
import com.chutikarn.saksi_application.firebase.FirebaseManager;
import com.chutikarn.saksi_application.model.tattooPic;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Home_page extends AppCompatActivity {


    RecyclerView recView;
    FirebaseRecyclerOptions<tattooPic>options;
    FirebaseRecyclerAdapter<tattooPic, MyViewHolder>adapter;
    DatabaseReference dataRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        recView = findViewById(R.id.recView);
       // recView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recView.setHasFixedSize(true);

        dataRef = FirebaseDatabase.getInstance().getReference().child("tattooPic");



        LoadData();

        //BottomNav
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        return true;

                    case R.id.store:
                        startActivity(new Intent(getApplicationContext(), Store_page.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), profile_page.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        }); //BottomNav

    }



    private void LoadData() {
        options = new FirebaseRecyclerOptions.Builder<tattooPic>().setQuery(dataRef,tattooPic.class).build();
        adapter = new FirebaseRecyclerAdapter<tattooPic, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, final int position, @NonNull final tattooPic model) {
               // holder.detail_Home.setText(model.getDetail());
                Picasso.get().load(model.getImageUrl()).into(holder.image_singlerow);
                holder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(Home_page.this, ViewActivity.class);
                        intent.putExtra("pickey",getRef(position).getKey());
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
                return new MyViewHolder(v);
            }
        };
        adapter.startListening();
        recView.setAdapter(adapter);
    }///onCreate


}//homepageClass