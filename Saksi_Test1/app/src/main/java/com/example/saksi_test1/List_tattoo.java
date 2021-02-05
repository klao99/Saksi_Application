package com.example.saksi_test1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

//import com.example.saksi_test1.model.TattooData;
import com.example.saksi_test1.model.StoreData;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class List_tattoo extends AppCompatActivity {

    RecyclerView recView;
   Myadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tattoo);

        getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new RecFragment()).commit();

//        recView = findViewById(R.id.recView);
//        recView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
//
//        FirebaseRecyclerOptions<StoreData> options =
//                new FirebaseRecyclerOptions.Builder<StoreData>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Store"), StoreData.class)
//                        .build();
//
//        adapter = new Myadapter(options);
//        recView.setAdapter(adapter);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        adapter.startListening();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        adapter.stopListening();
//    }
}