package com.chutikarn.saksi_application.viewHolder;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chutikarn.saksi_application.R;


public class ResultTattooViewHolder extends RecyclerView.ViewHolder  {

    public ImageView roomImage;

    public ResultTattooViewHolder(@NonNull View itemView) {
        super(itemView);

        roomImage=(ImageView)itemView.findViewById(R.id.room_image);

    }

}
