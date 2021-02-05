package com.example.saksi_test1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.saksi_test1.model.ImageTattoo;
import com.example.saksi_test1.model.StoreData;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;

public class RecFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

//    DatabaseReference DatabaseReference;
//    FirebaseDatabase firebaseDatabase;
//    ArrayList<StoreData> storeData = new ArrayList<>();
//    private static RecyclerView.Adapter adapter;
    //private RecyclerView.LayoutManager layoutManager;
//    private static RecyclerView recView;

    RecyclerView recView;
    Myadapter adapter;


    public RecFragment() {
        // Required empty public constructor
    }


    public static RecFragment newInstance(String param1, String param2) {
        RecFragment fragment = new RecFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_rec, container, false);
        recView = view.findViewById(R.id.recView);
        recView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        FirebaseRecyclerOptions<StoreData> options =
                new FirebaseRecyclerOptions.Builder<StoreData>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Stores"), StoreData.class)
                        .build();

        adapter = new Myadapter(options);
        recView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}