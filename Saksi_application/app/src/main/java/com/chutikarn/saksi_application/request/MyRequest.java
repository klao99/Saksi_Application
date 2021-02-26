package com.chutikarn.saksi_application.request;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chutikarn.saksi_application.R;
import com.chutikarn.saksi_application.model.Request;
import com.chutikarn.saksi_application.viewHolder.MyRequestViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class MyRequest extends AppCompatActivity {

    RecyclerView recyclerView;
    Query databaseReference;
    FirebaseRecyclerOptions<Request> options;
    FirebaseRecyclerAdapter<Request, MyRequestViewHolder> adapter;


    private TextView inputPosition,inputSize,inputColor,inputDetail,inPutDateTime,nameUser;
    private ImageView imgtattoo;

    String dateTime;
    String position;
    String color;
    String imageUrl;
    String detail;
    String size;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_request);

//        inputColor=findViewById(R.id.color);
//        inputSize=findViewById(R.id.size);
//        inputPosition=findViewById(R.id.position);
//        inputDetail=findViewById(R.id.detail);
//        inPutDateTime=findViewById(R.id.dateTime);
//        imgtattoo=findViewById(R.id.imgtattoo);
        //nameUser=findViewById(R.id.nameUser);




        // CategoryList

//        name = getIntent().getStringExtra("name");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Request");
        options = new FirebaseRecyclerOptions.Builder<Request>().setQuery(databaseReference, Request.class).build();

        recyclerView = (RecyclerView) findViewById(R.id.recViewMyRequest);
        recyclerView.setHasFixedSize(true);

        adapter = new FirebaseRecyclerAdapter<Request, MyRequestViewHolder>(options) {
            @Override
            protected void onBindViewHolder(MyRequestViewHolder holder, int position, final Request model) {
                holder.inputColor.setText(model.getColor());
                holder.inPutDateTime.setText(model.getDateTime());
                holder.inputPosition.setText(model.getPosition());
                holder.inputSize.setText(model.getSize());
                holder.inputDetail.setText(model.getDetail());
//                holder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent=new Intent(StoreProfileActivity.this, ViewActivity2.class);
//                        intent.putExtra("imageUrl",model.getImageUrl());
//                        intent.putExtra("detail",model.getDetail());
//                        //
//                        intent.putExtra("storeProfile",storeProfile);
//                        intent.putExtra("storeName",storeName);
//                        startActivity(intent);
//                    }
//                });

                Picasso.get().load(model.getImageUrl()).into(holder.imgtattoo, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
            }

            @NonNull
            @Override
            public MyRequestViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_my_request, viewGroup, false);
                return new MyRequestViewHolder(view);
            }
        };

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
    }



    @Override
    protected void onStart() {

        if(adapter!=null)
            adapter.startListening();
        super.onStart();
    }

    @Override
    protected void onStop() {
        if (adapter!=null)
            adapter.stopListening();
        super.onStop();
    }
}