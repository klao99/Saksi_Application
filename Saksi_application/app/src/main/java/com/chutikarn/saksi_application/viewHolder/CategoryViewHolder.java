package com.chutikarn.saksi_application.viewHolder;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chutikarn.saksi_application.R;


public class CategoryViewHolder extends RecyclerView.ViewHolder  {


    public ImageView roomImage;
    public TextView roomPrice;


    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        roomPrice=(TextView)itemView.findViewById(R.id.room_price);
        //idCat=(TextView)itemView.findViewById(R.id.idCat);
        roomImage=(ImageView)itemView.findViewById(R.id.room_image);

    }

}
