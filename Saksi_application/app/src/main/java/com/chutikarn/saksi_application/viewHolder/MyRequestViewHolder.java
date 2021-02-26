package com.chutikarn.saksi_application.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chutikarn.saksi_application.R;

public class MyRequestViewHolder extends RecyclerView.ViewHolder{

    public TextView inputPosition,inputSize,inputColor,inputDetail,inPutDateTime,nameUser;
    public ImageView imgtattoo;

    public MyRequestViewHolder(@NonNull View itemView) {
        super(itemView);

        inputColor=itemView.findViewById(R.id.color);
        inputSize=itemView.findViewById(R.id.size);
        inputPosition=itemView.findViewById(R.id.position);
        inputDetail=itemView.findViewById(R.id.detail);
        inPutDateTime=itemView.findViewById(R.id.dateTime);
        imgtattoo=itemView.findViewById(R.id.imgtattoo);
        //nameUser=itemView.findViewById(R.id.nameUser);
    }
}
