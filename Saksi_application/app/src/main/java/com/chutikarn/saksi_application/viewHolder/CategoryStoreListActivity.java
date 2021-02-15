package com.chutikarn.saksi_application.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chutikarn.saksi_application.R;

public class CategoryStoreListActivity extends RecyclerView.ViewHolder {

    public ImageView catImage;
    public TextView catName;


    public CategoryStoreListActivity(@NonNull View itemView) {
        super(itemView);

        catImage=(ImageView)itemView.findViewById(R.id.cat_image);
        catName=(TextView)itemView.findViewById(R.id.cat_name);
    }
}
