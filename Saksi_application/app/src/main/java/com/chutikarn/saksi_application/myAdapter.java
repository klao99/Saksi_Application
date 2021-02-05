package com.chutikarn.saksi_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransitionImpl;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chutikarn.saksi_application.model.tattooImg;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myAdapterViewHoder>
{
    public Context context;
    public ArrayList<tattooImg> arrayList;
    public myAdapter(Context context, ArrayList<tattooImg> arrayList)
    {
        this.context = context;
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public myAdapter.myAdapterViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myAdapterViewHoder(v);
    }

    @Override
    public void onBindViewHolder( myAdapterViewHoder holder, int position) {
        tattooImg picitem = arrayList.get(position);
        Picasso.get().load(picitem.getImage()).into(holder.imgtattoo);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class myAdapterViewHoder extends RecyclerView.ViewHolder{

        public RoundedImageView imgtattoo;

        public myAdapterViewHoder(@NonNull View itemView) {
            super(itemView);

            imgtattoo = (RoundedImageView)itemView.findViewById(R.id.imgtatoo);

        }
    }
}
