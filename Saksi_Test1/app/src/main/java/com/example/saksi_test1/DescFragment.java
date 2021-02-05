package com.example.saksi_test1;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.sql.Connection;


public class DescFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    String storeName, detail, typeTattoo, imgTattoo;

    Button test;

    public DescFragment()
    {

    }

    public DescFragment(String storeName,String detail,String typeTattoo,String imgTattoo)
    {
        this.storeName=storeName;
        this.detail=detail;
        this.typeTattoo=typeTattoo;
        this.imgTattoo=imgTattoo;
    }
    public static DescFragment newInstance(String param1, String param2) {
        DescFragment fragment = new DescFragment();
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
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_desc, container, false);

        ImageView imgtatooholder=view.findViewById(R.id.imgtatooholder);
        TextView storeNameholder=view.findViewById(R.id.storeNameholder);
        TextView detailholder=view.findViewById(R.id.detailholder);
        TextView typetatholder=view.findViewById(R.id.typetatholder);

        Button test=view.findViewById(R.id.test);

        storeNameholder.setText(storeName);
        detailholder.setText(detail);
        typetatholder.setText(typeTattoo);
        Glide.with(getContext()).load(imgTattoo).into(imgtatooholder);





        return view;
    }

    public void onBackPressed()
    {
        AppCompatActivity activity=(AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new RecFragment()).addToBackStack(null).commit();
    }

}