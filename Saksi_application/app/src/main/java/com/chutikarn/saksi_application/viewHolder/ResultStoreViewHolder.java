package com.chutikarn.saksi_application.viewHolder;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chutikarn.saksi_application.R;


public class ResultStoreViewHolder extends RecyclerView.ViewHolder  {

    public ImageView storeImage;
    public TextView storeName;

    public ResultStoreViewHolder(@NonNull View itemView) {
        super(itemView);

        storeImage=itemView.findViewById(R.id.store_image);
        storeName=itemView.findViewById(R.id.store_name);

    }

}
