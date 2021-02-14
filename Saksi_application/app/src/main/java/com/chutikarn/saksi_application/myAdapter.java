package com.chutikarn.saksi_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chutikarn.saksi_application.model.tattooPic;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myAdapterViewHoder>
{
    public Context context;
    public ArrayList<tattooPic> arrayList;
    public myAdapter(Context context, ArrayList<tattooPic> arrayList)
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
        tattooPic picitem = arrayList.get(position);
        holder.detailholder.setText(picitem.getDetail());
        Picasso.get().load(picitem.getImageUrl()).into(holder.image_singlerow);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class myAdapterViewHoder extends RecyclerView.ViewHolder{

        ImageView image_singlerow;
        TextView detailholder;

        public myAdapterViewHoder(@NonNull View itemView) {
            super(itemView);
            image_singlerow = itemView.findViewById(R.id.image_singlerow);
            detailholder = itemView.findViewById(R.id.detailholder);

        }
    }
}
