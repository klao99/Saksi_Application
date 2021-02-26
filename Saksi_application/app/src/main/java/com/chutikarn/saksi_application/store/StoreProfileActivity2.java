package com.chutikarn.saksi_application.store;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.chutikarn.saksi_application.loginRegis.LoginActivity;
import com.chutikarn.saksi_application.R;
import com.chutikarn.saksi_application.model.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class StoreProfileActivity2 extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;

    String profile,userID;
    String nameStore;

    ImageView imgPro;
    TextView textNameStore;

    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_profile2);


        btnLogout = findViewById(R.id.btnLogout);
        textNameStore = findViewById(R.id.nameStore);
        imgPro = findViewById(R.id.imgPro);

        DecsProfile();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });
    }

    private void DecsProfile() {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null){
            userID = user.getUid();
            databaseReference.child("Users/"+ userID).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    showData(snapshot);
                }

                @Override
                public void onCancelled(DatabaseError error) {

                }
            });

        }else {
            startActivity(new Intent(StoreProfileActivity2.this, LoginActivity.class));
            finish();
        }

    }

    private  void showData(DataSnapshot snapshot) {

        Users uInfo = snapshot.getValue(Users.class);
        textNameStore.setText(uInfo.getName());
        Picasso.get().load(uInfo.getProfile()).into(imgPro);

    }

}