package com.chutikarn.saksi_application.viewHolder;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chutikarn.saksi_application.R;

public class TattooinStoreProfile extends RecyclerView.ViewHolder {

    public ImageView tattooImage;


    public TattooinStoreProfile(@NonNull View itemView) {
        super(itemView);
        tattooImage=(ImageView)itemView.findViewById(R.id.tattoo_image);

    }
}
