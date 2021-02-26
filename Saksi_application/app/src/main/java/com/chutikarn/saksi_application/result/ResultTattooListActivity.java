package com.chutikarn.saksi_application.result;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chutikarn.saksi_application.R;
import com.chutikarn.saksi_application.ViewActivity2;
import com.chutikarn.saksi_application.model.tattooPic;
import com.chutikarn.saksi_application.viewHolder.ResultTattooViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ResultTattooListActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseRef;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;

    RecyclerView recyclerView;
    String Cat_id;
    Query databaseReference;
    FirebaseRecyclerOptions<tattooPic> options;
    FirebaseRecyclerAdapter<tattooPic, ResultTattooViewHolder> adapter;

    String catTitle;
    TextView textType;
    String textTattooType;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_tattoo_list);

        Intent intent = getIntent();
        textTattooType= intent.getStringExtra("catTitle");

        textType = findViewById(R.id.textType);
        textType.setText("" + textTattooType);



        // RoomList

        if (getIntent() != null)

        Cat_id = getIntent().getStringExtra("catId");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("tattooPic").orderByChild("typeTattoo").equalTo(Cat_id);
        options = new FirebaseRecyclerOptions.Builder<tattooPic>().setQuery(databaseReference, tattooPic.class).build();

        recyclerView = (RecyclerView) findViewById(R.id.tattoo_list);
        recyclerView.setHasFixedSize(true);

        if (!Cat_id.isEmpty() && Cat_id!= null) {

            adapter = new FirebaseRecyclerAdapter<tattooPic, ResultTattooViewHolder>(options) {
                @Override
                protected void onBindViewHolder(ResultTattooViewHolder holder, int position, final tattooPic model) {
                    //holder.textType.setText(model.getTypeTattoo());
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Intent i=new Intent(Page_room_list.this,Page_room_detail.class);
                            Intent intent = new Intent(ResultTattooListActivity.this, ViewActivity2.class);
                            intent.putExtra("imageUrl",model.getImageUrl());
                            intent.putExtra("detail",model.getDetail());

                             startActivity(intent);
                        }
                    });

                    Picasso.get().load(model.getImageUrl()).into(holder.roomImage, new Callback() {
                        @Override
                        public void onSuccess() { }

                        @Override
                        public void onError(Exception e) {

                        }
                    });
                }

                @NonNull
                @Override
                public ResultTattooViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_result_tattoo, viewGroup, false);
                    return new ResultTattooViewHolder(view);
                }
            };

            GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(adapter);

        }
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
