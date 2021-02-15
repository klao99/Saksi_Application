package com.chutikarn.saksi_application.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chutikarn.saksi_application.R;

public class MyViewHolder extends RecyclerView.ViewHolder {

   public ImageView image_singlerow;
   //public TextView detail_Home;
   public View v;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        image_singlerow = itemView.findViewById(R.id.image_singlerow);
        //detail_Home = itemView.findViewById(R.id.detail_Home);
        v = itemView;
    }
}
