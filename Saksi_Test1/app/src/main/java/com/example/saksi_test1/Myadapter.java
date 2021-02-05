package com.example.saksi_test1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.saksi_test1.model.ImageTattoo;
import com.example.saksi_test1.model.StoreData;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static java.security.AccessController.getContext;



public class Myadapter extends FirebaseRecyclerAdapter<StoreData,Myadapter.myviewholder>
{

    public Myadapter(@NonNull FirebaseRecyclerOptions<StoreData> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder( myviewholder holder, int position ,final StoreData model )
    {
        //holder.storeName.setText(model.getStoreName());
        Glide.with(holder.imgtattoo.getContext()).load(model.getImgTattoo()).into(holder.imgtattoo);
        //Picasso.get().load(storeData.getImgtatoo().getPictattoo()).into(holder.imgtattoo);

                holder.imgtattoo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       AppCompatActivity activity=(AppCompatActivity)view.getContext();
                       activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new DescFragment(model.getStoreName(),model.getDetail(),model.getTypeTattoo(),model.getImgTattoo())).addToBackStack(null).commit();
                    }
                });
    }


    @Override
    public myviewholder onCreateViewHolder( ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    public static class myviewholder extends RecyclerView.ViewHolder {
        ImageView imgtattoo;

       // TextView storeName;
        public myviewholder(View itemView) {
            super(itemView);

            imgtattoo = itemView.findViewById(R.id.imgtatoo);
//            storeName = itemView.findViewById(R.id.nameStore);
        }


    }
}
